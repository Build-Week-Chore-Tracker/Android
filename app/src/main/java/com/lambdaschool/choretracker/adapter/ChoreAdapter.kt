package com.lambdaschool.choretracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.model.Chore
import kotlinx.android.synthetic.main.item_parent_standard_chore.view.*

class ChoreAdapter(val choreList: MutableList<Chore>) : RecyclerView.Adapter<ChoreAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_parent_standard_chore, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return choreList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = choreList[position]

        // capture intent

        holder.chore.text = item.title
        holder.choreCard.setOnClickListener {
            //return intent
        }
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chore: TextView = view.tv_chore_item
        val choreCard: CardView = view.cv_standard_chore
    }
}