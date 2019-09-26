package com.lambdaschool.choretracker.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.ChoreDetailActivity.Companion.CUSTOM_CHORE_REQUEST_CODE
import com.lambdaschool.choretracker.activity.CreateChore
import com.lambdaschool.choretracker.model.ChildList
import com.lambdaschool.choretracker.model.Chore
import kotlinx.android.synthetic.main.chore_item.view.*

class ChoreAdapter(val choreList: MutableList<Chore>) : RecyclerView.Adapter<ChoreAdapter.CustomViewHolder>() {
    val childList = ChildList.childList
    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.chore_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return choreList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = choreList[position]

        holder.chore.text = item.title

        holder.chore.setOnClickListener {
            if (holder.chore.text == "Create your own chore") {
                val intent = Intent(context, CreateChore::class.java)
                (context as Activity).startActivityForResult(intent, CUSTOM_CHORE_REQUEST_CODE)
            } else {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Who would you like this chore assigned to?")
                builder.setItems(childList.forEach {
                    it.name
                }, DialogInterface.OnClickListener( { dialog, which ->

                }))
                builder.create()
            }
        }
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chore: TextView = view.tv_chore_item
    }
}