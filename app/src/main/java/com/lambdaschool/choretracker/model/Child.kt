package com.lambdaschool.choretracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

object ChildList {
    val childList = mutableListOf<Child>()
}

@Entity
class Child (
    val name: String,
    val labelColorHex: String, // "#FFFFFF"
    val earnedPoints: Int,
    val photo_id: String, // "${child_id}_${timestamp}" where timestamp = 'MM-DD-YYYY_HHMMSS' in 24h format
    @PrimaryKey(autoGenerate = true)
    val child_id: Int
) : Serializable
