package com.lambdaschool.choretracker.viewmodel

import androidx.lifecycle.ViewModel
import com.lambdaschool.choretracker.model.CredentialsAPI
import com.lambdaschool.choretracker.repo

class RegistrationFragmentViewModel: ViewModel() {

    fun registerUser(creds: CredentialsAPI) = repo.registerUser(creds)
}