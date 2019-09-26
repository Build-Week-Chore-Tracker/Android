package com.lambdaschool.choretracker.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

object ChildLoginCredentialList {
    val ChildloginCredentialList = mutableListOf<ChildLoginCredential>()
}

@Entity(tableName = "child_login_credential")
class ChildLoginCredential (
    val username: String,
    val password: String,

    @PrimaryKey(autoGenerate = true) @NonNull
    val child_id: Int = 0

) : Serializable
