package com.example.hseapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hseapp.databinding.FragmentGradesBinding
import com.example.hseapp.datamodels.Assignment
import com.example.hseapp.datamodels.Course
import com.example.hseapp.datamodels.Period
import com.example.hseapp.interfaces.ItemClickListener
import com.example.hseapp.ui.adapters.PeriodAdapter
import com.example.hseapp.ui.adapters.StudyUnitAdapter
import com.example.hseapp.viewmodels.GradeViewModel
import com.example.hseapp.viewmodels.PeriodViewModel

class GradesFragment : Fragment(), ItemClickListener {
    val periodviewModel by viewModels<PeriodViewModel>()
    lateinit var gradebinding: FragmentGradesBinding
    lateinit var periodAdapter: PeriodAdapter

    val gradeviewModel by viewModels<GradeViewModel>()
    lateinit var studyunitadapter : StudyUnitAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        gradebinding = FragmentGradesBinding.inflate(inflater, container, false)
        return gradebinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        gradebinding.coursetoolbar.toolbarTitle.text = "Grades"
        gradebinding.periodrecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,
            false)
        periodAdapter = PeriodAdapter(null, this)
        gradebinding.periodrecycler.adapter = periodAdapter

        gradebinding.graderecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,
        false)
        studyunitadapter = StudyUnitAdapter(null)
        gradebinding.graderecycler.adapter = studyunitadapter

        observeLiveData()

        periodviewModel.getPeriod()
        gradeviewModel.getGrade()
    }

    private fun observeLiveData() {
        periodviewModel.periodData.observe(viewLifecycleOwner) {
            periodAdapter.names = it
            periodAdapter.notifyDataSetChanged()
        }

        gradeviewModel.gradeData.observe(viewLifecycleOwner) {
            studyunitadapter.names = it
            studyunitadapter.notifyDataSetChanged()
        }

            gradeviewModel.loader.observe(viewLifecycleOwner){
                if (it) {
                    gradebinding.loader.visibility = View.VISIBLE
                }
                else {
                    gradebinding.loader.visibility = View.GONE
                }
            }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GradesFragment()
    }

    override fun onCourseClick(obj: Course) {
    }

    override fun onPeriodClick(obj: Period) {
        //Toast.makeText(activity, "Period Clicked", Toast.LENGTH_SHORT).show()
        setPeriodData(obj)
    }

    private fun setPeriodData(obj: Period) {
        for(period in periodviewModel.periodData.value!!) {
            period.isSelected = period == obj
        }
        periodAdapter.names = periodviewModel.periodData.value
        periodAdapter.notifyDataSetChanged()
    }

    override fun onAssignmentClick(obj: Assignment) {
    }
}