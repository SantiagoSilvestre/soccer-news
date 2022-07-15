package br.com.santiago.teste.soccernews.data.remote

import br.com.santiago.teste.soccernews.domain.News

interface SoccerNewsServiceRepository {

   suspend fun getNews(): List<News>

}
