package com.lambdaschool.choretracker.database

import androidx.room.*
import com.lambdaschool.choretracker.model.Chore

@Dao
interface ChoreDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createChore(chore: Chore)

    @Query("SELECT * FROM Chore")
    fun getAllChores(): MutableList<Chore>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChore(chore: Chore)

    @Delete
    fun deleteChore(chore: Chore)
}