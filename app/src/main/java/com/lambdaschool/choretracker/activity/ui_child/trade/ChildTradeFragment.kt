package com.lambdaschool.choretracker.activity.ui_child.trade

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R

class ChildTradeFragment : Fragment() {

    private lateinit var childTradeViewModel: ChildTradeViewModel
    private var listenerChild: OnChildTradeFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        childTradeViewModel =
            ViewModelProviders.of(this).get(ChildTradeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_trade_child, container, false)
        /*val textView: TextView = root.findViewById(R.id.tv_child_notifications)
        childTradeViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnChildTradeFragmentInteractionListener) {
            listenerChild = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnParentChildrenListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerChild = null
    }

    interface OnChildTradeFragmentInteractionListener {
        fun onChildTradeFragmentInteractionListener()
    }
}