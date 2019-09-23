package com.lambdaschool.choretracker.activity.ui_parent.children

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R

class ChildrenFragment : Fragment() {

    private lateinit var childrenViewModel: ChildrenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        childrenViewModel =
            ViewModelProviders.of(this).get(ChildrenViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard_parent, container, false)
        /*val textView: TextView = root.findViewById(R.id.tv_parent_dashboard)
        childrenViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }
}