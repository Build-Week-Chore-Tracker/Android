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

    @Query("SELECT * FROM chore_table WHERE parent_id = :parentId")
    fun getAllChoresForParentId(parentId: Int): LiveData<List<Chore>>

    @Query("SELECT * FROM chore_table WHERE child_id = :childId")
    fun getAllChoresForChildId(childId: Int): LiveData<List<Chore>>

    @Query("SELECT * FROM chore_table WHERE parent_id = :parentId AND child_id != :childId")
    fun getAllChoresForParentIdExceptChildId(parentId: Int, childId: Int): LiveData<List<Chore>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChore(chore: Chore)

    @Delete
    fun deleteChore(chore: Chore)

    // Child table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createChild(child: Child)

    @Query("SELECT * FROM child_table WHERE parent_id = :parentId")
    fun getAllChildForParentId(parentId: Int): LiveData<List<Child>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChild(child: Child)

    @Delete
    fun deleteChild(child: Child)
}