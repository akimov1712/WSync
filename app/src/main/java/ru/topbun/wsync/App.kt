package ru.topbun.wsync

import android.app.Application
import ru.topbun.wsync.di.DaggerAppComponent

class App: Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

}