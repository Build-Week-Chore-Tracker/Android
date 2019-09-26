package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.ChildLoginCredential
import com.lambdaschool.choretracker.model.CredentialsAPI
import com.lambdaschool.choretracker.util.repo

class LoginActivityViewModel: ViewModel() {

    fun loginUser(creds: CredentialsAPI) = repo.loginUser(creds)

    fun getChildLoginCredentialForUsernamePassword(username: String, password: String):
            LiveData<ChildLoginCredential> =
        repo.getChildLoginCredentialForUsernamePassword(username, password)
}