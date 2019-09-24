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
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.ChildMainActivity
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.model.ChoreList
import kotlinx.android.synthetic.main.child_chore_item.view.*
import kotlinx.android.synthetic.main.child_chore_item_list.*
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
        val root = inflater.inflate(R.layout.fragment_chores_child, container, false)
        /*val textView: TextView = root.findViewById(R.id.tv_child_home)
        childChoresViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        fun onChildChoresFragmentInteractionListener(entry: Chore, longPress: Boolean)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        viewAdapter = ChoreRecyclerViewAdapter(ChoreList.choreList/*<-- temporary*/, listener)
        recyclerView.adapter = viewAdapter
    }

    class ChoreRecyclerViewAdapter(
        /*private val parentActivity: ChildChoresFragment,*/
        private val values: List<Chore>,
        private val listener: OnChildChoresFragmentInteractionListener?
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