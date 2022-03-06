package uz.qmgroup.pharmabook.medicines

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Keep
@Entity(indices = [
    Index(value = ["name", "tags", "diagnoses"])
])
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true) val medicineId: Long,
    val name: String,
    val vendor: String,
    @ColumnInfo(defaultValue = "0") val positionColumn: Int,
    @ColumnInfo(defaultValue = "0") val positionRow: Int,
    @ColumnInfo(defaultValue = "") val diagnoses: String,
    @ColumnInfo(defaultValue = "") val tags: String
){
    fun toMedicine() = Medicine(
        id = medicineId,
        name = name,
        vendor = vendor,
        positionColumn = positionColumn,
        positionRow = positionRow,
        diagnoses = diagnoses.split(";"),
        tags = tags.split(";")
    )
}
