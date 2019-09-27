package com.lambdaschool.choretracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.model.Chore
import kotlinx.android.synthetic.main.list_item_parent_child_detail_chore.view.*
import org.w3c.dom.Text

class ParentChildDetailChoreListAdapter(val childChoreList: MutableList<Chore>) : RecyclerView.Adapter<ParentChildDetailChoreListAdapter.CustomViewHolder>() {
    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_parent_child_detail_chore, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return childChoreList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val chore = childChoreList[position]
        holder.chore.text = chore.title
        holder.points.text = chore.pointValue.toString()
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout: LinearLayout = view.ll_parent_child_detail_chore_layout
        val chore: TextView = view.tv_parent_child_detail_chore
        val points: TextView = view.tv_parent_child_detail_chore_points
    }
}