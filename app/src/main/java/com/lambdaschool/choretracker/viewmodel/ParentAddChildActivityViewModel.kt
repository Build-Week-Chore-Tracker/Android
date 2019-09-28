package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.ChildLoginCredential
import com.lambdaschool.choretracker.util.repo

class ParentAddChildActivityViewModel: ViewModel() {

    fun getChildLoginCredentialForUsername(username: String):
            LiveData<ChildLoginCredential> {
        return repo.getChildLoginCredentialForUsername(username)
    }
}