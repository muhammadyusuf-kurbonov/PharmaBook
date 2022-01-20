package uz.qmgroup.pharmabook.database

import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["medicineId", "tagId"],
    indices = [
        Index("medicineId"),
        Index("tagId")
    ]
)
data class MedicineTagCrossRef(
    val medicineId: Long,
    val tagId: Int
)
