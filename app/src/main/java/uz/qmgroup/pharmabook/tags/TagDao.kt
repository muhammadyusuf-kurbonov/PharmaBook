package uz.qmgroup.pharmabook.tags

import androidx.room.*

@Dao
interface TagDao {
    @Insert
    suspend fun insert(tag: TagEntity): Long

    @Update
    suspend fun update(tag: TagEntity)

    @Delete
    suspend fun delete(tag: TagEntity)

    @Query("SELECT * FROM TagEntity WHERE tagId = :id")
    suspend fun getById(id: Long): TagEntity

    @Query("SELECT * FROM TagEntity")
    suspend fun getAll(): List<TagEntity>

    @Query("SELECT * FROM TagEntity WHERE label IN (:ids)")
    suspend fun findByTags(ids: List<String>): List<TagWithMedicines>

}