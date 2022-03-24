package uz.qmgroup.pharmabook.screens.editor.medicine

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.medicines.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo
import java.util.*

class EditorMedicineViewModel : ViewModel() {
    var medicine by mutableStateOf<Medicine?>(null)
        private set

    var medicineName by mutableStateOf("")
        private set

    var medicineVendor by mutableStateOf("")
        private set

    var medicinePosition by mutableStateOf(0 to 0)
        private set

    val medicineTags = mutableStateListOf<String>()

    val medicineDiagnoses = mutableStateListOf<String>()

    val alternativeMedicines = mutableStateListOf<Medicine>()

    val allMedicines = mutableStateListOf<Medicine>()

    private var saving by mutableStateOf(false)

    fun loadMedicines(){
        viewModelScope.launch {
            allMedicines.addAll(MedicinesRepo().getMedicines())
        }
    }

    fun loadMedicine(medicineId: Long) {
        viewModelScope.launch {
            val medicinesRepo = MedicinesRepo()
            medicine = medicinesRepo.getMedicine(medicineId)
            if (medicine != null){
                val foundMedicine = medicine!!
                medicineName = foundMedicine.name
                medicineVendor = foundMedicine.vendor
                medicinePosition = foundMedicine.positionColumn to foundMedicine.positionRow
                medicineTags.addAll(foundMedicine.tags ?: emptyList())
                medicineDiagnoses.addAll(foundMedicine.diagnoses)
            }
        }
    }

    fun updateMedicineName(name: String) {
        medicineName =
            name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    fun updateVendor(producer: String) {
        medicineVendor =
            producer.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    fun updateMedicinePositionRow(row: String) {
        medicinePosition = try {
            medicinePosition.first to row.toInt()
        } catch (e: NumberFormatException){
            medicinePosition.first to 0
        }
    }

    fun updateMedicinePositionColumn(col: String) {
        medicinePosition = try {
            col.toInt() to medicinePosition.second
        } catch (e: NumberFormatException) {
            0 to medicinePosition.second
        }
    }

    fun addTag(tag: String){
        if (tag.isNotEmpty())
            medicineTags.add(tag)
    }

    fun removeTag(tag: String){
        medicineTags.remove(tag)
    }

    fun addAlternative(medicineName: String){
        val medicine = allMedicines.find { it.name == medicineName }
        if (medicine != null)
            alternativeMedicines.add(medicine)
        else
            throw IllegalStateException("Medicine not found")
    }

    fun removeAlternative(medicineName: String){
        val medicine = alternativeMedicines.find { it.name == medicineName }
        if (medicine != null)
            alternativeMedicines.remove(medicine)
        else
            throw IllegalStateException("Medicine not found")
    }

    @Composable
    fun isSaveButtonEnabled()
        = medicineName.isNotEmpty()
            && medicineVendor.isNotEmpty()
            && medicineDiagnoses.isNotEmpty()
            && !saving

    fun save(){
        saving = true
        val newMedicineModels = Medicine(
            id = medicine?.id ?: 0L,
            name = medicineName,
            vendor = medicineVendor,
            positionColumn = medicinePosition.first,
            positionRow = medicinePosition.second,
            tags = medicineTags.toList(),
            diagnoses = medicineDiagnoses,
            alternativeIds = alternativeMedicines.map { it.id }
        )
        viewModelScope.launch {
            if (medicine == null)
                MedicinesRepo().addMedicine(newMedicineModels)
            else
                MedicinesRepo().updateMedicine(newMedicineModels)
        }
        saving = false
    }
}