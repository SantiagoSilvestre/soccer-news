package br.com.santiago.teste.soccernews.domain.di

import br.com.santiago.teste.soccernews.domain.usecases.SoccerNewsServiceUseCase
import br.com.santiago.teste.soccernews.domain.usecases.SoccerNewsUseCase
import br.com.santiago.teste.soccernews.mainviewmodel.SoccerNewsServiceViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory { SoccerNewsUseCase(get()) }
            factory { SoccerNewsServiceUseCase(get()) }
        }
    }

}