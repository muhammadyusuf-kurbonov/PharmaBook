package uz.qmgroup.moneyandbanks.screens.editor.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.qmgroup.moneyandbanks.term.Term
import uz.qmgroup.moneyandbanks.repos.TermsRepo

class EditorHomeViewModel: ViewModel() {
    var loading by mutableStateOf(false)
    private set

    val list = mutableStateListOf<Term>()

    fun deleteMedicine(term: Term){
        viewModelScope.launch {
            TermsRepo().deleteTerm(term = term)
            reloadData()
        }
    }

    fun reloadData(){
        viewModelScope.launch {
            loading = true
            list.clear()
            list.addAll(TermsRepo().getTerms())
            loading = false
        }
    }
}