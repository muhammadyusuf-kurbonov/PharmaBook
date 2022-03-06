package uz.qmgroup.pharmabook.tags

import androidx.room.*
import kotlinx.coroutines.flow.Flow

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
    fun getAll(): Flow<List<TagEntity>>

    @Transaction
    @Query("SELECT * FROM TagEntity WHERE label IN (:ids)")
    suspend fun findByTags(ids: List<String>): List<TagWithMedicines>

    @Query("SELECT * FROM TagEntity WHERE label LIKE :label")
    fun getTagByLabel(label: String): Flow<List<TagEntity>>
}