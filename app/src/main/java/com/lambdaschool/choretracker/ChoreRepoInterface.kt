package com.lambdaschool.choretracker

import com.lambdaschool.choretracker.model.Chore

interface ChoreRepoInterface {

    fun createChore(chore: Chore)
    fun getAllChores(): MutableList<Chore>
    fun updateChore(chore: Chore)
    fun deleteChore(chore: Chore)
}