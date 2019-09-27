package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.util.repo

class ParentStandardChoreListActivityViewModel : ViewModel() {

    fun getAllChoresForParentId(parentId: Int) = repo.getAllChoresForParentId(parentId)

    fun createChore(chore: Chore) = repo.createChore(chore)
}