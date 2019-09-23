package com.lambdaschool.choretracker.activity.ui_child.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R

class ChoresFragment : Fragment() {

    private lateinit var choresViewModel: ChoresViewModel
    private var listener: OnChoresFragmentInteractionListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        choresViewModel =
            ViewModelProviders.of(this).get(ChoresViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chores_child, container, false)
        /*val textView: TextView = root.findViewById(R.id.tv_child_home)
        choresViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnChoresFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnChoresFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnChoresFragmentInteractionListener {
        fun onChoresFragmentInteractionListener()
    }
}