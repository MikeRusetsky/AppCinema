package com.mikerusetsky.appcinema.di

import com.mikerusetsky.appcinema.di.modules.DatabaseModule
import com.mikerusetsky.appcinema.di.modules.DomainModule
import com.mikerusetsky.appcinema.viewmodel.HomeFragmentViewModel
import com.mikerusetsky.appcinema.viewmodel.SettingsFragmentViewModel
import com.mikerusetsky.remote_module.RemoteModule
import com.mikerusetsky.remote_module.RemoteProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    dependencies = [RemoteProvider::class],
    modules = [
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась возможность внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    //метод для того, чтобы появилась возможность внедрять зависимости в SettingsFragmentViewModel
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}