package com.lambdaschool.choretracker.activity.ui_parent.children

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.ParentMainActivity
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.ChildList
import com.lambdaschool.choretracker.util.Prefs
import kotlinx.android.synthetic.main.fragment_dashboard_parent.*
import kotlinx.android.synthetic.main.item_parent_child.view.*
import kotlinx.android.synthetic.main.list_item_parent_child.*
import lecho.lib.hellocharts.view.PieChartView

class ChildrenFragment : Fragment() {

    private lateinit var childrenViewModel: ChildrenViewModel
    private var listenerParent: OnParentChildrenListFragmentInteractionListener? = null
    private var viewAdapter: ParentsChildrenViewAdapter? = null
    var prefs: Prefs? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        childrenViewModel =
            ViewModelProviders.of(this).get(ChildrenViewModel::class.java)

        prefs = Prefs(context!!)

        val root = inflater.inflate(R.layout.fragment_dashboard_parent, container, false)
        return root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginCreds = prefs?.getLoginCredentials()
        var userId = -1

        loginCreds?.user?.let {
            userId = it
        }

        childrenViewModel.getAllChildForParentId(userId).observe(this, Observer {
            if (it.isNotEmpty()) {
                it.forEachIndexed { index, child ->
                    if (index == 0) {
                        ChildList.childList.clear()
                    }

                    ChildList.childList.add(child)

                    if (index == it.size - 1) {
                        viewAdapter?.notifyDataSetChanged()
                    }
                }
            } else {
                ChildList.childList.clear()
                viewAdapter?.notifyDataSetChanged()
            }
        })
        setupRecyclerView(recyclerview_children)

        fab_add_child.setOnClickListener {
            listenerParent?.onParentChildrenListFragmentInteractionListener(
                Child(
                    ParentMainActivity.ADD_CHILD_KEY,
                    "",
                    0,
                    0,
                    "",
                    -1
                ))
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnParentChildrenListFragmentInteractionListener) {
            listenerParent = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnParentChildrenListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerParent = null
    }

    interface OnParentChildrenListFragmentInteractionListener {
        fun onParentChildrenListFragmentInteractionListener(child: Child)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(this.context)
        viewAdapter = ParentsChildrenViewAdapter(ChildList.childList, listenerParent)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = viewAdapter
    }

    class ParentsChildrenViewAdapter(
        /*private val parentActivity: ChildChoresFragment,*/
        private val childrenList: List<Child>,
        private val listener: OnParentChildrenListFragmentInteractionListener?
    ) : RecyclerView.Adapter<ParentsChildrenViewAdapter.ViewHolder>() {

        lateinit var context: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_parent_child, parent, false)
            context = parent.context
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val child = childrenList[position]

            holder.name.text = child.name
            holder.card.setCardBackgroundColor(Color.parseColor(child.labelColorHex))


            holder.card.setOnClickListener {
                listener?.onParentChildrenListFragmentInteractionListener(child)
            }

            /*holder.card.setOnLongClickListener {
                listener?.onParentChildrenListFragmentInteractionListener(item, true)
                true
            }*/
        }

        override fun getItemCount(): Int {
            return childrenList.size
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.tv_parent_child_view_name
            val card: CardView = view.cardview_parent_child_list
            val pieChart: PieChartView = view.pie_chart
        }
    }
}