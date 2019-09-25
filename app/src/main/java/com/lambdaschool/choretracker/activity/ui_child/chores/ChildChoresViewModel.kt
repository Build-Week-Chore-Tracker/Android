package com.lambdaschool.choretracker.activity.ui_child.chores

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.repo

class ChildChoresViewModel : ViewModel() {

    val entries: LiveData<List<Chore>> by lazy {
        getAllChores()
    }

    fun getAllChores() : LiveData<List<Chore>> {
        return repo.getAllChores()
    }

    fun createChore(chore: Chore) {
        repo.createChore(chore)
    }
}