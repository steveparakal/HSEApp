package com.example.hseapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hseapp.datamodels.Grade
import com.example.hseapp.datamodels.StudyUnit
import com.example.hseapp.network.RetrofitHelper
import kotlinx.coroutines.launch

class GradeViewModel: ViewModel() {
    var gradeData: MutableLiveData<ArrayList<StudyUnit>> = MutableLiveData()
    val loader: MutableLiveData<Boolean> = MutableLiveData()

    fun getGrade() {

        viewModelScope.launch {
            loader.postValue(true)
            val gradeResult = RetrofitHelper.getInstance().getGrades()
            if (gradeResult != null) {
                gradeData.postValue(gradeResult.body()?.grades)
                loader.postValue(false)

            }
        }
    }


}