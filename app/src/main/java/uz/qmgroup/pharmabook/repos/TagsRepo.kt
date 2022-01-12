package uz.qmgroup.pharmabook.repos

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import uz.qmgroup.pharmabook.models.Tag
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class TagsRepo(
    firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val collectionReference = firestore.collection("tags")

    suspend fun getTags() = collectionReference.get().toSuspendFunc()

    suspend fun addTag(tag: Tag){
        val documentReference = collectionReference.document()
        tag.id = documentReference.id
        documentReference.set(tag).toSuspendFunc()
    }

    suspend fun deleteTag(tag: Tag) = collectionReference.document(tag.id).delete().toSuspendFunc()

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