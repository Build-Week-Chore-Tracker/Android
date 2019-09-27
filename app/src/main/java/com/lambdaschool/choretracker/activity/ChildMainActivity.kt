package com.lambdaschool.choretracker.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.ui_child.points.ChildPointsFragment
import com.lambdaschool.choretracker.activity.ui_child.chores.ChildChoresFragment
import com.lambdaschool.choretracker.activity.ui_child.trade.ChildTradeFragment
import com.lambdaschool.choretracker.model.Chore

class ChildMainActivity : AppCompatActivity(),
    ChildPointsFragment.OnChildPointsFragmentInteractionListener,
    ChildChoresFragment.OnChildChoresFragmentInteractionListener,
    ChildTradeFragment.OnChildTradeFragmentInteractionListener {

    companion object {
        const val CHILD_CHORE_DETAIL_KEY = "LAHSD0FINHQ02IEFH"
        const val CHILD_CHORE_DETAIL_CODE = 3871
    }

    override fun onChildTradeFragmentInteractionListener() {

    }

    override fun onChildChoresFragmentInteractionListener(chore: Chore) {
        val intent = Intent(this, ChildChoreDetialActivity::class.java)
        intent.putExtra(CHILD_CHORE_DETAIL_KEY, chore)
        startActivityForResult(intent, CHILD_CHORE_DETAIL_CODE)
    }

    override fun onChildPointsFragmentInteractionListener() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_child_chores, R.id.navigation_child_points, R.id.navigation_child_trade
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CHILD_CHORE_DETAIL_CODE && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Chore submitted!", Toast.LENGTH_SHORT).show()
        }
    }
}
