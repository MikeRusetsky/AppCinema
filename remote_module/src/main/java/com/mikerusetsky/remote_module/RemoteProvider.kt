package com.mikerusetsky.remote_module

interface RemoteProvider {
    fun provideRemote(): TmdbApi
}