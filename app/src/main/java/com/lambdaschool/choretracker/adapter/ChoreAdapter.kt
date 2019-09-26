package com.lambdaschool.choretracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.model.Chore
import kotlinx.android.synthetic.main.chore_item.view.*

class ChoreAdapter(val choreList: MutableList<Chore>) : RecyclerView.Adapter<ChoreAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chore_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return choreList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = choreList[position]

        holder.chore.text = item.title
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chore: TextView = view.tv_chore_item
    }
}