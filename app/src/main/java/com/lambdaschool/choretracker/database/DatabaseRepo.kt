package com.lambdaschool.choretracker.database

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.lambdaschool.choretracker.DatabaseRepoInterface
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.Chore

class DatabaseRepo(val contxt: Context) : DatabaseRepoInterface {

    val context = contxt.applicationContext

    // Chore table
    override fun createChore(chore: Chore) {
        CreateChoreAsyncTask(database.databaseDao()).execute(chore)
    }

    override fun getAllChores(): LiveData<List<Chore>> {
        return database.databaseDao().getAllChores()
    }

    override fun updateChore(chore: Chore) {
        UpdateChoreAsyncTask(database.databaseDao()).execute(chore)
    }

    override fun deleteChore(chore: Chore) {
        DeleteChoreAsyncTask(database.databaseDao()).execute(chore)
    }

    // Child table
    override fun createChild(child: Child) {
        CreateChildAsyncTask(database.databaseDao()).execute(child)
    }

    override fun getAllChild(): LiveData<List<Child>> {
        return database.databaseDao().getAllChild()
    }

    override fun updateChild(child: Child) {
        UpdateChildAsyncTask(database.databaseDao()).execute(child)
    }

    override fun deleteChild(child: Child) {
        DeleteChildAsyncTask(database.databaseDao()).execute(child)
    }

    private val database by lazy {
        Room.databaseBuilder(
            context,
            database::class.java,
            "chore_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    companion object {

        class CreateChoreAsyncTask(val dbDao: DatabaseDAO) : AsyncTask<Chore, Unit, Unit>() {

            override fun doInBackground(vararg chore: Chore?) {
                chore[0]?.let {
                    dbDao.createChore(it)
                }
            }
        }

        class UpdateChoreAsyncTask(val dbDao: DatabaseDAO) : AsyncTask<Chore, Unit, Unit>() {

            override fun doInBackground(vararg chore: Chore?) {
                chore[0]?.let {
                    dbDao.updateChore(it)
                }
            }
        }

        class DeleteChoreAsyncTask(val dbDao: DatabaseDAO) : AsyncTask<Chore, Unit, Unit>() {

            override fun doInBackground(vararg chore: Chore?) {
                chore[0]?.let {
                    dbDao.deleteChore(it)
                }
            }
        }

        class CreateChildAsyncTask(val dbDao: DatabaseDAO) : AsyncTask<Child, Unit, Unit>() {

            override fun doInBackground(vararg child: Child?) {
                child[0]?.let {
                    dbDao.createChild(it)
                }
            }
        }

        class UpdateChildAsyncTask(val dbDao: DatabaseDAO) : AsyncTask<Child, Unit, Unit>() {

            override fun doInBackground(vararg child: Child?) {
                child[0]?.let {
                    dbDao.updateChild(it)
                }
            }
        }

        class DeleteChildAsyncTask(val dbDao: DatabaseDAO) : AsyncTask<Child, Unit, Unit>() {

            override fun doInBackground(vararg child: Child?) {
                child[0]?.let {
                    dbDao.deleteChild(it)
                }
            }
        }
    }
}