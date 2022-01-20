package uz.qmgroup.pharmabook.tags

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TagEntity(
    @PrimaryKey(autoGenerate = true) val tagId: Int = 0,
    val refId: String,
    val label: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
){
    fun toTag(): Tag {
        return Tag(tagId, label)
    }
}