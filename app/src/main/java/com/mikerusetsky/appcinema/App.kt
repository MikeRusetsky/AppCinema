package com.mikerusetsky.appcinema

import android.app.Application
import com.mikerusetsky.appcinema.di.AppComponent
import com.mikerusetsky.appcinema.di.modules.DatabaseModule
import com.mikerusetsky.appcinema.di.modules.DomainModule
import com.mikerusetsky.appcinema.di.modules.RemoteModule


class App : Application() {
    lateinit var dagger: AppComponent


    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}