package com.lambdaschool.choretracker.database

import android.content.Context
import androidx.room.Room
import com.lambdaschool.choretracker.ChoreRepoInterface
import com.lambdaschool.choretracker.model.Chore

class ChoreDBRepo(contxt: Context) : ChoreRepoInterface {

    val context = contxt.applicationContext

    override fun createChore(chore: Chore) {
        database.choresDao().createChore(chore)
    }

    override fun getAllChores(): MutableList<Chore> {
        return database.choresDao().getAllChores()
    }

    override fun updateChore(chore: Chore) {
        database.choresDao().updateChore(chore)
    }

    override fun deleteChore(chore: Chore) {
        database.choresDao().deleteChore(chore)
    }

    private val database by lazy {
        Room.databaseBuilder(
            context,
            ChoreDB::class.java,
            "chore_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
}