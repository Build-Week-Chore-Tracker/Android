package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.util.repo

class ChildChoreDetailActivityViewModel : ViewModel() {

    fun updateChore(chore: Chore) =
        repo.updateChore(chore)
}