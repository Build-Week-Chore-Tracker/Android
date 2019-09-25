package com.lambdaschool.choretracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.ParentMainActivity.Companion.CHILD_REQUEST_KEY
import com.lambdaschool.choretracker.model.Child
import kotlinx.android.synthetic.main.activity_child_detail.*

class ChildDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_detail)

        val intent = intent.getSerializableExtra(CHILD_REQUEST_KEY) as Child
        tv_child_detail_name.text = intent.name
    }
}
