package uz.qmgroup.moneyandbanks.term

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Keep
@Entity(
    indices = [Index(value = ["name", "tags"])]
)
data class TermEntity(
    @PrimaryKey(autoGenerate = true) val termId: Long,
    val name: String,
    val definition: String,
    @ColumnInfo(defaultValue = "") val tags: String,
    @ColumnInfo(defaultValue = "") val synonymIds: String,
) {
    fun toTerm() = Term(id = termId,
        name = name,
        definition = definition,
        tags = tags.split(";"),
        synonymIds = synonymIds.split(";").filter { it.isNotEmpty() }.map { it.toLong() })
}
