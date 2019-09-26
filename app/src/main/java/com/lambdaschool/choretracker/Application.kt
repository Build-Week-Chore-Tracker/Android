package com.lambdaschool.choretracker

import android.app.Application
import com.lambdaschool.choretracker.database.DatabaseRepo
import com.lambdaschool.choretracker.util.Prefs

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