package com.example.hseapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hseapp.datamodels.User
import com.example.hseapp.network.RetrofitHelper
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var usersList: ArrayList<User>? = null
    val userLogin: MutableLiveData<Boolean> = MutableLiveData()

    fun getUserlist() {
        viewModelScope.launch {
            val result = RetrofitHelper.getInstance().getUsers()
            if (result.body() != null) {
                usersList = result.body()
            }
        }
    }
    fun validateUser(username: String, password:String) {
        if (usersList != null && usersList!!.size > 0) {
            for (user in usersList!!) {
                if (username.equals(user.email, true) && password.equals(user.password, false)) {
                    userLogin.postValue(true)
                    return
                }
            }
            userLogin.postValue(false)
        }
        else {
            userLogin.postValue(false)
        }
    }


}