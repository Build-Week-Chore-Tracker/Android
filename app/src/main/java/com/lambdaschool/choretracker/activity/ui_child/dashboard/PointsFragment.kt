package com.lambdaschool.choretracker.activity.ui_child.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R

class PointsFragment : Fragment() {

    private lateinit var pointsViewModel: PointsViewModel
    private var listener: OnPointsFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pointsViewModel =
            ViewModelProviders.of(this).get(PointsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_points_child, container, false)
        /*val textView: TextView = root.findViewById(R.id.tv_child_dashboard)
        pointsViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPointsFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnPointsFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnPointsFragmentInteractionListener {
        fun onPointsFragmentInteractionListener()
    }
}