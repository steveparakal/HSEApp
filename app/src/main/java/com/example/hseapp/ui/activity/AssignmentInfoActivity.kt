package com.example.hseapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.hseapp.R
import com.example.hseapp.databinding.ActivityAssignmentInfoBinding
import com.example.hseapp.datamodels.Assignment
import com.example.hseapp.utils.Constants

class AssignmentInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityAssignmentInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_assignment_info)
        binding.submissionToolbar.backButton.setOnClickListener {
            onBackPressed()
        }
        val assignment: Assignment = intent.getSerializableExtra(Constants.ASSIGNMENT_INFO) as Assignment
        binding.submissionToolbar.toolbarTitle.text = assignment.name
        binding.submissionInfo.courseName.text = assignment.subject
        binding.submissionInfo.assignmentName.text = assignment.name
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}