package com.lambdaschool.choretracker.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.ChildList
import com.lambdaschool.choretracker.model.Chore
import kotlinx.android.synthetic.main.list_item_parent_child_detail_chore.view.*

class ParentChildDetailChoreListAdapter(val childChoreList: List<Chore>) :
    RecyclerView.Adapter<ParentChildDetailChoreListAdapter.CustomViewHolder>() {

    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.list_item_parent_child_detail_chore, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return childChoreList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val chore = childChoreList[position]
        var childInfo: Child? = null

        ChildList.childList.forEach {
            if (it.child_id == chore.child_id) {
                childInfo = it
            }
        }

        holder.chore.text = chore.title
        holder.points.text = chore.pointValue.toString()
        holder.cardview.setCardBackgroundColor(Color.parseColor(childInfo?.labelColorHex))
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout: ConstraintLayout = view.constraint_parent_child_detail_chore_layout
        val chore: TextView = view.tv_parent_child_detail_chore
        val points: TextView = view.tv_parent_child_detail_chore_points
        val cardview: CardView = view.cardview_parent_child_detail_chore
    }
}