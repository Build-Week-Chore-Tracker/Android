package com.lambdaschool.choretracker.activity.ui_parent.chores

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.ParentMainActivity
import com.lambdaschool.choretracker.model.Chore
import kotlinx.android.synthetic.main.fragment_chores_parent.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var listenerParent: OnParentChoresFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chores_parent, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_add_chore.setOnClickListener {
            listenerParent?.onParentChoresFragmentInteractionListener(Chore(
                ParentMainActivity.ADD_CHORE_KEY,
                "",
                0,
                false,
                "",
                0,
                0
            ))
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnParentChoresFragmentInteractionListener) {
            listenerParent = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnParentChildrenListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerParent = null
    }

    interface OnParentChoresFragmentInteractionListener {
        fun onParentChoresFragmentInteractionListener(chore: Chore)
    }
}