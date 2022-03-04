package uz.qmgroup.pharmabook.screens.tags.select_dialog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.repos.TagsRepo
import uz.qmgroup.pharmabook.tags.Tag

class TagSelectViewModel : ViewModel() {
    private val _tags = MutableStateFlow<List<Tag>>(emptyList())
    val tags = _tags.asStateFlow()

    var query by mutableStateOf("")
        private set

    var loading by mutableStateOf(false)
        private set

    var excludedTags = emptyList<Tag>()
        set(value) {
            field = value
            loadData()
        }

    fun loadData() {
        viewModelScope.launch {
            try {
                loading = true
                val allTags = if (query.isEmpty() || query.isBlank())
                    TagsRepo().getTags()
                else
                    TagsRepo().searchTagByName(query)
                _tags.emitAll(allTags
                    ?.map {
                        it.filter { tag -> !excludedTags.contains(tag) }
                    } ?: emptyFlow())
            } finally {
                loading = false
            }
        }
    }

    fun updateQuery(newValue: String) {
        this.query = newValue
        loadData()
    }

    fun createTag(tag: String) {
        viewModelScope.launch {
            TagsRepo().createTag(Tag(
                id = 0,
                label = tag,
            ))
        }
    }
}