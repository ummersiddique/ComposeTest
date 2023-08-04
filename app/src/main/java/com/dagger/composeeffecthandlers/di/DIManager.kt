package com.dagger.composeeffecthandlers.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class DIManager private constructor(private val application: Context) {

    companion object {
        fun initialize(application: Context) {
            DIManager(application)
        }
    }

    init {
        startKoin {
            androidContext(application)
            modules(getModules())
        }
    }

    private val moduleList
        get() = listOf(localRepoList, remoteRepoList)

    private val localRepoList
        get() = listOf(localRepo)

    private val remoteRepoList
        get() = listOf(remoteRepo)

    private fun getModules(): List<Module> {
        return moduleList.flatten()
    }
}