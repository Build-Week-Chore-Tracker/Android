package com.lambdaschool.choretracker.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.get
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.ParentMainActivity.Companion.CHILD_REQUEST_KEY
import com.lambdaschool.choretracker.model.Child
import kotlinx.android.synthetic.main.activity_add_child.*

class AddChildActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_child)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.save_child, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save_child_menu) {
            //TODO pass data back to child fragment
            val name = et_add_child_child_name.text.toString()
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
                spinner.selectedItem.toString() == "White" -> "#FFFFFF"
                else -> "#FFFFFF"
            }

            if (name == "" || userName == "" || password == "") {
                Toast.makeText(this, "Make sure Name, Username and password are not blank", Toast.LENGTH_SHORT).show()
            } else {
                val child = Child(name, childColor, 0, "", 0)
                val intent = Intent()
                intent.putExtra(CHILD_REQUEST_KEY, child)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        return true
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this,
            "Press back again to leave child sign up. This will not add your child to the account.",
            Toast.LENGTH_LONG).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 3000)
    }
}
