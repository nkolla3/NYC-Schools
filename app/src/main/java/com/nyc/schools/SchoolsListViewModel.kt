package com.nyc.schools

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyc.schools.model.School
import com.nyc.schools.model.SchoolDetail
import com.nyc.schools.network.SchoolsService
import kotlinx.coroutines.launch

class SchoolsListViewModel : ViewModel() {
    private val TAG: String? = "SchoolsListViewModel"
    private val schoolsService: SchoolsService = SchoolsService()
    private val _schoolsList: MutableLiveData<List<School>> = MutableLiveData()
    val schoolsList: LiveData<List<School>> = _schoolsList
    private val _schoolsDetailsList: MutableLiveData<List<SchoolDetail>> = MutableLiveData()
    val schoolsDetailsList: LiveData<List<SchoolDetail>> = _schoolsDetailsList

    fun getSchools() {
        viewModelScope.launch {
            try {
                schoolsService.getSchools().apply {
                    _schoolsList.value = this
                }
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    fun getSchoolDetails() {
        viewModelScope.launch {
            try {
                schoolsService.getSchoolDetails().apply {
                    _schoolsDetailsList.value = this
                }
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }
}