package com.lambdaschool.choretracker.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.lambdaschool.choretracker.DatabaseRepoInterface
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.Chore

class DatabaseRepo(val contxt: Context) : DatabaseRepoInterface {

    val context = contxt.applicationContext

    // Chore table
    override fun createChore(chore: Chore) {
        database.databaseDao().createChore(chore)
    }

    override fun getAllChores(): LiveData<List<Chore>> {
        return database.databaseDao().getAllChores()
    }

    override fun updateChore(chore: Chore) {
        database.databaseDao().updateChore(chore)
    }

    override fun deleteChore(chore: Chore) {
        database.databaseDao().deleteChore(chore)
    }

    // Child table
    override fun createChild(child: Child) {
        database.databaseDao().createChild(child)
    }

    override fun getAllChild(): LiveData<List<Child>> {
        return database.databaseDao().getAllChild()
    }

    override fun updateChild(child: Child) {
        database.databaseDao().updateChild(child)
    }

    override fun deleteChild(child: Child) {
        database.databaseDao().deleteChild(child)
    }

    private val database by lazy {
        Room.databaseBuilder(
            context,
            database::class.java,
            "chore_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
}