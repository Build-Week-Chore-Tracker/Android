package com.lambdaschool.choretracker.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

object ChildList {
    val childList = mutableListOf<Child>()
}

@Entity(tableName = "child_table")
class Child(
    var name: String,
    var labelColorHex: String, // "#FFFFFF"
    var earnedPoints: Int,
    var numChoresComplete: Int,
    var photo_id: String,
    val parent_id: Int,

    @PrimaryKey(autoGenerate = true) @NonNull
    val child_id: Int = 0

) : Serializable
