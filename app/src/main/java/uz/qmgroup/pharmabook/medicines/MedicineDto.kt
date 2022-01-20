package uz.qmgroup.pharmabook.medicines

import androidx.annotation.Keep

@Keep
data class MedicineDto(
    var id: String = "",
    val name: String = "",
    val producer: String = "",
    val description: String = "",
    val tags: List<String> = listOf(),
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
