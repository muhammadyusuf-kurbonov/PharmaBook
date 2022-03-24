package uz.qmgroup.pharmabook.medicines

data class Medicine(
    val id: Long,
    val name: String,
    val vendor: String,
    val positionColumn: Int,
    val positionRow: Int,
    val tags: List<String>? = null,
    val diagnoses: List<String>,
    val alternativeIds: List<Long>,
    val alternatives: List<Medicine>? = null
){
    fun toEntity() = MedicineEntity(
        medicineId = id,
        name = name,
        vendor = vendor,
        positionColumn = positionColumn,
        positionRow = positionRow,
        diagnoses = diagnoses.joinToString(";"),
        tags = tags?.joinToString(";") ?: "",
        alternativeIds = alternativeIds.joinToString(";")
    )
}
