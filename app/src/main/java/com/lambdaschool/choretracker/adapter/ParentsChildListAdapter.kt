package com.lambdaschool.choretracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.choretracker.R

class ParentsChildListAdapter(val childList: MutableList<Child>) : RecyclerView.Adapter<ParentsChildListAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParentsChildListAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_child_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return childList.size
    }

    override fun onBindViewHolder(holder: ParentsChildListAdapter.CustomViewHolder, position: Int) {

    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

}