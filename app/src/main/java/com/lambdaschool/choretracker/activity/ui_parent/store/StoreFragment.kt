package com.lambdaschool.choretracker.activity.ui_parent.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications_parent, container, false)
        /*val textView: TextView = root.findViewById(R.id.tv_parent_notifications)
        notificationsViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }
}