package com.lambdaschool.choretracker

import android.app.Application
import com.lambdaschool.choretracker.database.DatabaseRepo

val repo by lazy {

    App.repo!!

}

class App : Application() {

    companion object {
        var repo: DatabaseRepoInterface? = null
    }

    override fun onCreate() {
        super.onCreate()

        repo = DatabaseRepo(applicationContext)
    }
}