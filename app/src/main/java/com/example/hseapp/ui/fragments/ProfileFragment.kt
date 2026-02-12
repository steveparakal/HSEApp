package com.example.hseapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hseapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var profilebinding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        profilebinding = FragmentProfileBinding.inflate(inflater, container, false)
        return profilebinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profilebinding.profiletoolbar.toolbarTitle.text = "Profile"
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment()
    }
}