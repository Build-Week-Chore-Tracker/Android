package com.lambdaschool.choretracker.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.ParentChoreDetailActivity
import com.lambdaschool.choretracker.activity.ParentStandardChoreListActivity
import com.lambdaschool.choretracker.model.Chore
import kotlinx.android.synthetic.main.item_parent_standard_chore.view.*

class StandardChoreListRecyclerViewAdapter(val choreList: MutableList<Chore>) :
    RecyclerView.Adapter<StandardChoreListRecyclerViewAdapter.CustomViewHolder>() {

    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(context)
                .inflate(R.layout.item_parent_standard_chore, parent, false)

        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return choreList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = choreList[position]

        /*if (position == 0) {
            holder.choreCard.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.secondaryLightColor
                )
            )
        }*/

        holder.chore.text = item.title
        holder.choreCard.setOnClickListener {
            val intent = Intent(context, ParentChoreDetailActivity::class.java)
            intent.putExtra(ParentStandardChoreListActivity.PARENT_CHORE_DETAIL_KEY, item)
            (context as Activity).startActivityForResult(
                intent,
                ParentStandardChoreListActivity.PARENT_CHORE_DETAIL_CODE
            )
        }

        holder.choreCard.setOnLongClickListener {
            Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
            true
        }
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chore: TextView = view.tv_chore_item
        val choreCard: CardView = view.cv_standard_chore
    }
}