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
    val pointValue: Int,
    val childCompleted: Boolean,
    val parent_id: Int,
    val child_id: Int,

    @PrimaryKey(autoGenerate = true) @NonNull
    val chore_id: Int = 0

    ) : Serializable
