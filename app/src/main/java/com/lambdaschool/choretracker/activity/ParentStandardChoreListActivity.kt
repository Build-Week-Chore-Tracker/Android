package com.lambdaschool.choretracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.adapter.ChoreAdapter
import com.lambdaschool.choretracker.model.ChoreList
import com.lambdaschool.choretracker.model.StandardChoreList
import com.lambdaschool.choretracker.viewmodel.ParentStandardChoreListActivityViewModel
import kotlinx.android.synthetic.main.activity_parent_standard_chore_list.*

class ParentStandardChoreListActivity : AppCompatActivity() {

    private lateinit var parentStandardChoreListViewModel: ParentStandardChoreListActivityViewModel

    companion object {
        const val CUSTOM_CHORE_REQUEST_CODE = 289
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_standard_chore_list)

        parentStandardChoreListViewModel = ViewModelProviders.of(this).get(ParentStandardChoreListActivityViewModel::class.java)

        val recyclerView = recyclerview_chores

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = ChoreAdapter(StandardChoreList.standardChoreList)
        recyclerView.adapter = adapter

        parentStandardChoreListViewModel.getAllChores(-1).observe(this, Observer {
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
}
