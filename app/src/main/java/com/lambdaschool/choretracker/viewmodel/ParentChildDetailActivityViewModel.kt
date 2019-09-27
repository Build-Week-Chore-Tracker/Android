package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.util.repo

class ParentChildDetailActivityViewModel : ViewModel() {

    fun getAllChoresForChildId(childId: Int): LiveData<List<Chore>> =
        repo.getAllChoresForChildId(childId)
}