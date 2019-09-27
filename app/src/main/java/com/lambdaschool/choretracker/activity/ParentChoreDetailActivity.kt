package com.lambdaschool.choretracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.model.Chore

class ParentChoreDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_chore_detail)

        val data = intent.getSerializableExtra(
            ParentStandardChoreListActivity.PARENT_CHORE_DETAIL_KEY
        ) as Chore


    }
}
