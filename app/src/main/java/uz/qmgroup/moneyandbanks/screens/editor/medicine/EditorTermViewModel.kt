package uz.qmgroup.moneyandbanks.screens.editor.medicine

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.qmgroup.moneyandbanks.term.Term
import uz.qmgroup.moneyandbanks.repos.TermsRepo
import java.util.*

class EditorTermViewModel : ViewModel() {
    var term by mutableStateOf<Term?>(null)
        private set

    var termName by mutableStateOf("")
        private set

    var termDefinition by mutableStateOf("")
        private set

    val tags = mutableStateListOf<String>()

    val synonymTerms = mutableStateListOf<Term>()

    val allTerms = mutableStateListOf<Term>()

    private var saving by mutableStateOf(false)

    fun loadMedicines(){
        viewModelScope.launch {
            allTerms.addAll(TermsRepo().getTerms())
        }
    }

    fun loadMedicine(medicineId: Long) {
        viewModelScope.launch {
            val termsRepo = TermsRepo()
            term = termsRepo.getTerm(medicineId)
            if (term != null){
                val foundMedicine = term!!
                termName = foundMedicine.name
                termDefinition = foundMedicine.definition
                tags.addAll(foundMedicine.tags ?: emptyList())
            }
        }
    }

    fun updateMedicineName(name: String) {
        termName =
            name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    fun updateVendor(producer: String) {
        termDefinition =
            producer.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    fun addTag(tag: String){
        if (tag.isNotEmpty())
            tags.add(tag)
    }

    fun removeTag(tag: String){
        tags.remove(tag)
    }

    fun addAlternative(medicineName: String){
        val medicine = allTerms.find { it.name == medicineName }
        if (medicine != null)
            synonymTerms.add(medicine)
        else
            throw IllegalStateException("Medicine not found")
    }

    fun removeAlternative(medicineName: String){
        val medicine = synonymTerms.find { it.name == medicineName }
        if (medicine != null)
            synonymTerms.remove(medicine)
        else
            throw IllegalStateException("Medicine not found")
    }

    @Composable
    fun isSaveButtonEnabled()
        = termName.isNotEmpty()
            && termDefinition.isNotEmpty()
            && !saving

    fun save(){
        saving = true
        val newTermModels = Term(
            id = term?.id ?: 0L,
            name = termName,
            definition = termDefinition,
            tags = tags.toList(),
            synonymIds = synonymTerms.map { it.id }
        )
        viewModelScope.launch {
            if (term == null)
                TermsRepo().addTerm(newTermModels)
            else
                TermsRepo().updateTerm(newTermModels)
        }
        saving = false
    }
}