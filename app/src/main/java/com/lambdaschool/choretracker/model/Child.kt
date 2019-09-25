package com.lambdaschool.choretracker.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

object ChildList {
    val childList = mutableListOf<Child>()
}

@Entity (tableName = "child_table")
class Child (
    val name: String,
    val labelColorHex: String, // "#FFFFFF"
    val earnedPoints: Int,
    val photo_id: String, // "${child_id}_${timestamp}" where timestamp = 'MM-DD-YYYY_HHMMSS' in 24h format
    val parent_id: Int,

    @PrimaryKey(autoGenerate = true) @NonNull
    val child_id: Int = 0

) : Serializable
