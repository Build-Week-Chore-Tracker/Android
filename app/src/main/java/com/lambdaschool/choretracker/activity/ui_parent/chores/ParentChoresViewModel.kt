package com.lambdaschool.choretracker.activity.ui_parent.chores

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.util.repo

class ParentChoresViewModel : ViewModel() {

    fun getAllChoresForParentId(parentId: Int): LiveData<List<Chore>> =
        repo.getAllChoresForParentId(parentId)

    fun getAllChildForParentId(parentId: Int): LiveData<List<Child>> =
        repo.getAllChildForParentId(parentId)
}