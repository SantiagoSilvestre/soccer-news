package br.com.santiago.teste.soccernews.data.di

import android.util.Log
import br.com.santiago.teste.soccernews.data.local.SoccerNewsRepository
import br.com.santiago.teste.soccernews.data.local.SoccerNewsRepositoryImpl
import br.com.santiago.teste.soccernews.data.remote.SoccerNewsServiceRepository
import br.com.santiago.teste.soccernews.data.remote.SoccerNewsServiceRepositoryImpl
import br.com.santiago.teste.soccernews.data.remote.api.SoccerNewsApiRepository
import com.google.gson.GsonBuilder
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataModule {
    private const val OK_HTTP = "OkHttp"

    fun load() {
        loadKoinModules(networkModules() + repositoriesModule())
    }

    private fun networkModules(): Module {
        return module {

            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.d(OK_HTTP, it)
                }

                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val requireTls12: ConnectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .build()

                OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.MINUTES)
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .connectionSpecs(listOf(requireTls12))
                    .build()

            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<SoccerNewsApiRepository>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module {
        return module {
            single<SoccerNewsServiceRepository> { SoccerNewsServiceRepositoryImpl(get()) }
            single<SoccerNewsRepository> { SoccerNewsRepositoryImpl(get()) }
        }
    }

    private inline fun <reified T> createService(
        client: OkHttpClient,
        factory: GsonConverterFactory
    ): T {
        return Retrofit.Builder()
            .baseUrl("https://santiagosilvestre.github.io/Soccer-news-api/")
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }
}