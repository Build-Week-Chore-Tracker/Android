package com.lambdaschool.choretracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.ParentMainActivity.Companion.CHILD_REQUEST_KEY
import com.lambdaschool.choretracker.adapter.ParentChildDetailChoreListAdapter
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.ChildList
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.model.ChoreList
import com.lambdaschool.choretracker.util.Prefs
import com.lambdaschool.choretracker.util.repo
import com.lambdaschool.choretracker.viewmodel.ParentChildDetailActivityViewModel
import kotlinx.android.synthetic.main.activity_parent_child_detail.*

class ParentChildDetailActivity : AppCompatActivity() {

    private lateinit var parentChildDetailActivityViewModel: ParentChildDetailActivityViewModel
    var prefs: Prefs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_child_detail)

        val intent = intent.getSerializableExtra(CHILD_REQUEST_KEY) as Child
        tv_child_detail_name.text = intent.name

        parentChildDetailActivityViewModel =
            ViewModelProviders.of(this).get(ParentChildDetailActivityViewModel::class.java)

        val recyclerView = recyclerview_parent_child_detail

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = ParentChildDetailChoreListAdapter(ChoreList.choreList)
        recyclerView.adapter = adapter

        parentChildDetailActivityViewModel.getAllChoresForChildId(intent.child_id).observe(this, Observer {
            if (it.isNotEmpty()) {
                it.forEachIndexed { index, t ->
                    if (index == 0) {
                        ChoreList.choreList.clear()
                    }

                    ChoreList.choreList.add(t)

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
