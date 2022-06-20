package com.mostafamnaguib.newsapp.di.modules

import com.mostafamnaguib.newsapp.data.usecases.GetArticlesUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetArticlesUseCase(get()) }


}