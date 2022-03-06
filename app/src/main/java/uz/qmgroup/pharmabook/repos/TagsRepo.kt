package uz.qmgroup.pharmabook.repos

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import uz.qmgroup.pharmabook.database.AppDatabase
import uz.qmgroup.pharmabook.tags.Tag
import uz.qmgroup.pharmabook.tags.TagEntity

class TagsRepo {
    suspend fun getTags(): Flow<List<Tag>> = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.run {
            tagDao().getAll().map {
                it.map { tagEntity -> tagEntity.toTag() }
            }
        } ?: emptyFlow()
    }

    suspend fun searchTagByName(tagName: String) = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.run {
            tagDao().getTagByLabel("%$tagName%").map {
                it.map { tagEntity -> tagEntity.toTag() }.sortedBy { tag ->
                    tag.label.indexOf(tagName)
                }
            }
        }
    }

    suspend fun createTag(tag: Tag) = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.run {
            tagDao().insert(
                TagEntity(
                    refId = "", label = tag.label,
                )
            )
        }
    }

    suspend fun deleteTag(tag: Tag) = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.run {
            tagDao().delete(tag.toEntity())
        }
    }
}