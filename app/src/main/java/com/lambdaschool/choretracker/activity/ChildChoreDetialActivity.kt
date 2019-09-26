package com.lambdaschool.choretracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.adapter.ChoreAdapter
import com.lambdaschool.choretracker.model.StandardChoreList
import com.lambdaschool.choretracker.viewmodel.ChoreDetailActivityViewModel
import kotlinx.android.synthetic.main.activity_child_chore_detial.*

class ChildChoreDetialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_chore_detial)

    }
}
