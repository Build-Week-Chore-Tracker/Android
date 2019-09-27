package com.lambdaschool.choretracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.util.Prefs
import com.lambdaschool.choretracker.viewmodel.ParentChoreDetailActivityViewModel
import kotlinx.android.synthetic.main.activity_parent_chore_detail.*

class ParentChoreDetailActivity : AppCompatActivity() {

    private lateinit var parentChoreDetailActivityViewModel: ParentChoreDetailActivityViewModel
    var data: Chore? = null
    var prefs: Prefs? = null
    val children = mutableListOf<Child>()
    var pointer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_chore_detail)

        prefs = Prefs(this)
        parentChoreDetailActivityViewModel = ViewModelProviders.of(this).get(
            ParentChoreDetailActivityViewModel::class.java)

        val loginCreds = prefs?.getLoginCredentials()
        var userId = -1

        loginCreds?.user?.let {
            userId = it
        }

        parentChoreDetailActivityViewModel.getAllChildForParentId(userId).observe(this, Observer {
            if (it.isNotEmpty()) {
                it.forEachIndexed { index, t ->
                    if (index == 0) {
                        children.clear()
                    }
                    children.add(t)
                }
            } else {
                children.clear()
                val string = "No Children"
                btn_parent_chore_detail_select_child.text = string
            }
        })

        if (intent.getSerializableExtra(
                ParentStandardChoreListActivity.PARENT_CHORE_DETAIL_KEY) != null) {

            data = intent.getSerializableExtra(
                ParentStandardChoreListActivity.PARENT_CHORE_DETAIL_KEY) as Chore

            setHeader("Create Chore")

        } else if (intent.getSerializableExtra(ParentMainActivity.EDIT_CHORE_DETAIL_KEY) != null) {

            data = intent.getSerializableExtra(
                ParentStandardChoreListActivity.PARENT_CHORE_DETAIL_KEY
            ) as Chore

            setHeader("Edit Chore")
            deletionButtonVisibility(data?.childCompleted)
            setPointerForChild(data?.child_id)
        }


        setChoreName(data?.title)
        setChorePoints(data?.pointValue)
        setChoreDetail(data?.description)
        approvalButtonsVisibility(data?.childCompleted)

        ib_parent_chore_detail_previous.setOnClickListener {
            decrementPointer()
            btn_parent_chore_detail_select_child.text = children[pointer].name
        }

        ib_parent_chore_detail_next.setOnClickListener {
            incrementPointer()
            btn_parent_chore_detail_select_child.text = children[pointer].name
        }

    }

    private fun setHeader(string: String) {
        tv_parent_chore_detail_header.text = string
    }

    private fun setChoreName(string: String?) {
        if (string != "Create your own chore") {
            et_parent_chore_detail_name.setText(string)
        }
    }

    private fun setChorePoints(int: Int?) {
        et_parent_chore_detail_points.setText(int.toString())
    }

    private fun setChoreDetail(string: String?) {
        et_parent_chore_detail_description.setText(string)
    }

    private fun approvalButtonsVisibility(isComplete: Boolean?) {
        if (isComplete != null) {
            if (isComplete) {
                ll_parent_chore_detail_completion_buttons.visibility = View.VISIBLE
            } else {
                ll_parent_chore_detail_completion_buttons.visibility = View.GONE
            }
        }
    }

    private fun deletionButtonVisibility(isCompleted: Boolean?) {
        if (isCompleted != null) {
            if (!isCompleted) {
                btn_parent_chore_detail_delete.visibility = View.VISIBLE
            } else
                btn_parent_chore_detail_delete.visibility = View.GONE
        }
    }

    private fun setPointerForChild(childId: Int?) {
        children.forEachIndexed { index, child ->
            if (child.child_id == childId) {
                pointer = index
                btn_parent_chore_detail_select_child.text = child.name
            }
        }
    }

    private fun incrementPointer() {
        pointer++
        if (pointer >= children.size) {
            pointer = 0
        }
    }

    private fun decrementPointer() {
        pointer--
        if (pointer < 0) {
            pointer = children.size - 1
        }
    }

}
