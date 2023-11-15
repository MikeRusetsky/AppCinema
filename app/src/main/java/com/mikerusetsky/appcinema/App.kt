package com.mikerusetsky.appcinema



import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.mikerusetsky.appcinema.di.AppComponent
import com.mikerusetsky.appcinema.di.DaggerAppComponent
import com.mikerusetsky.appcinema.di.modules.DatabaseModule
import com.mikerusetsky.appcinema.di.modules.DomainModule
import com.mikerusetsky.remote_module.DaggerRemoteComponent
import com.mikerusetsky.appcinema.view.notification.NotificationConstants.CHANNEL_ID


class App : Application() {
    lateinit var dagger: AppComponent
    var isPromoShown = false
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Задаем имя, описание и важность канала
            val name = "WatchLaterChannel"
            val descriptionText = "FilmsSearch notification Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            //Создаем канал, передав в параметры его ID(строка), имя(строка), важность(константа)
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            //Отдельно задаем описание
            mChannel.description = descriptionText
            //Получаем доступ к менеджеру нотификаций
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            //Регистрируем канал
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}