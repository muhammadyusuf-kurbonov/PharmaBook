package uz.qmgroup.moneyandbanks.repos

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.qmgroup.moneyandbanks.database.AppDatabase
import uz.qmgroup.moneyandbanks.term.Term

class TermsRepo {

    suspend fun getTerms(): List<Term> = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.run {
            medicineDao().getAll().map {
                it.toTerm()
            }
        } ?: emptyList()
    }

    suspend fun getTerm(id: Long) = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.run {
            val medicineEntity = medicineDao().getById(id)
            return@run medicineEntity.toTerm()
        }
    }

    suspend fun addTerm(term: Term) = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.apply {
            runInTransaction {
                launch(Dispatchers.IO) {
                    medicineDao().insert(term.toEntity())
                }
            }
        }
    }

    suspend fun updateTerm(term: Term) = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.apply {
            runInTransaction {
                launch(Dispatchers.IO) {
                    medicineDao().update(term.toEntity())
                }
            }
        }
    }

    suspend fun deleteTerm(term: Term) = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.apply {
            runInTransaction {
                launch {
                    medicineDao().delete(term.id)
                }
            }
        }
    }

    suspend fun getTermsByTags(tags: List<String>): List<Term> =
        withContext(Dispatchers.IO) {
            AppDatabase.Instance?.run {
                if (tags.isEmpty())
                    return@run getTerms()
                return@run medicineDao().getByTag("%${tags[0]}%").map {
                    it.toTerm()
                }
            } ?: emptyList()
        }

    suspend fun searchTermsByName(name: String): List<Term> = withContext(Dispatchers.IO) {
        AppDatabase.Instance?.run {
            if (name.isEmpty())
                return@run getTerms()
            medicineDao().searchByName("%$name%").map {
                it.toTerm()
            }
        } ?: emptyList()
    }
}
