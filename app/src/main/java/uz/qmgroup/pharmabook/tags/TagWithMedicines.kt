package uz.qmgroup.pharmabook.tags

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import uz.qmgroup.pharmabook.database.MedicineTagCrossRef
import uz.qmgroup.pharmabook.medicines.MedicineEntity

data class TagWithMedicines(
    @Embedded
    val tag: TagEntity,
    @Relation(
        parentColumn = "tagId",
        entityColumn = "medicineId",
        associateBy = Junction(MedicineTagCrossRef::class)
    )
    val medicines: List<MedicineEntity>
)
