package com.mostafamnaguib.newsapp.di.modules

val appComponent = listOf(
    Modules.modules,
    networkModule,
    viewModelModule,
    repositoryModule,
    useCaseModule
)