package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.LiveData
import com.lambdaschool.choretracker.model.Child
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.model.CredentialsAPI
import com.lambdaschool.choretracker.model.LoginCredential
import com.lambdaschool.choretracker.repo

class LoginActivityViewModel {

    fun loginUser(creds: CredentialsAPI) = repo.loginUser(creds)
}