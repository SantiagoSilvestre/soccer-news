package br.com.santiago.teste.soccernews.mainviewmodel.di

import br.com.santiago.teste.soccernews.mainviewmodel.SoccerNewsServiceViewModel
import br.com.santiago.teste.soccernews.mainviewmodel.SoccerNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {
    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {
        return module {
            viewModel { SoccerNewsViewModel(get()) }
            viewModel { SoccerNewsServiceViewModel(get()) }
        }
    }
}