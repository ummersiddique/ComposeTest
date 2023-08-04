package com.dagger.composeeffecthandlers.di

import com.dagger.composeeffecthandlers.repo.LocalRepo
import com.dagger.composeeffecthandlers.repo.RemoteRepo
import org.koin.dsl.module

val localRepo = module {
    single { LocalRepo() }
}

val remoteRepo = module {
    single { RemoteRepo() }
}