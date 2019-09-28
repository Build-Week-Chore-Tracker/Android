package com.lambdaschool.choretracker.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.ui_parent.children.ChildrenFragment
import com.lambdaschool.choretracker.activity.ui_parent.chores.ParentChoresFragment
import com.lambdaschool.choretracker.activity.ui_parent.store.NotificationsFragment
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.ChildLoginCredential
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.viewmodel.ParentMainActivityViewModel

class ParentMainActivity : AppCompatActivity(),
    ChildrenFragment.OnParentChildrenListFragmentInteractionListener,
    ParentChoresFragment.OnParentChoresFragmentInteractionListener,
    NotificationsFragment.OnStoreFragmentInteractionListener {

    companion object {
        const val ADD_CHILD_KEY = "ALKSDFPOKASDNLGKANDG"
        const val ADD_CHORE_KEY = "LJASHD0GVINQ0PIGJH0IER"
        const val CHILD_REQUEST_CODE = 77
        const val CHILD_REQUEST_KEY = "REQUEST_KEY"
        const val CHILD_CREDENTIALS_REQUEST_KEY = "QUIAHSWF09IUQWH0REGFH"
        const val EDIT_CHORE_DETAIL_KEY = "LJAHS0FIHPQIWEHFISF"
        const val EDIT_CHORE_DETAIL_CODE = 7263
        const val DELETE_CHORE_KEY = "O0AINSDF08Q09FOJEPIH9IJPIAHS9FDIHJ1IHEF"
    }

    private lateinit var parentViewModel: ParentMainActivityViewModel


    override fun onParentChildrenListFragmentInteractionListener(child: Child) {
        if (child.name == ADD_CHILD_KEY) {
            val intent = Intent(this, ParentAddChildActivity::class.java)
            startActivityForResult(intent, CHILD_REQUEST_CODE)
        } else {
            val intent = Intent(this, ParentChildDetailActivity::class.java)
            intent.putExtra(CHILD_REQUEST_KEY, child)
            startActivity(intent)
        }
    }

    override fun onParentChoresFragmentInteractionListener(chore: Chore) {
        if (chore.title == ADD_CHORE_KEY) {
            val intent = Intent(this, ParentStandardChoreListActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, ParentChoreDetailActivity::class.java)
            intent.putExtra(EDIT_CHORE_DETAIL_KEY, chore)
            startActivityForResult(intent, EDIT_CHORE_DETAIL_CODE)
        }

    }

    override fun onStoreFragmentInteractionListener() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_children, R.id.navigation_chores, R.id.navigation_store
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        parentViewModel = ViewModelProviders.of(this).get(ParentMainActivityViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CHILD_REQUEST_CODE) {
                val child = data?.getSerializableExtra(CHILD_REQUEST_KEY) as Child
                val childCreds =
                    data.getSerializableExtra(CHILD_CREDENTIALS_REQUEST_KEY) as ChildLoginCredential

                parentViewModel.createChild(child)
                parentViewModel.createChildLoginCredential(childCreds)

                Toast.makeText(
                    this,
                    "${child.name} has been registered for an account with the " +
                            "username of '${childCreds.username}'",
                    Toast.LENGTH_LONG
                ).show()

            } else if (requestCode == EDIT_CHORE_DETAIL_CODE) {
                val chore =
                    data?.getSerializableExtra(ParentStandardChoreListActivity.PARENT_CHORE_DETAIL_KEY) as Chore
                val choreId = data.getSerializableExtra(EDIT_CHORE_DETAIL_KEY) as Int
                val isBeingUpdated = data.getSerializableExtra(DELETE_CHORE_KEY) as Boolean

                val reconstructedChore = Chore(
                    chore.title,
                    chore.description,
                    chore.pointValue,
                    chore.childCompleted,
                    chore.photoFilePath,
                    chore.parent_id,
                    chore.child_id,
                    choreId
                )

                if (isBeingUpdated) {

                    parentViewModel.updateChore(reconstructedChore)
                } else {
                    parentViewModel.deleteChore(reconstructedChore)
                }
            }
        }
    }
}
