package com.lambdaschool.choretracker.activity.ui_child.points

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.repo

class ChildPointsViewModel : ViewModel() {

    val entries: LiveData<List<Child>> by lazy {
        getAllChild()
    }

    fun getAllChild() : LiveData<List<Child>> {
        return repo.getAllChild()
    }

}