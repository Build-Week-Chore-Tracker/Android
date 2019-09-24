package com.lambdaschool.choretracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

object ChoreList {
    val choreList = mutableListOf<Chore>()
}

@Entity
class Chore (
    val title: String,
    val description: String,
    val dueDate: String, // MM-DD-YYYY
    val repeating: Boolean,
    val interval: String, // "daily", "weekly", "bi-weekly", "monthly"
    val pointValue: Int,
    @PrimaryKey(autoGenerate = true)
    val chore_id: Int
    ) : Serializable
