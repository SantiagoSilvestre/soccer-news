package br.com.santiago.teste.soccernews.data.remote.api

import br.com.santiago.teste.soccernews.domain.News
import retrofit2.http.GET

interface SoccerNewsApiRepository {

    @GET("news.json")
    suspend fun getNews(): List<News>
}