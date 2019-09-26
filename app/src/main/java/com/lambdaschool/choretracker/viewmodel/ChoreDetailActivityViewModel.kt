package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.util.repo

class ChoreDetailActivityViewModel : ViewModel() {

    fun getAllChores(parentId: Int) = repo.getAllChoresForParentId(parentId)
}