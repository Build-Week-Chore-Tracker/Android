package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.repo

class ParentMainActivityViewModel : ViewModel() {

    fun createChild(child: Child) {
        repo.createChild(child)
    }
}