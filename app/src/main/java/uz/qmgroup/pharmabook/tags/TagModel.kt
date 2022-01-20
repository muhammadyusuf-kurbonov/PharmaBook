package uz.qmgroup.pharmabook.tags

import androidx.annotation.Keep

@Keep
data class TagModel(
    var id: String = "",
    val label: String = "",
)