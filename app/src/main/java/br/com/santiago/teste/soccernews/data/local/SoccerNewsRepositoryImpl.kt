package br.com.santiago.teste.soccernews.data.local

import android.content.Context
import br.com.santiago.teste.soccernews.domain.News

class SoccerNewsRepositoryImpl(val context: Context) : SoccerNewsRepository {

    private val mNewsDatabase =
        AppDatabase.getDatabase(context).newsDao()


    override suspend fun save(news: News) {
        Thread {
            mNewsDatabase.save(news)
        }.run()
    }

    override suspend fun loadFavoriteNews(): List<News> {
        return mNewsDatabase.loadFavoriteNews()
    }

    override suspend fun loadByTitle(title: String): News {
        return mNewsDatabase.loadByTitle(title)
    }
}