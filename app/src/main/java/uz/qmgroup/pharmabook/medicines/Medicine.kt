package uz.qmgroup.pharmabook.medicines

import uz.qmgroup.pharmabook.tags.Tag

data class Medicine(
    val id: Long,
    val name: String,
    val vendor: String,
    val tags: List<Tag>? = null,
){
    fun toEntity() = MedicineEntity(
        medicineId = id,
        name = name,
        vendor = vendor,
        refId = ""
    )
}
