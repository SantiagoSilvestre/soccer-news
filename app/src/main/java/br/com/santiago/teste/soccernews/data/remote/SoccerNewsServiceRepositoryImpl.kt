package br.com.santiago.teste.soccernews.data.remote

import br.com.santiago.teste.soccernews.data.remote.api.SoccerNewsApiRepository
import br.com.santiago.teste.soccernews.domain.News

class SoccerNewsServiceRepositoryImpl(private val service: SoccerNewsApiRepository) :
    SoccerNewsServiceRepository {

    override suspend fun getNews(): List<News> {

        return service.getNews()
    }

}