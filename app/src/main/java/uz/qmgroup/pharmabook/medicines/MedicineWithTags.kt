package uz.qmgroup.pharmabook.medicines

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import uz.qmgroup.pharmabook.database.MedicineTagCrossRef
import uz.qmgroup.pharmabook.tags.TagEntity

data class MedicineWithTags(
    @Embedded val medicine: MedicineEntity,
    @Relation(
        parentColumn = "medicineId",
        entityColumn = "tagId",
        associateBy = Junction(MedicineTagCrossRef::class)
    )
    val tags: List<TagEntity>
)
