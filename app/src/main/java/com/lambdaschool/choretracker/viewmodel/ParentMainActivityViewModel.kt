package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.ChildLoginCredential
import com.lambdaschool.choretracker.util.repo

class ParentMainActivityViewModel : ViewModel() {

    fun createChild(child: Child) = repo.createChild(child)

    fun createChildLoginCredential(childCreds: ChildLoginCredential) =
        repo.createChildLoginCredential(childCreds)
}