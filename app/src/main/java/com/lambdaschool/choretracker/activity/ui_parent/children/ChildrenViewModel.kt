package com.lambdaschool.choretracker.activity.ui_parent.children

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.util.repo

class ChildrenViewModel : ViewModel() {

    fun getAllChildForParentId(parentId: Int): LiveData<List<Child>> =
        repo.getAllChildForParentId(parentId)
}