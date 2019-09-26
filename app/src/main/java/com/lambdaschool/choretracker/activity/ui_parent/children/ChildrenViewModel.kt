package com.lambdaschool.choretracker.activity.ui_parent.children

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.repo

class ChildrenViewModel : ViewModel() {

    fun getAllChild(parentId: Int): LiveData<List<Child>> {
        return repo.getAllChildForParentId(parentId)
    }
}