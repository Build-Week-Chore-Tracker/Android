package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.util.repo

class ParentChildDetailActivityViewModel : ViewModel() {

    fun getChildChores(childId: Int) = repo.getAllChoresForChildId(childId)
}