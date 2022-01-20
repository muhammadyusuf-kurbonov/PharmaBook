package uz.qmgroup.pharmabook.repos

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.qmgroup.pharmabook.database.AppDatabase
import uz.qmgroup.pharmabook.database.MedicineTagCrossRef
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
            val medicineEntity = medicineDao().getWithTags(id)
            val medicine = medicineEntity.medicine.toMedicine()
            return@run medicine.copy(
                tags = medicineEntity.tags.map {
                    it.toTag()
                }
            )
        }
    }

    suspend fun addMedicine(medicine: Medicine) = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.apply {
            runInTransaction {
                launch(Dispatchers.IO) {
                    val medicineId =
                        medicineDao().insert(medicine.toEntity())

                    val tagIds = medicine.tags?.map { it.id } ?: emptyList()

                    medicineTagCrossRefDao().insertAll(
                        *tagIds.map {
                            MedicineTagCrossRef(
                                medicineId = medicineId,
                                tagId = it
                            )
                        }.toTypedArray()
                    )
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
                launch {
                    medicineTagCrossRefDao().deleteAllTagsOfMedicine(medicine.id)
                    val tagIds = medicine.tags?.map {
                        async {
                            val tagId = tagDao().insert(it.toEntity())
                            tagId
                        }
                    }?.map {
                        it.await()
                    }

                    tagIds?.let {
                        medicineTagCrossRefDao().insertAll(
                            *tagIds.map {
                                MedicineTagCrossRef(
                                    medicineId = medicine.id,
                                    tagId = it.toInt()
                                )
                            }.toTypedArray()
                        )
                    }
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
                launch {
                    medicineTagCrossRefDao().deleteAllTagsOfMedicine(medicine.id)
                }
            }
        }
    }

    suspend fun getMedicinesByTags(tags: List<String>): List<Medicine> =
        withContext(Dispatchers.IO) {
            AppDatabase.Instance?.run {
                val searchResult = tagDao().findByTags(tags)
                searchResult.flatMap {
                    it.medicines.map { entity -> entity.toMedicine() }
                }
            } ?: emptyList()
        }

    suspend fun searchMedicineByName(name: String): List<Medicine> = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.run {
            medicineDao().getByName("%$name%").map {
                it.toMedicine()
            }
        } ?: emptyList()
    }


    suspend fun search(searchPattern: String): List<Medicine> {
        if (searchPattern.isEmpty())
            return getMedicines()
        return searchMedicineByName(searchPattern)
    }
}
