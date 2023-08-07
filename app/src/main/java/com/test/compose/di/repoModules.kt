package com.test.compose.di

import com.test.compose.repo.LocalRepo
import com.test.compose.repo.RemoteRepo
import org.koin.dsl.module

val localRepo = module {
    single { LocalRepo() }
}

val remoteRepo = module {
    single { RemoteRepo() }
}