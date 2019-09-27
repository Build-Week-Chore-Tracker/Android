package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Child

class ParentChoreDetailActivityViewModel: ViewModel() {

    fun getAllChildForParentId(parentId: Int): LiveData<List<Child>> {
        return getAllChildForParentId(parentId)
    }
}