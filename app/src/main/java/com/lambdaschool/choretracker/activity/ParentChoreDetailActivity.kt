package com.lambdaschool.choretracker.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.ChildLoginCredential
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
    var choreIsBeingEdited = false
    var userId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_chore_detail)

        prefs = Prefs(this)
        parentChoreDetailActivityViewModel = ViewModelProviders.of(this).get(
            ParentChoreDetailActivityViewModel::class.java
        )

        val loginCreds = prefs?.getLoginCredentials()

        loginCreds?.user?.let {
            userId = it
        }

        if (intent.getSerializableExtra(
                ParentStandardChoreListActivity.PARENT_CHORE_DETAIL_KEY
            ) != null
        ) {

            data = intent.getSerializableExtra(
                ParentStandardChoreListActivity.PARENT_CHORE_DETAIL_KEY
            ) as Chore

            setHeader("Create Chore")

        } else if (intent.getSerializableExtra(ParentMainActivity.EDIT_CHORE_DETAIL_KEY) != null) {
            choreIsBeingEdited = true

            data = intent.getSerializableExtra(
                ParentMainActivity.EDIT_CHORE_DETAIL_KEY
            ) as Chore

            setHeader("Edit Chore")

        }

        parentChoreDetailActivityViewModel.getAllChildForParentId(userId).observe(this, Observer {
            if (it != null) {
                it.forEachIndexed { index, t ->
                    if (index == 0) {
                        children.clear()
                        addClickToRegisterChildToMList()
                    }
                    children.add(t)
                    if (index == it.size - 1 && !choreIsBeingEdited) {
                        setPointerForChild(children[1].child_id)
                    } else if (index == it.size - 1 && choreIsBeingEdited) {
                        setPointerForChild(data?.child_id)
                    }
                }
            } else {
                children.clear()
                addClickToRegisterChildToMList()
                btn_parent_chore_detail_select_child.text = children[0].name
            }
        })

        setChoreName(data?.title)
        setChorePoints(data?.pointValue)
        setChoreDescription(data?.description)
        deletionButtonVisibility(choreIsBeingEdited)
        isCompleteButtonsVisibility(data?.childCompleted)

        btn_parent_chore_detail_select_child.setOnClickListener {
            if (btn_parent_chore_detail_select_child.text == children[0].name) {

                val intent = Intent(this, ParentAddChildActivity::class.java)
                startActivityForResult(intent, ParentMainActivity.CHILD_REQUEST_CODE)
            } else {
                // send intent with activity for result to edit child and update upon returning
            }
        }

        ib_parent_chore_detail_previous.setOnClickListener {
            if (children.size > 1) {
                decrementPointer()
                btn_parent_chore_detail_select_child.text = children[pointer].name
            }
        }

        ib_parent_chore_detail_next.setOnClickListener {
            if (children.size > 1) {
                incrementPointer()
                btn_parent_chore_detail_select_child.text = children[pointer].name
            }
        }

        fab_delete_chore.setOnClickListener {

            val intent = Intent()
            intent.putExtra(ParentMainActivity.EDIT_CHORE_DETAIL_KEY, data?.chore_id)
            intent.putExtra(ParentStandardChoreListActivity.PARENT_CHORE_DETAIL_KEY, data)
            intent.putExtra(ParentMainActivity.DELETE_CHORE_KEY, false)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        fab_save_chore.setOnClickListener {
            if (pointer != 0) {

                val choreItem = choreBuilder()

                val intent = Intent()
                intent.putExtra(ParentMainActivity.EDIT_CHORE_DETAIL_KEY, data?.chore_id)
                intent.putExtra(ParentStandardChoreListActivity.PARENT_CHORE_DETAIL_KEY, choreItem)
                intent.putExtra(ParentMainActivity.DELETE_CHORE_KEY, true)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Please select a child", Toast.LENGTH_SHORT).show()
            }
        }

        btn_parent_chore_detail_approve.setOnClickListener {

        }

        btn_parent_chore_detail_deny.setOnClickListener {

        }

    }

    private fun choreBuilder(): Chore {
        val choreName = et_parent_chore_detail_name.text.toString()
        val choreDescription = et_parent_chore_detail_description.text.toString()
        val chorePointValue = et_parent_chore_detail_points.text.toString().toInt()
        val childId = children[pointer].child_id

        val choreItem = Chore(
            choreName,
            choreDescription,
            chorePointValue,
            data?.childCompleted ?: false,
            data?.photoFilePath ?: "",
            userId,
            childId
        )

        return choreItem
    }

    private fun addClickToRegisterChildToMList() {
        children.add(Child("Click to register a child", "", 0, 0, "", -1))
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

    private fun setChoreDescription(string: String?) {
        et_parent_chore_detail_description.setText(string)
    }

    private fun isCompleteButtonsVisibility(isComplete: Boolean?) {
        if (isComplete != null) {
            if (isComplete) {
                ll_parent_chore_detail_completion_buttons.visibility = View.VISIBLE
                ll_parent_chore_detail_save_delete_fabs.visibility = View.GONE
            }
        }
    }

    private fun deletionButtonVisibility(isBeingEdited: Boolean?) {
        if (isBeingEdited != null) {
            if (isBeingEdited) {
                ll_parent_chore_detail_delete_fab.visibility = View.VISIBLE
            }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ParentMainActivity.CHILD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val child = data?.getSerializableExtra(ParentMainActivity.CHILD_REQUEST_KEY) as Child
            val childCreds =
                data.getSerializableExtra(ParentMainActivity.CHILD_CREDENTIALS_REQUEST_KEY) as ChildLoginCredential

            parentChoreDetailActivityViewModel.createChild(child)
            parentChoreDetailActivityViewModel.createChildLoginCredential(childCreds)
        }
    }

}
