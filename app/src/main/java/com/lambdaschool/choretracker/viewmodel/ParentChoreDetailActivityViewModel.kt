package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.ChildLoginCredential
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.util.repo

class ParentChoreDetailActivityViewModel: ViewModel() {

    fun getAllChildForParentId(parentId: Int): LiveData<List<Child>> =
        repo.getAllChildForParentId(parentId)

    fun createChild(child: Child) = repo.createChild(child)

    fun updateChild(child: Child) = repo.updateChild(child)

    fun createChildLoginCredential(childCreds: ChildLoginCredential) =
        repo.createChildLoginCredential(childCreds)

    fun updateChore(chore: Chore) = repo.updateChore(chore)
}