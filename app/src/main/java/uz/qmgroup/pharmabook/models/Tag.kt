package uz.qmgroup.pharmabook.models

import androidx.annotation.Keep

@Keep
data class Tag(
    var id: String = "",
    val label: String = "",
)