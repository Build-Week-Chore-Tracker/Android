package com.lambdaschool.choretracker.activity.ui_child.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R

class ChildChoresFragment : Fragment() {

    private lateinit var childChoresViewModel: ChildChoresViewModel
    private var listenerChild: OnChildChoresFragmentInteractionListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        childChoresViewModel =
            ViewModelProviders.of(this).get(ChildChoresViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chores_child, container, false)
        /*val textView: TextView = root.findViewById(R.id.tv_child_home)
        childChoresViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnChildChoresFragmentInteractionListener) {
            listenerChild = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnChildChoresFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerChild = null
    }

    interface OnChildChoresFragmentInteractionListener {
        fun onChildChoresFragmentInteractionListener()
    }
}