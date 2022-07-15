package br.com.santiago.teste.soccernews

import android.app.Application
import br.com.santiago.teste.soccernews.data.di.DataModule
import br.com.santiago.teste.soccernews.domain.di.DomainModule
import br.com.santiago.teste.soccernews.mainviewmodel.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application()  {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()

    }

}