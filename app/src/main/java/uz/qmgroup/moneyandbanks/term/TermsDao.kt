package uz.qmgroup.moneyandbanks.term

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TermsDao {
    @Insert
    suspend fun insert(medicine: TermEntity): Long

    @Update
    suspend fun update(medicine: TermEntity)

    @Query("DELETE FROM TermEntity WHERE termId = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM TermEntity ORDER BY name")
    suspend fun getAll(): List<TermEntity>

    @Query("SELECT * FROM TermEntity ORDER BY name")
    fun lookAll(): LiveData<List<TermEntity>>

    @Query("SELECT * FROM TermEntity WHERE termId = :id")
    suspend fun getById(id: Long): TermEntity

    @Query("SELECT * FROM TermEntity WHERE name LIKE :name ORDER BY name")
    suspend fun searchByName(name: String): List<TermEntity>

    @Query("SELECT * FROM TermEntity WHERE tags LIKE :tag ORDER BY name")
    suspend fun getByTag(tag: String): List<TermEntity>


}