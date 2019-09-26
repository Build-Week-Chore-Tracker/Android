package com.lambdaschool.choretracker.activity.ui_child.chores

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.util.repo

class ChildChoresViewModel : ViewModel() {

    fun getAllChoresForChildId(childId: Int): LiveData<List<Chore>> {
        return repo.getAllChoresForChildId(childId)
    }
}