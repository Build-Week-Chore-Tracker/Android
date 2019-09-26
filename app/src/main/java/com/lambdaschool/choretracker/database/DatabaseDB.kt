package com.lambdaschool.choretracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.ChildLoginCredential
import com.lambdaschool.choretracker.model.Chore

@Database(entities = [Chore::class, Child::class, ChildLoginCredential::class],
    version = 3, exportSchema = false)
abstract class database : RoomDatabase() {
    abstract fun databaseDao() : DatabaseDAO
}