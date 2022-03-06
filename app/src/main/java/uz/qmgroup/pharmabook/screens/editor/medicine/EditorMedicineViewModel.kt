package uz.qmgroup.pharmabook.screens.editor.medicine

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.qmgroup.pharmabook.medicines.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo
import uz.qmgroup.pharmabook.tags.Tag
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

    val medicineTags = mutableStateListOf<Tag>()

    val medicineDiagnoses = mutableStateListOf<String>()

    private var saving by mutableStateOf(false)

    fun loadMedicine(medicineId: Long) {
        viewModelScope.launch {
            medicine = MedicinesRepo().getMedicine(medicineId)
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
            diagnoses = medicineDiagnoses
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