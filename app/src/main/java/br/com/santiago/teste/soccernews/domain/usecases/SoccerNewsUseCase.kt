package br.com.santiago.teste.soccernews.domain.usecases

import br.com.santiago.teste.soccernews.data.local.SoccerNewsRepository
import br.com.santiago.teste.soccernews.domain.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SoccerNewsUseCase(private val soccerNewsRepository: SoccerNewsRepository) {
    suspend fun save(news: News) {
        soccerNewsRepository.save(news)
    }

    fun loadByTitle(title: String): Flow<News> = flow {
        emit(soccerNewsRepository.loadByTitle(title))
    }

    fun loadNews(): Flow<List<News>> = flow {
        emit(soccerNewsRepository.loadFavoriteNews())
    }
}