package com.lambdaschool.choretracker.util

import android.app.Application
import com.lambdaschool.choretracker.database.DatabaseRepo

val repo by lazy {

    App.repo!!

}

val prefs: Prefs by lazy {

    App.prefs!!

}

class App : Application() {

    companion object {
        var repo: DatabaseRepoInterface? = null
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        super.onCreate()

        repo = DatabaseRepo(applicationContext)
        prefs = Prefs(applicationContext)
    }
}