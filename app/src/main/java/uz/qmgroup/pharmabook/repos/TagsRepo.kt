package uz.qmgroup.pharmabook.repos

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.qmgroup.pharmabook.database.AppDatabase
import uz.qmgroup.pharmabook.tags.Tag
import uz.qmgroup.pharmabook.tags.TagEntity

class TagsRepo() {
    suspend fun getTags(): List<Tag> = withContext(Dispatchers.IO){
        AppDatabase.Instance?.run {
            tagDao().getAll().map { it.toTag() }
        } ?: emptyList()
    }

    suspend fun createTag(tag: Tag) = withContext(Dispatchers.IO){
        AppDatabase.Instance?.run {
            tagDao().insert(
                TagEntity(
                    refId = "", label = tag.label,
                )
            )
        }
    }

    suspend fun deleteTag(tag: Tag) = withContext(Dispatchers.IO){
        AppDatabase.Instance?.run {
            tagDao().delete(tag.toEntity())
        }
    }
}