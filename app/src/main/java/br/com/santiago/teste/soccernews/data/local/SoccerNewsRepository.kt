package br.com.santiago.teste.soccernews.data.local

import br.com.santiago.teste.soccernews.domain.News

interface SoccerNewsRepository {

    suspend fun save(news: News)

    suspend fun loadFavoriteNews(): List<News>

    suspend fun loadNews(): List<News>

    suspend fun loadByTitle(title: String): News
}