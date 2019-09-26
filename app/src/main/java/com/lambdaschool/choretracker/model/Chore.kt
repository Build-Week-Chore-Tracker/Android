package com.lambdaschool.choretracker.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

object ChoreList {
    val choreList = mutableListOf<Chore>()
}

object StandardChoreList {
    val standardChoreList = mutableListOf<Chore>(
        Chore("Create your own chore", "Create your own chore", 5, false, "", -1, -1),
        Chore("Clean room", "Clean your room", 5, false, "", -1, -1),
        Chore("Make bed", "Make your bed", 5, false, "", -1, -1),
        Chore("Vacuum", "Vacuum the house", 5, false, "", -1, -1),
        Chore("Clean bathtub", "Clean the bathtub", 5, false, "", -1, -1),
        Chore("Clean bathroom sink", "Clean bathroom sink", 5, false, "", -1, -1),
        Chore("Clean dishes", "Clean dishes ", 5, false, "", -1, -1),
        Chore("Take out trash", "Take out trash", 5, false, "", -1, -1),
        Chore("Take dog for walk", "Take dog for walk", 5, false, "", -1, -1),
        Chore("Clean counters", "Clean counters", 5, false, "", -1, -1),
        Chore("Clean stove", "Clean stove", 5, false, "", -1, -1),
        Chore("Sweep floors", "Sweep floors", 5, false, "", -1, -1),
        Chore("Laundry", "Laundry", 5, false, "", -1, -1),
        Chore("Clean toilet", "Clean toilet", 5, false, "", -1, -1),
        Chore("Mop floors", "Mop floors", 5, false, "", -1, -1),
        Chore("Water plants", "Water plants", 5, false, "", -1, -1),
        Chore("Mow the lawn", "Mow the lawn", 5, false, "", -1, -1)
    )
}

@Entity (tableName = "chore_table")
class Chore (
    var title: String,
    var description: String,
    var pointValue: Int,
    var childCompleted: Boolean,
    var photoFilePath: String,
    val parent_id: Int,
    var child_id: Int,

    @PrimaryKey(autoGenerate = true) @NonNull
    val chore_id: Int = 0

    ) : Serializable
