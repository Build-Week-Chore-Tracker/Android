package com.lambdaschool.choretracker.model

object ChoreList {
    val choreList = mutableListOf<Chore>()
}

class Chore (
    val title: String,
    val description: String,
    val dueDate: String, // MM-DD-YYYY
    val assignMultiple: Boolean,
    val repeating: Boolean,
    val interval: String, // "daily", "weekly", "bi-weekly", "monthly"
    val pointValue: Int,
    val child_id: List<Int>,
    val chore_id: Int
    )
