package com.lambdaschool.choretracker.activity.ui_child.chores

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
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.model.ChoreList
import com.lambdaschool.choretracker.util.Prefs
import kotlinx.android.synthetic.main.item_child_chore.view.*
import kotlinx.android.synthetic.main.list_item_child_chore.*

class ChildChoresFragment : Fragment() {

    private lateinit var childChoresViewModel: ChildChoresViewModel
    private var listener: OnChildChoresFragmentInteractionListener? = null
    private var viewAdapter: ChoreRecyclerViewAdapter? = null
    var prefs: Prefs? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        childChoresViewModel =
            ViewModelProviders.of(this).get(ChildChoresViewModel::class.java)
        return inflater.inflate(R.layout.fragment_chores_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = Prefs(context!!)

        var userId = -1

        prefs?.getLoginCredentials()?.let {
            userId = it.user
        }

        childChoresViewModel.getAllChoresForChildId(userId).observe(this, Observer {
            if (it.isNotEmpty()) {
                it.forEachIndexed { index, t ->
                    if (index == 0) {
                        ChoreList.choreList.clear()
                    }

                    ChoreList.choreList.add(t)

                    if (index == it.size - 1) {
                        viewAdapter?.notifyDataSetChanged()
                    }
                }
            } else {
                ChoreList.choreList.clear()
                viewAdapter?.notifyDataSetChanged()
            }
        })

        setupRecyclerView(rv_child_chore_list)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnChildChoresFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnChildChoresFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnChildChoresFragmentInteractionListener {
        fun onChildChoresFragmentInteractionListener(chore: Chore)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        viewAdapter = ChoreRecyclerViewAdapter(ChoreList.choreList, listener, childChoresViewModel)
        recyclerView.adapter = viewAdapter
    }

    class ChoreRecyclerViewAdapter(
        /*private val parentActivity: ChildChoresFragment,*/
        private val values: List<Chore>,
        private val listener: OnChildChoresFragmentInteractionListener?,
        private val viewModel: ChildChoresViewModel
    ) : RecyclerView.Adapter<ChoreRecyclerViewAdapter.ViewHolder>() {

        lateinit var context: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_child_chore, parent, false)
            context = parent.context
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]

            holder.name.text = item.title
            holder.points.text = "${item.pointValue} Pts"
            if (item.childCompleted) {
                holder.card.setCardBackgroundColor(Color.GREEN)
            }

            holder.card.setOnClickListener {
                listener?.onChildChoresFragmentInteractionListener(item)
            }
        }

        override fun getItemCount(): Int {
            return values.size
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.tv_child_chore_name
            val points: TextView = view.tv_child_chore_points
            val card: CardView = view.cv_child_chore_card
        }
    }
}