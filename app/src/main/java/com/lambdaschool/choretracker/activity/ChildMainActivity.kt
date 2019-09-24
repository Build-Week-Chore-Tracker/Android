package com.lambdaschool.choretracker.activity

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

    override fun onChildTradeFragmentInteractionListener() {

    }

    override fun onChildChoresFragmentInteractionListener(entry: Chore, longPress: Boolean) {
        if (!longPress) {
            Toast.makeText(this, "${entry.title} was clicked", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "${entry.title} was LONGPRESSED", Toast.LENGTH_SHORT).show()
        }
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
}
