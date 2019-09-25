package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.LiveData
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.repo

class ChildMainActivityViewModel {

    // Chores
    val entriesChore: LiveData<List<Chore>> by lazy {
        getAllChores()
    }

    fun getAllChores() : LiveData<List<Chore>> {
        return repo.getAllChores()
    }

    fun createChore(chore: Chore) {
        repo.createChore(chore)
    }

    fun updateChore(chore: Chore) {
        repo.updateChore(chore)
    }

    fun deleteChore(chore: Chore) {
        repo.deleteChore(chore)
    }

    // Children
    val entriesChild: LiveData<List<Child>> by lazy {
        getAllChild()
    }

    fun getAllChild() : LiveData<List<Child>> {
        return repo.getAllChild()
    }

    fun createChild(child: Child) {
        repo.createChild(child)
    }

    fun updateChild(child: Child) {
        repo.updateChild(child)
    }

    fun deleteChild(child: Child) {
        repo.deleteChild(child)
    }

}