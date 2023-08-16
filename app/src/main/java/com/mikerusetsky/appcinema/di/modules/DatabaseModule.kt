package com.mikerusetsky.appcinema.di.modules

import android.content.Context
import com.mikerusetsky.appcinema.data.MainRepository
import com.mikerusetsky.appcinema.data.db.DatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabaseHelper(context: Context) = DatabaseHelper(context)


    @Provides
    @Singleton
    fun provideRepository (databaseHelper: DatabaseHelper) = MainRepository(databaseHelper)
}