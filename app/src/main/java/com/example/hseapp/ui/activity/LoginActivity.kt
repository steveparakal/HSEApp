package com.example.hseapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.hseapp.R
import com.example.hseapp.databinding.ActivityLoginBinding
import com.example.hseapp.utils.Constants
import com.example.hseapp.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private var roleSelected:String = Constants.STUDENT_ROLE
    private val viewmodel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        initView()
        observeLiveData()
        binding.loginbutton.setOnClickListener {
            viewmodel.validateUser(binding.login.text.toString(), binding.password.text.toString())
        }
    }

    private fun observeLiveData() {
        viewmodel.userLogin.observe(this) {
            if (it) {
                navigateToHome()
            }
            else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun initView() {
        binding.stud.setOnClickListener {
            roleSelected = Constants.STUDENT_ROLE
            setupButton(binding.stud, binding.assist, binding.prof)
        }

        binding.assist.setOnClickListener {
            roleSelected = Constants.ASSISTANT_ROLE
            setupButton(binding.assist, binding.stud, binding.prof)
        }

        binding.prof.setOnClickListener {
            roleSelected = Constants.PROFESSOR_ROLE
            setupButton(binding.prof, binding.assist, binding.stud)
        }
        viewmodel.getUserlist()
    }

    fun setupButton(selectedView:Button, firstView:Button, secondView:Button) {
        selectedView.background = getDrawable(R.drawable.role_background)
        selectedView.setTextColor(getColor(R.color.primary_hse_blue))

        firstView.setBackgroundColor(getColor(R.color.white))
        firstView.setTextColor(getColor(R.color.not_selected))

        secondView.setBackgroundColor(getColor(R.color.white))
        secondView.setTextColor(getColor(R.color.not_selected))
    }

    fun navigateToHome() {

        Intent(this, HomeActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }
}