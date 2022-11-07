package uz.qmgroup.moneyandbanks.term

data class Term(
    val id: Long,
    val name: String,
    val definition: String,
    val tags: List<String>? = null,
    val synonymIds: List<Long>,
    val synonyms: List<Term>? = null
){
    fun toEntity() = TermEntity(
        termId = id,
        name = name,
        definition = definition,
        tags = tags?.joinToString(";") ?: "",
        synonymIds = synonymIds.joinToString(";")
    )
}
