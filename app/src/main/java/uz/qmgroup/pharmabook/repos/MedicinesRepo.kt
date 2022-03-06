package uz.qmgroup.pharmabook.repos

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.qmgroup.pharmabook.database.AppDatabase
import uz.qmgroup.pharmabook.medicines.Medicine

class MedicinesRepo {

    suspend fun getMedicines(): List<Medicine> = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.run {
            medicineDao().getAll().map {
                it.toMedicine()
            }
        } ?: emptyList()
    }

    suspend fun getMedicine(id: Long) = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.run {
            val medicineEntity = medicineDao().getById(id)
            return@run medicineEntity.toMedicine()
        }
    }

    suspend fun addMedicine(medicine: Medicine) = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.apply {
            runInTransaction {
                launch(Dispatchers.IO) {
                    medicineDao().insert(medicine.toEntity())
                }
            }
        }
    }

    suspend fun updateMedicine(medicine: Medicine) = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.apply {
            runInTransaction {
                launch(Dispatchers.IO) {
                    medicineDao().update(medicine.toEntity())
                }
            }
        }
    }

    suspend fun deleteMedicine(medicine: Medicine) = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.apply {
            runInTransaction {
                launch {
                    medicineDao().delete(medicine.id)
                }
            }
        }
    }

    suspend fun getMedicinesByTags(tags: List<String>): List<Medicine> =
        withContext(Dispatchers.IO) {
            AppDatabase.Instance?.run {
                if (tags.isEmpty())
                    return@run getMedicines()
                return@run medicineDao().getByTag("%${tags[0]}%").map {
                    it.toMedicine()
                }
            } ?: emptyList()
        }

    suspend fun searchMedicineByName(name: String): List<Medicine> = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.run {
            if (name.isEmpty())
                return@run getMedicines()
            medicineDao().getByName("%$name%").map {
                it.toMedicine()
            }
        } ?: emptyList()
    }
}
