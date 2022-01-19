package uz.qmgroup.pharmabook.repos

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import uz.qmgroup.pharmabook.models.Medicine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MedicinesRepo(
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    suspend fun getMedicines() = firebaseFirestore.collection("medicines").get().toSuspendFunc()

    suspend fun getMedicine(id: String) =
        firebaseFirestore.collection("medicines").document(id).get().toSuspendFunc()

    suspend fun addMedicine(medicine: Medicine) {
        val documentReference = firebaseFirestore.collection("medicines").document()
        medicine.id = documentReference.id
        documentReference.set(medicine).toSuspendFunc()
    }

    suspend fun updateMedicine(id: String, medicine: Medicine) =
        firebaseFirestore.collection("medicines").document(id).set(medicine).toSuspendFunc()

    suspend fun deleteMedicine(id: String) =
        firebaseFirestore.collection("medicines").document(id).delete().toSuspendFunc()

    suspend fun getMedicinesByTags(tags: List<String>) =
        firebaseFirestore.collection("medicines").whereArrayContainsAny("tags", tags).get()
            .toSuspendFunc()

    suspend fun searchMedicineByName(name: String) =
        firebaseFirestore.collection("medicines")
            .whereGreaterThanOrEqualTo("name", name)
            .whereLessThanOrEqualTo("name", "$name\uffff")
            .get().toSuspendFunc()

    suspend fun search(searchPattern: String): List<Medicine> {
        if (searchPattern.isEmpty())
            return getMedicines()?.toObjects(Medicine::class.java) ?: emptyList()
        return searchMedicineByName(
            searchPattern.trim()
        )?.toObjects(Medicine::class.java) ?: emptyList()
    }

    // wrapper to convert task to coroutine
    suspend fun <TResult : Any> Task<TResult>.toSuspendFunc() = suspendCoroutine<TResult?> {
        addOnCompleteListener { task ->
            if (task.isSuccessful) {
                it.resume(task.result)
            } else {
                it.resumeWithException(task.exception!!)
            }
        }
    }
}
