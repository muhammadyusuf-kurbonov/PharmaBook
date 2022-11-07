package uz.qmgroup.moneyandbanks.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.qmgroup.moneyandbanks.term.Term
import uz.qmgroup.moneyandbanks.repos.TermsRepo

class MedicineDetailsViewModel: ViewModel() {
    private val _term = MutableStateFlow<Term?>(null)
    val medicine = _term.asStateFlow()

    var isLoading by mutableStateOf(false)
    private set

    fun loadData(id: Long){
        viewModelScope.launch {
            try {
                isLoading = true
                val value = TermsRepo().getTerm(id)
                if (value != null) {
                    _term.tryEmit(value)
                    val alternatives = value.synonymIds.map {
                        async {
                            TermsRepo().getTerm(it)
                        }
                    }.awaitAll().filterNotNull()
                    _term.tryEmit(value.copy(
                        synonyms = alternatives
                    ))
                }
            } finally {
                isLoading = false
            }
        }
    }
}