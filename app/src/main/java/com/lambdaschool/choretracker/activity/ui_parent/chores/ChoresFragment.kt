package com.lambdaschool.choretracker.activity.ui_parent.chores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home_parent, container, false)
        /*val textView: TextView = root.findViewById(R.id.tv_parent_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }
}