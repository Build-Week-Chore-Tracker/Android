package com.lambdaschool.choretracker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.lambdaschool.choretracker.activity.ParentMainActivity.Companion.CHILD_REQUEST_KEY
import com.lambdaschool.choretracker.model.Child

class AddChildActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_child)

        val addChildIntent = intent
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.save_child, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save_child_menu) {
            //TODO pass data back to child fragment
            val child = Child("Name", "#ffffff", 9000, "photo_id", 0)
            val intent = Intent()
            intent.putExtra(CHILD_REQUEST_KEY, child)
            setResult(Activity.RESULT_OK, intent)
            finish()
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
