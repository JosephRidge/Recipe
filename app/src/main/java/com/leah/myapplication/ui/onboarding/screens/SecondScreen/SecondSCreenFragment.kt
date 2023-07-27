package com.leah.myapplication.ui.onboarding.screens.SecondScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leah.myapplication.R

class SecondSCreenFragment : Fragment() {

    companion object {
        fun newInstance() = SecondSCreenFragment()
    }

    private lateinit var viewModel: SecondSCreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second_s_creen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SecondSCreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}