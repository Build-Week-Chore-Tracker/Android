package com.lambdaschool.choretracker

import androidx.lifecycle.LiveData
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.model.CredentialsAPI

interface DatabaseRepoInterface {

    // Chore table
    fun createChore(chore: Chore)
    fun getAllChores(): LiveData<List<Chore>>
    fun updateChore(chore: Chore)
    fun deleteChore(chore: Chore)

    // Child table
    fun createChild(child: Child)
    fun getAllChild(): LiveData<List<Child>>
    fun updateChild(child: Child)
    fun deleteChild(child: Child)

    // ChoreTrackerAPI
    fun registerUser(creds: CredentialsAPI): LiveData<Boolean>
    fun loginUser(creds: CredentialsAPI): LiveData<Boolean>
}