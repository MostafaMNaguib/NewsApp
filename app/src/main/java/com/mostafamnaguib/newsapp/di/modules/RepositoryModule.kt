package com.mostafamnaguib.newsapp.di.modules

import com.mostafamnaguib.newsapp.data.repos.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        NewsRepository(get())
    }
}