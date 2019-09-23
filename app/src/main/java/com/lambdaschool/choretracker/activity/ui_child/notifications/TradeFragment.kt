package com.lambdaschool.choretracker.activity.ui_child.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R

class TradeFragment : Fragment() {

    private lateinit var tradeViewModel: TradeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tradeViewModel =
            ViewModelProviders.of(this).get(TradeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_trade_child, container, false)
        /*val textView: TextView = root.findViewById(R.id.tv_child_notifications)
        tradeViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }
}