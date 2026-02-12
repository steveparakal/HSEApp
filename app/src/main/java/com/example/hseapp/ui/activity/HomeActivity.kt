package com.example.hseapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.hseapp.*
import com.example.hseapp.databinding.ActivityHomeBinding
import com.example.hseapp.ui.fragments.CalendarFragment
import com.example.hseapp.ui.fragments.CourseFragment
import com.example.hseapp.ui.fragments.GradesFragment
import com.example.hseapp.ui.fragments.ProfileFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        val calendarFragment = CalendarFragment.newInstance()
        setCurrentFragment(calendarFragment)
        val courseFragment = CourseFragment.newInstance()
        val gradeFragment = GradesFragment.newInstance()
        val userFragment = ProfileFragment.newInstance()

        binding.menubar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.calender -> setCurrentFragment(calendarFragment)
                R.id.course -> setCurrentFragment(courseFragment)
                R.id.grade -> setCurrentFragment(gradeFragment)
                R.id.profile -> setCurrentFragment(userFragment)
            }
            true
        }
    }

    fun setCurrentFragment(fragment: Fragment) {
        val Transaction = supportFragmentManager.beginTransaction()
        Transaction.replace(R.id.homecontainer, fragment, "Menu Item").addToBackStack("Menu Item").commit()
    }
}