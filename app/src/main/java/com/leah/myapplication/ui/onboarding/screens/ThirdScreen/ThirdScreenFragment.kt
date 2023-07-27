package com.leah.myapplication.ui.onboarding.screens.ThirdScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leah.myapplication.R

class ThirdScreenFragment : Fragment() {

    companion object {
        fun newInstance() = ThirdScreenFragment()
    }

    private lateinit var viewModel: ThirdScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ThirdScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}