package com.example.hotrobay.userdata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {
    private val dataStore = app.userDataStore

    // Expose User as Flow for Compose
    val user: StateFlow<User> = dataStore.data
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            User.getDefaultInstance()
        )

    // -------------------
    // Personal info
    // -------------------
    fun updateFirstName(firstName: String) = viewModelScope.launch {
        dataStore.updateData { it.toBuilder().setFirstName(firstName).build() }
    }

    fun updateLastName(lastName: String) = viewModelScope.launch {
        dataStore.updateData { it.toBuilder().setLastName(lastName).build() }
    }

    fun updateBirthday(birthday: String) = viewModelScope.launch {
        dataStore.updateData { it.toBuilder().setBirthday(birthday).build() }
    }

    fun updateNationality(nationality: String) = viewModelScope.launch {
        dataStore.updateData { it.toBuilder().setNationality(nationality).build() }
    }

    fun updateGender(gender: String) = viewModelScope.launch {
        dataStore.updateData { it.toBuilder().setGender(gender).build() }
    }

    fun updateAddress(address: String) = viewModelScope.launch {
        dataStore.updateData { it.toBuilder().setAddress(address).build() }
    }

    // -------------------
    // Passport info
    // -------------------
    fun updatePassportId(passportId: String) = viewModelScope.launch {
        dataStore.updateData { it.toBuilder().setPassportId(passportId).build() }
    }

    fun updatePassportExpiryDate(expiryDate: String) = viewModelScope.launch {
        dataStore.updateData { it.toBuilder().setPassportExpiryDate(expiryDate).build() }
    }

    // -------------------
    // Contact numbers
    // -------------------
    fun addContactNumber(number: String) = viewModelScope.launch {
        dataStore.updateData { current ->
            val updatedList = current.contactNumbersList.toMutableList()
            if (number !in updatedList) {
                updatedList.add(number)
            }
            current.toBuilder()
                .clearContactNumbers()
                .addAllContactNumbers(updatedList)
                .build()
        }
    }

    fun removeContactNumberAt(index: Int) = viewModelScope.launch {
        dataStore.updateData { current ->
            val updatedList = current.contactNumbersList.toMutableList()
            if (index in updatedList.indices) {
                updatedList.removeAt(index)
            }
            current.toBuilder()
                .clearContactNumbers()
                .addAllContactNumbers(updatedList)
                .build()
        }
    }

    fun updateContactNumberAt(index: Int, newNumber: String) = viewModelScope.launch {
        dataStore.updateData { current ->
            val updatedList = current.contactNumbersList.toMutableList()
            if (index in updatedList.indices) {
                updatedList[index] = newNumber
            }
            current.toBuilder()
                .clearContactNumbers()
                .addAllContactNumbers(updatedList)
                .build()
        }
    }

    // -------------------
    // Medical history
    // -------------------
    fun addMedication(medication: String) = viewModelScope.launch {
        dataStore.updateData { current ->
            val newMedical = current.medicalHistory.toBuilder()
                .addMedications(medication)
                .build()
            current.toBuilder().setMedicalHistory(newMedical).build()
        }
    }

    fun addDisease(disease: String) = viewModelScope.launch {
        dataStore.updateData { current ->
            val newMedical = current.medicalHistory.toBuilder()
                .addDiseases(disease)
                .build()
            current.toBuilder().setMedicalHistory(newMedical).build()
        }
    }

    fun addAllergy(allergy: String) = viewModelScope.launch {
        dataStore.updateData { current ->
            val newMedical = current.medicalHistory.toBuilder()
                .addAllergies(allergy)
                .build()
            current.toBuilder().setMedicalHistory(newMedical).build()
        }
    }

    fun removeMedicationAt(index: Int) = viewModelScope.launch {
        dataStore.updateData { current ->
            val updatedList = current.medicalHistory.medicationsList.toMutableList()
            if (index in updatedList.indices) {
                updatedList.removeAt(index)
            }
            val newMedical = current.medicalHistory.toBuilder()
                .clearMedications()
                .addAllMedications(updatedList)
                .build()
            current.toBuilder().setMedicalHistory(newMedical).build()
        }
    }

    fun removeDiseaseAt(index: Int) = viewModelScope.launch {
        dataStore.updateData { current ->
            val updatedList = current.medicalHistory.diseasesList.toMutableList()
            if (index in updatedList.indices) {
                updatedList.removeAt(index)
            }
            val newMedical = current.medicalHistory.toBuilder()
                .clearDiseases()
                .addAllDiseases(updatedList)
                .build()
            current.toBuilder().setMedicalHistory(newMedical).build()
        }
    }

    fun removeAllergyAt(index: Int) = viewModelScope.launch {
        dataStore.updateData { current ->
            val updatedList = current.medicalHistory.allergiesList.toMutableList()
            if (index in updatedList.indices) {
                updatedList.removeAt(index)
            }
            val newMedical = current.medicalHistory.toBuilder()
                .clearAllergies()
                .addAllAllergies(updatedList)
                .build()
            current.toBuilder().setMedicalHistory(newMedical).build()
        }
    }

    fun updateMedicationAt(index: Int, newMedication: String) = viewModelScope.launch {
        dataStore.updateData { current ->
            val updatedList = current.medicalHistory.medicationsList.toMutableList()
            if (index in updatedList.indices) {
                updatedList[index] = newMedication
            }
            val newMedical = current.medicalHistory.toBuilder()
                .clearMedications()
                .addAllMedications(updatedList)
                .build()
            current.toBuilder().setMedicalHistory(newMedical).build()
        }
    }

    fun updateDiseaseAt(index: Int, newDisease: String) = viewModelScope.launch {
        dataStore.updateData { current ->
            val updatedList = current.medicalHistory.diseasesList.toMutableList()
            if (index in updatedList.indices) {
                updatedList[index] = newDisease
            }
            val newMedical = current.medicalHistory.toBuilder()
                .clearDiseases()
                .addAllDiseases(updatedList)
                .build()
            current.toBuilder().setMedicalHistory(newMedical).build()
        }
    }

    fun updateAllergyAt(index: Int, newAllergy: String) = viewModelScope.launch {
        dataStore.updateData { current ->
            val updatedList = current.medicalHistory.allergiesList.toMutableList()
            if (index in updatedList.indices) {
                updatedList[index] = newAllergy
            }
            val newMedical = current.medicalHistory.toBuilder()
                .clearAllergies()
                .addAllAllergies(updatedList)
                .build()
            current.toBuilder().setMedicalHistory(newMedical).build()
        }
    }

    fun updateBloodType(type: String) = viewModelScope.launch {
        dataStore.updateData { prefs ->
            prefs.toBuilder()
                .setMedicalHistory(
                    prefs.medicalHistory.toBuilder()
                        .setBloodType(type)
                        .build()
                )
                .build()
        }
    }

    fun clearMedicalHistory() = viewModelScope.launch {
        dataStore.updateData { current ->
            val newMedical = MedicalHistory.getDefaultInstance()
            current.toBuilder().setMedicalHistory(newMedical).build()
        }
    }
}
