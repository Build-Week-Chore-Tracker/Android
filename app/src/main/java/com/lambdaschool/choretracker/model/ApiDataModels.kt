package com.lambdaschool.choretracker.model

import java.io.Serializable

object ListChoreAPI {
    val listChoreAPI = mutableListOf<ChoreAPI>()
}

object ListChildAPI {
    val listChildAPI = mutableListOf<ChildAPI>()
}

data class RegistrationReturnedAPI(
    val message: String,
    val id: Int
)

class CredentialsAPI(
    val name: String,
    val username: String,
    val email: String,
    val password: String
)

data class LoginReturnedAPI(
    val message: String,
    val token: String,
    val user: Int
)

class SerializableChoreAPI(
    val name: String,
    val how_long: String,
    val points: Int,
    val due_date: String,
    val done_date: String,
    val notes: String,
    val User_id: Int
) : Serializable

class SerializableChildAPI(
    val name: String,
    val username: String,
    val age: Int,
    val points: Int,
    val child: Boolean
) : Serializable

data class ChoreAPI(
    val name: String,
    val how_long: String,
    val points: Int,
    val due_date: String,
    val done_date: String,
    val notes: String,
    val User_id: Int
)

data class ChildAPI(
    val name: String,
    val username: String,
    val age: Int,
    val points: Int,
    val child: Boolean
)

