package com.lambdaschool.choretracker.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.adapter.StandardChoreListRecyclerViewAdapter
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.model.ChoreList
import com.lambdaschool.choretracker.model.StandardChoreList
import com.lambdaschool.choretracker.viewmodel.ParentStandardChoreListActivityViewModel
import kotlinx.android.synthetic.main.activity_parent_standard_chore_list.*
import kotlinx.android.synthetic.main.item_parent_standard_chore.view.*

class ParentStandardChoreListActivity : AppCompatActivity() {

    private lateinit var parentStandardChoreListViewModel: ParentStandardChoreListActivityViewModel

    companion object {
        const val PARENT_CHORE_DETAIL_KEY = "ALKJSHDF08INQPOJERF0IASDG"
        const val CREATE_CHORE_REQUEST_CODE = 2898
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_standard_chore_list)

        parentStandardChoreListViewModel =
            ViewModelProviders.of(this).get(ParentStandardChoreListActivityViewModel::class.java)

        val recyclerView = recyclerview_chores

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = StandardChoreListRecyclerViewAdapter(StandardChoreList.standardChoreList)
        recyclerView.adapter = adapter

        parentStandardChoreListViewModel.getAllChoresForParentId(-1).observe(this, Observer {
            if (it.isNotEmpty()) {
                it.forEachIndexed { index, t ->
                    if (index == 0) {
                        StandardChoreList.standardChoreList.clear()
                    }

                    StandardChoreList.standardChoreList.add(t)

                    if (index == it.size - 1) {
                        adapter.notifyDataSetChanged()
                    }
                }
            } else {
                ChoreList.choreList.clear()
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_CHORE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val newChore = data?.getSerializableExtra(PARENT_CHORE_DETAIL_KEY) as Chore
            parentStandardChoreListViewModel.createChore(newChore)
            Toast.makeText(this, "Chore created!", Toast.LENGTH_SHORT).show()
        }
    }
}
