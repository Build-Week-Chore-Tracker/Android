package com.lambdaschool.choretracker.activity.ui_child.chores

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.repo

class ChildChoresViewModel : ViewModel() {

    fun getAllChoresForChildId(childId: Int): LiveData<List<Chore>> {
        return repo.getAllChoresForChildId(childId)
    }

    fun createChore(chore: Chore) {
        repo.createChore(chore)
    }

    fun deleteChore(chore: Chore) {
        repo.deleteChore(chore)
    }
}