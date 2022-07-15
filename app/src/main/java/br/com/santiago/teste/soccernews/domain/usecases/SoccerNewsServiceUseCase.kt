package br.com.santiago.teste.soccernews.domain.usecases

import br.com.santiago.teste.soccernews.data.remote.SoccerNewsServiceRepository
import br.com.santiago.teste.soccernews.domain.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SoccerNewsServiceUseCase(private val soccerNewsServiceRepository: SoccerNewsServiceRepository) {


    fun getNewsFromApi(): Flow<List<News>> {
        return flow {
            emit(soccerNewsServiceRepository.getNews())
        }
    }


}