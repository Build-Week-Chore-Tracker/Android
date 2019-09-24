package com.lambdaschool.choretracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.Chore

@Database(entities = [Chore::class, Child::class], version = 1, exportSchema = false)
abstract class ChoreDB : RoomDatabase() {
    abstract fun choresDao() : ChoreDAO

//    abstract fun childDao() : ChildDAO
}