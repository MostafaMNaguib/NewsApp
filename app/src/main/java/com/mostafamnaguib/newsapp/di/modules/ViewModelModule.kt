package com.mostafamnaguib.newsapp.di.modules


import com.mostafamnaguib.newsapp.ui.vms.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ArticlesViewModel(get(),get()) }
}