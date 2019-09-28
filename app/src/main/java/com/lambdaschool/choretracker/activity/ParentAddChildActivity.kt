package com.lambdaschool.choretracker.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.ParentMainActivity.Companion.CHILD_CREDENTIALS_REQUEST_KEY
import com.lambdaschool.choretracker.activity.ParentMainActivity.Companion.CHILD_REQUEST_KEY
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.ChildLoginCredential
import com.lambdaschool.choretracker.util.Prefs
import com.lambdaschool.choretracker.viewmodel.ParentAddChildActivityViewModel
import kotlinx.android.synthetic.main.activity_parent_add_child.*

class ParentAddChildActivity : AppCompatActivity() {

    var prefs: Prefs? = null
    private var doubleBackToExitPressedOnce = false
    lateinit var viewModel: ParentAddChildActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_add_child)

        var childCreated = false
        prefs = Prefs(this)
        viewModel = ViewModelProviders.of(this).get(ParentAddChildActivityViewModel::class.java)

        val loginCreds = prefs?.getLoginCredentials()

        var userId = -1
        loginCreds?.user?.let {
            userId = it
        }

        fab_save_child.setOnClickListener {
            val name = et_add_child_child_name.text.toString().capitalize()
            val userName = et_add_child_username.text.toString()
            val password = et_add_child_password.text.toString()
            val childColor = when {
                spinner.selectedItem.toString() == "Red" -> "#FF0000"
                spinner.selectedItem.toString() == "Blue" -> "#0000FF"
                spinner.selectedItem.toString() == "Green" -> "#00FF00"
                spinner.selectedItem.toString() == "Purple" -> "#800080"
                spinner.selectedItem.toString() == "Orange" -> "#FFA500"
                spinner.selectedItem.toString() == "Pink" -> "#FFC0CB"
                spinner.selectedItem.toString() == "Yellow" -> "#FFFF00"
                else -> "#FFFFFF"
            }

            if (name != "" && userName != "" && password != "") {
                viewModel.getChildLoginCredentialForUsername(userName)
                    .observe(this, Observer {
                        if (it == null) {
                            val child = Child(name, childColor, 0, 0, "", userId)
                            val childCreds = ChildLoginCredential(userName, password)

                            childCreated = true

                            val intent = Intent()
                            intent.putExtra(CHILD_REQUEST_KEY, child)
                            intent.putExtra(CHILD_CREDENTIALS_REQUEST_KEY, childCreds)
                            setResult(Activity.RESULT_OK, intent)
                            finish()
                        } else {
                            if (!childCreated) {
                                Toast.makeText(
                                    this,
                                    "The username '${it.username}' already exists",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            } else {
                Toast.makeText(
                    this,
                    "Please ensure no fields are left blank.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(
            this,
            "Press back again to leave child sign up. This will not add your child to the account.",
            Toast.LENGTH_LONG
        ).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 3000)
    }
}
