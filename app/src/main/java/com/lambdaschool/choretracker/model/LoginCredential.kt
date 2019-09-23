package com.lambdaschool.choretracker.model

object LoginCredentialList {
    val loginCredentialList = mutableListOf<LoginCredential>()
}

class LoginCredential (
    val username: String,
    val password: String,
    val isParent: Boolean
)
