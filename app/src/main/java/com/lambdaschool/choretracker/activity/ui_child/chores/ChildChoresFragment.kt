package com.lambdaschool.choretracker.activity.ui_child.chores

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.graphics.ColorUtils
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.ChildMainActivity
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.model.ChoreList
import kotlinx.android.synthetic.main.child_chore_item.view.*
import kotlinx.android.synthetic.main.child_chore_item_list.*
import kotlinx.android.synthetic.main.fragment_chores_child.*
import kotlinx.android.synthetic.main.fragment_registration.*
import lecho.lib.hellocharts.view.PieChartView

class ChildChoresFragment : Fragment() {

    private lateinit var childChoresViewModel: ChildChoresViewModel
    private var listener: OnChildChoresFragmentInteractionListener? = null
    private var viewAdapter: ChoreRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        childChoresViewModel =
            ViewModelProviders.of(this).get(ChildChoresViewModel::class.java)

        childChoresViewModel.getAllChoresForChildId().observe(this, Observer {
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

        return inflater.inflate(R.layout.fragment_chores_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add_chore.setOnClickListener {
            childChoresViewModel.createChore(Chore("Take trash out", "Do what you're told", 9000, false, 0))
        }

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
        fun onChildChoresFragmentInteractionListener(chore: Chore, longPress: Boolean)
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
                .inflate(R.layout.child_chore_item, parent, false)
            context = parent.context
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]

            holder.name.text = item.title
            holder.points.text = "${item.pointValue} Pts"

            holder.card.setOnClickListener {
                listener?.onChildChoresFragmentInteractionListener(item, false)
            }

            holder.card.setOnLongClickListener {
                listener?.onChildChoresFragmentInteractionListener(item, true)
                viewModel.deleteChore(item)
                true
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