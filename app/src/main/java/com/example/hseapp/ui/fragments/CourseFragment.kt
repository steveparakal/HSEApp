package com.example.hseapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hseapp.ui.adapters.CourseAdapter
import com.example.hseapp.databinding.FragmentCourseBinding
import com.example.hseapp.datamodels.Assignment
import com.example.hseapp.datamodels.ChatInfo
import com.example.hseapp.datamodels.Course
import com.example.hseapp.datamodels.Period
import com.example.hseapp.interfaces.ItemClickListener
import com.example.hseapp.ui.activity.AssignmentInfoActivity
import com.example.hseapp.ui.activity.ChatActivity
import com.example.hseapp.ui.adapters.TeacherAdapter
import com.example.hseapp.utils.Constants
import com.example.hseapp.viewmodels.CourseViewModel

class CourseFragment : Fragment(),ItemClickListener {

    lateinit var selectedCourse: Course
    val courseviewModel by viewModels<CourseViewModel>()
    lateinit var coursebinding: FragmentCourseBinding
    lateinit var courseAdapter : CourseAdapter

    lateinit var teacherAdapter : TeacherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        coursebinding = FragmentCourseBinding.inflate(inflater, container, false)
        return coursebinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        coursebinding.coursetoolbar.toolbarTitle.text = "Courses"
        coursebinding.courserecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,
            false)
        courseAdapter = CourseAdapter(null, this)
        coursebinding.courserecycler.adapter = courseAdapter

        coursebinding.courseTeachingStaff.teacherRecycler.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        teacherAdapter = TeacherAdapter(null)
        coursebinding.courseTeachingStaff.teacherRecycler.adapter = teacherAdapter

        observeLiveData()
        initView()
        courseviewModel.getCourses()
    }

    private fun observeLiveData() {
        courseviewModel.courseData.observe(viewLifecycleOwner) {
            if (it != null && it.size > 0) {
                courseAdapter.names = it
                courseAdapter.notifyDataSetChanged()
                selectedCourse = it[0]
                it[0].isSelected = true
                setInfoData(it[0])
            }

            courseviewModel.loader.observe(viewLifecycleOwner){
                if (it) {
                    coursebinding.loader.visibility = View.VISIBLE
                }
                else {
                    coursebinding.loader.visibility = View.GONE
                }
            }
        }

    }

    fun initView() {
        coursebinding.chats.firstchat.setOnClickListener {
            navigateToChat(selectedCourse.courseInfo.chatlist[0])
        }

        coursebinding.chats.secondchat.setOnClickListener {
            navigateToChat(selectedCourse.courseInfo.chatlist[1])
        }

        coursebinding.chats.thirdchat.setOnClickListener {
            navigateToChat(selectedCourse.courseInfo.chatlist[2])
        }
    }

    private fun navigateToChat(chatInfo: ChatInfo) {
        Intent(requireContext(), ChatActivity::class.java).apply {
            putExtra(Constants.CHAT_INFO, chatInfo)
            startActivity(this)
        }
    }

    override fun onCourseClick(obj: Course) {
        setCoursesData(obj)
        setInfoData(obj)
        selectedCourse = obj
    }

    private fun setCoursesData(obj: Course) {
        for(course in courseviewModel.courseData.value!!) {
            course.isSelected = course == obj
        }
        courseAdapter.names = courseviewModel.courseData.value
        courseAdapter.notifyDataSetChanged()
    }

    private fun setInfoData(obj: Course) {
        if (obj.courseInfo != null) {
            if (obj.courseInfo.description != null) {
                coursebinding.courseDescription.root.visibility = View.VISIBLE
                coursebinding.courseDescription.courseDsc.text = obj.courseInfo.description
            }
            else {
                coursebinding.courseDescription.root.visibility = View.GONE
            }

            if (obj.courseInfo.grading != null) {
                coursebinding.courseGrading.root.visibility = View.VISIBLE
                coursebinding.courseGrading.courseGrding.text = obj.courseInfo.grading
            }
            else {
                coursebinding.courseGrading.root.visibility = View.GONE
            }

            if (obj.courseInfo.teachers != null && obj.courseInfo.teachers.size > 0) {
                coursebinding.courseTeachingStaff.root.visibility = View.VISIBLE
                teacherAdapter.names = obj.courseInfo.teachers
                teacherAdapter.notifyDataSetChanged()
            }
            else {
                coursebinding.courseTeachingStaff.root.visibility = View.GONE
            }

            if(obj.courseInfo.chatlist != null && obj.courseInfo.chatlist.size == 3) {
                coursebinding.chats.root.visibility = View.VISIBLE
                coursebinding.chats.firstchatName.text = obj.courseInfo.chatlist[0].chatname
                coursebinding.chats.firstchatLastmsg.text = obj.courseInfo.chatlist[0].lastmessage
                coursebinding.chats.firstchatLastuser.text = obj.courseInfo.chatlist[0].lastsender
                coursebinding.chats.firstchatMsgcount.text = obj.courseInfo.chatlist[0].count.toString()

                coursebinding.chats.secondchatName.text = obj.courseInfo.chatlist[1].chatname
                coursebinding.chats.secondchatLastmsg.text = obj.courseInfo.chatlist[1].lastmessage
                coursebinding.chats.secondchatLastuser.text = obj.courseInfo.chatlist[1].lastsender
                coursebinding.chats.secondchatMsgcount.text = obj.courseInfo.chatlist[1].count.toString()

                coursebinding.chats.thirdchatName.text = obj.courseInfo.chatlist[2].chatname
                coursebinding.chats.thirdchatLastmsg.text = obj.courseInfo.chatlist[2].lastmessage
                coursebinding.chats.thirdchatLastuser.text = obj.courseInfo.chatlist[2].lastsender
                coursebinding.chats.thirdchatMsgcount.text = obj.courseInfo.chatlist[2].count.toString()
            }
            else {
                coursebinding.chats.root.visibility = View.GONE
            }
        }
    }

    override fun onPeriodClick(obj: Period) {
    }

    override fun onAssignmentClick(obj: Assignment) {
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CourseFragment()
    }
}