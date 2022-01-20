package uz.qmgroup.pharmabook.medicines

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true) val medicineId: Long,
    val refId: String,
    val name: String,
    val vendor: String,
){
    fun toMedicine() = Medicine(
        id = medicineId,
        name = name,
        vendor = vendor
    )
}
