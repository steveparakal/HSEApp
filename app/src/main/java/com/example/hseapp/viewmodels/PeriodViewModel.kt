package com.example.hseapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hseapp.datamodels.Period

class PeriodViewModel: ViewModel() {
    var periodData: MutableLiveData<ArrayList<Period>> = MutableLiveData()

    fun getPeriod() {
        var periodList = ArrayList<Period>()
        periodList.add(Period("Semester", true))
        periodList.add(Period("Module", false))
        periodList.add(Period("Type", false))
        periodList.add(Period("Subject", false))

        periodData.value = periodList
    }
}