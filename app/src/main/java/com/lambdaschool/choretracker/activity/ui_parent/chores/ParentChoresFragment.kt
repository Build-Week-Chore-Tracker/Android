package com.lambdaschool.choretracker.activity.ui_parent.chores

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
import com.lambdaschool.choretracker.activity.ParentMainActivity
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.ChildList
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.model.ChoreList
import com.lambdaschool.choretracker.util.Prefs
import kotlinx.android.synthetic.main.fragment_chores_parent.*
import kotlinx.android.synthetic.main.item_parent_chore.view.*
import kotlinx.android.synthetic.main.list_item_parent_chore.*

class ParentChoresFragment : Fragment() {

    private lateinit var parentChoresViewModel: ParentChoresViewModel
    private var listener: OnParentChoresFragmentInteractionListener? = null
    private var viewAdapterParent: ParentChoreRecyclerViewAdapter? = null
    var prefs: Prefs? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parentChoresViewModel =
            ViewModelProviders.of(this).get(ParentChoresViewModel::class.java)
        return inflater.inflate(R.layout.fragment_chores_parent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = Prefs(context!!)

        var userId = -1

        prefs?.getLoginCredentials()?.let {
            userId = it.user
        }

        fab_add_chore.setOnClickListener {
            listener?.onParentChoresFragmentInteractionListener(Chore(
                ParentMainActivity.ADD_CHORE_KEY,
                "",
                0,
                false,
                "",
                0,
                0
            ))
        }

        parentChoresViewModel.getAllChoresForParentId(userId).observe(this, Observer {
            if (it.isNotEmpty()) {
                it.forEachIndexed { index, t ->
                    if (index == 0) {
                        ChoreList.choreList.clear()
                    }

                    ChoreList.choreList.add(t)

                    if (index == it.size - 1) {
                        viewAdapterParent?.notifyDataSetChanged()
                    }
                }
            } else {
                ChoreList.choreList.clear()
                viewAdapterParent?.notifyDataSetChanged()
            }
        })

        parentChoresViewModel.getAllChildForParentId(userId).observe(this, Observer {
            if (it.isNotEmpty()) {
                it.forEachIndexed { index, t ->
                    if (index == 0) {
                        ChildList.childList.clear()
                    }

                    ChildList.childList.add(t)
                }
            } else {
                ChildList.childList.clear()
            }
        })

        setupRecyclerView(rv_parent_chore_list)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnParentChoresFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnParentChoresFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnParentChoresFragmentInteractionListener {
        fun onParentChoresFragmentInteractionListener(chore: Chore)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        viewAdapterParent = ParentChoreRecyclerViewAdapter(ChoreList.choreList, listener, parentChoresViewModel)
        recyclerView.adapter = viewAdapterParent
    }

    class ParentChoreRecyclerViewAdapter(
        private val values: List<Chore>,
        private val listener: OnParentChoresFragmentInteractionListener?,
        private val viewModel: ParentChoresViewModel
    ) : RecyclerView.Adapter<ParentChoreRecyclerViewAdapter.ViewHolder>() {

        lateinit var context: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_parent_chore, parent, false)
            context = parent.context
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            var childInfo: Child? = null

            ChildList.childList.forEach {
                if (it.child_id == item.child_id) {
                    childInfo = it
                }
            }

            holder.name.text = item.title
            holder.childHexColorButton.text = childInfo?.name ?: ""
            holder.childHexColorButton.setBackgroundColor(Color.parseColor(childInfo?.labelColorHex))

            if (item.childCompleted) {
                holder.card.setCardBackgroundColor(Color.GREEN)
                listener?.onParentChoresFragmentInteractionListener(item)
            }

            holder.card.setOnClickListener {
                listener?.onParentChoresFragmentInteractionListener(item)
            }
        }

        override fun getItemCount(): Int {
            return values.size
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.tv_parent_chore_name
            val childHexColorButton = view.btn_parent_chore_child_color
            val card: CardView = view.cv_parent_chore_card
        }
    }
}
