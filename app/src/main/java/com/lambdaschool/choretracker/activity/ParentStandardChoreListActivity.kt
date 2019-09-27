package com.lambdaschool.choretracker.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.adapter.StandardChoreListRecyclerViewAdapter
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.model.ChoreList
import com.lambdaschool.choretracker.model.StandardChoreList
import com.lambdaschool.choretracker.viewmodel.ParentStandardChoreListActivityViewModel
import kotlinx.android.synthetic.main.activity_parent_standard_chore_list.*

class ParentStandardChoreListActivity : AppCompatActivity() {

    private lateinit var parentStandardChoreListViewModel: ParentStandardChoreListActivityViewModel

    companion object {
        const val PARENT_CHORE_DETAIL_KEY = "ALKJSHDF08INQPOJERF0IASDG"
        const val PARENT_CHORE_DETAIL_CODE = 2898
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
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PARENT_CHORE_DETAIL_CODE) {
                val newChore = data?.getSerializableExtra(PARENT_CHORE_DETAIL_KEY) as Chore
                parentStandardChoreListViewModel.createChore(newChore)
                Toast.makeText(this, "Chore created!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
