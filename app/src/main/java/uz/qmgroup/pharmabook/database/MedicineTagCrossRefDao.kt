package uz.qmgroup.pharmabook.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MedicineTagCrossRefDao {
    @Insert
    suspend fun insert(medicineTagCrossRef: MedicineTagCrossRef)

    @Insert
    suspend fun insertAll(vararg medicineTagCrossRefs: MedicineTagCrossRef)

    @Delete
    suspend fun delete(medicineTagCrossRef: MedicineTagCrossRef)

    @Query("DELETE FROM MedicineTagCrossRef WHERE medicineId = :medicineId")
    suspend fun deleteAllTagsOfMedicine(medicineId: Long)
}