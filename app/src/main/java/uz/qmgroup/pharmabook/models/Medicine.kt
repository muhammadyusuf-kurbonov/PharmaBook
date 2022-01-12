package uz.qmgroup.pharmabook.models

import androidx.annotation.Keep

@Keep
data class Medicine(
    var id: String = "",
    val name: String = "",
    val producer: String = "",
    val description: String = "",
    val tags: List<String> = listOf(),
)
