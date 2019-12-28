package com.andreinsigne.moviegeek

import android.app.Application
import io.realm.Realm


class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

    }
}