package com.mikerusetsky.appcinema



import android.app.Application
import com.mikerusetsky.appcinema.di.AppComponent
import com.mikerusetsky.appcinema.di.DaggerAppComponent
import com.mikerusetsky.appcinema.di.modules.DatabaseModule
import com.mikerusetsky.appcinema.di.modules.DomainModule
import com.mikerusetsky.remote_module.DaggerRemoteComponent


class App : Application() {
    lateinit var dagger: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this

        //Создаем компонент
        val remoteProvider = DaggerRemoteComponent.create()
        dagger = DaggerAppComponent.builder()
            .remoteProvider(remoteProvider)
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}