package com.example.hseapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hseapp.datamodels.Assignment
import com.example.hseapp.datamodels.Day
import com.example.hseapp.datamodels.Timetable
import com.example.hseapp.network.RetrofitHelper
import kotlinx.coroutines.launch

class CalendarViewModel : ViewModel() {
    var timetableData: MutableLiveData<ArrayList<Day>> = MutableLiveData()
    val loader: MutableLiveData<Boolean> = MutableLiveData()

    fun getTimetable() {

        viewModelScope.launch {
            loader.postValue(true)
            val timetableResult = RetrofitHelper.getInstance().getTimetable()

            if (timetableResult != null) {
                timetableData.postValue(timetableResult.body()?.days)
                loader.postValue(false )
            }
        }
    }

    fun getAssignments() {

        viewModelScope.launch {
            loader.postValue(true)
            val assignmentResult = RetrofitHelper.getInstance().getAssignments()

            if (assignmentResult != null) {
                timetableData.postValue(assignmentResult.body()?.days)
                loader.postValue(false )
            }
        }
    }
}