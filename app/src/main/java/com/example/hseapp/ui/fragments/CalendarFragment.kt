package com.example.hseapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hseapp.R
import com.example.hseapp.databinding.FragmentCalendarBinding
import com.example.hseapp.datamodels.Assignment
import com.example.hseapp.datamodels.Course
import com.example.hseapp.datamodels.Period
import com.example.hseapp.interfaces.ItemClickListener
import com.example.hseapp.ui.activity.AssignmentInfoActivity
import com.example.hseapp.ui.adapters.DayAdapter
import com.example.hseapp.utils.Constants
import com.example.hseapp.viewmodels.CalendarViewModel


class CalendarFragment : Fragment(), ItemClickListener {
    val dayviewModel by viewModels<CalendarViewModel>()
    lateinit var dayAdapter: DayAdapter
    lateinit var calendarbinding: FragmentCalendarBinding

    var isTimetableSelected: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        calendarbinding = FragmentCalendarBinding.inflate(inflater,
            container, false)
        return calendarbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        calendarbinding.timetable.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        dayAdapter = DayAdapter(null, this)
        calendarbinding.timetable.adapter = dayAdapter

        observeLiveData()
        dayviewModel.getTimetable()
        setupToolbar()
    }

    private fun setupToolbar() {
        calendarbinding.calendertoolbar.toolbarTitle.text = Constants.CALENDAR_TIMETABLE
        calendarbinding.calendertoolbar.secondTitle.text = Constants.CALENDAR_ASSIGNMENT

        calendarbinding.calendertoolbar.toolbarTitle.setOnClickListener {
            onTitleClick()
        }

        calendarbinding.calendertoolbar.secondTitle.setOnClickListener {
            onSecondTitleClick()
        }
    }

    private fun onSecondTitleClick() {
        isTimetableSelected = !isTimetableSelected
        if(isTimetableSelected) {
            calendarbinding.calendertoolbar.toolbarTitle.text = Constants.CALENDAR_TIMETABLE
            calendarbinding.calendertoolbar.secondTitle.text = Constants.CALENDAR_ASSIGNMENT
            dayviewModel.getTimetable()
        }
        else {
            calendarbinding.calendertoolbar.toolbarTitle.text = Constants.CALENDAR_ASSIGNMENT
            calendarbinding.calendertoolbar.secondTitle.text =  Constants.CALENDAR_TIMETABLE
            dayviewModel.getAssignments()
        }
        calendarbinding.calendertoolbar.toolbarTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0, 0, R.drawable.ic_arrow_side, 0)
        calendarbinding.calendertoolbar.secondTitle.visibility = View.GONE
    }

    private fun onTitleClick() {
        if(calendarbinding.calendertoolbar.secondTitle.isVisible) {
            calendarbinding.calendertoolbar.secondTitle.visibility = View.GONE
            calendarbinding.calendertoolbar.toolbarTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0, 0, R.drawable.ic_arrow_side, 0)
        }
        else {
            calendarbinding.calendertoolbar.secondTitle.visibility = View.VISIBLE
            calendarbinding.calendertoolbar.toolbarTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0, 0, R.drawable.ic_arrow_down, 0)
        }

    }


    private fun observeLiveData() {
        dayviewModel.timetableData.observe(viewLifecycleOwner) {
            dayAdapter.names = it
            dayAdapter.notifyDataSetChanged()
        }
        dayviewModel.loader.observe(viewLifecycleOwner){
            if (it) {
                calendarbinding.loader.visibility = View.VISIBLE
            }
            else {
                calendarbinding.loader.visibility = View.GONE
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CalendarFragment()
    }

    override fun onCourseClick(obj: Course) {
    }

    override fun onPeriodClick(obj: Period) {
    }

    override fun onAssignmentClick(obj: Assignment) {

        Intent(requireContext(), AssignmentInfoActivity::class.java).apply {
            putExtra(Constants.ASSIGNMENT_INFO, obj)
            startActivity(this)
        }
    }

}