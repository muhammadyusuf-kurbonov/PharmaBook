package uz.qmgroup.pharmabook.tags

import uz.qmgroup.pharmabook.medicines.Medicine

data class Tag(
    val id: Int,
    val label: String,
    val medicines: List<Medicine>? = null
){
    fun toEntity() = TagEntity(
        label = label,
        refId = ""
    )
}
