package uz.qmgroup.moneyandbanks.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.qmgroup.moneyandbanks.term.Term
import uz.qmgroup.moneyandbanks.repos.TermsRepo

class HomeViewModel : ViewModel() {
    var loading by mutableStateOf(false)
        private set

    var tagSearching by mutableStateOf(false)
        private set

    val searchResults = mutableStateListOf<Term>()

    fun startSearch(searchPattern: String) {
        viewModelScope.launch {
            try {
                loading = true
                searchResults.clear()
                if (searchPattern.startsWith("#"))
                    searchResults
                        .addAll(TermsRepo().getTermsByTags(listOf(searchPattern.drop(1))))
                else
                    searchResults
                        .addAll(TermsRepo().searchTermsByName(searchPattern))
            } finally {
                loading = false
            }
        }
    }

}