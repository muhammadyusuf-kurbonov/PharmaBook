package uz.qmgroup.pharmabook.medicines

import androidx.room.*

@Dao
interface MedicineDao {
    @Insert
    suspend fun insert(medicine: MedicineEntity): Long

    @Update
    suspend fun update(medicine: MedicineEntity)

    @Query("DELETE FROM MedicineEntity WHERE medicineId = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM MEDICINEENTITY ORDER BY name")
    suspend fun getAll(): List<MedicineEntity>

    @Query("SELECT * FROM MEDICINEENTITY WHERE medicineId = :id")
    suspend fun getById(id: Long): MedicineEntity

    @Query("SELECT * FROM MEDICINEENTITY WHERE name LIKE :name ORDER BY name")
    suspend fun getByName(name: String): List<MedicineEntity>

    @Transaction
    @Query("SELECT * FROM MEDICINEENTITY WHERE medicineId = :id")
    suspend fun getWithTags(id: Long): MedicineWithTags
}