package com.lambdaschool.choretracker.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

object ChoreList {
    val choreList = mutableListOf<Chore>()
}

@Entity (tableName = "chore_table")
class Chore (
    val title: String,
    val description: String,
    val dueDate: String, // MM-DD-YYYY
    val repeating: Boolean,
    val interval: String, // "daily", "weekly", "bi-weekly", "monthly"
    val pointValue: Int,
    val childCompleted: Boolean,
    val parentCompleted: Boolean,
    val child_id: Int,

    @PrimaryKey(autoGenerate = true) @NonNull
    val chore_id: Int

    ) : Serializable
