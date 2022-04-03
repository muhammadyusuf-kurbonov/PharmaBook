package uz.qmgroup.pharmabook.components.autocomplete

interface AutoCompleteEntity {
    fun filter(query: String): Boolean
}