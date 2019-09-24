package com.lambdaschool.choretracker.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.Chore

@Dao
interface DatabaseDAO {

    // Chore table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createChore(chore: Chore)

    @Query("SELECT * FROM chore_table")
    fun getAllChores(): LiveData<List<Chore>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChore(chore: Chore)

    @Delete
    fun deleteChore(chore: Chore)

    // Child table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createChild(child: Child)

    @Query("SELECT * FROM child_table")
    fun getAllChild(): LiveData<List<Child>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChild(child: Child)

    @Delete
    fun deleteChild(child: Child)
}