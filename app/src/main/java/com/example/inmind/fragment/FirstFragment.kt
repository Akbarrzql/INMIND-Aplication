package com.example.inmind.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inmind.R

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)


        return view
    }

    companion object {
        fun newInstance(): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}