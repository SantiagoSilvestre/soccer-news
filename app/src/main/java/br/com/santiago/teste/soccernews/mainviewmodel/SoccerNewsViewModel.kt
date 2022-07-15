package br.com.santiago.teste.soccernews.mainviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.santiago.teste.soccernews.domain.News
import br.com.santiago.teste.soccernews.domain.usecases.SoccerNewsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SoccerNewsViewModel(
    private val soccerNewsUseCase: SoccerNewsUseCase
) : ViewModel() {

    private val _data = MutableLiveData<List<News>>()
    val data: LiveData<List<News>> = _data

    fun save(news: News) {
        viewModelScope.launch {
            soccerNewsUseCase.loadByTitle(news.title)
                .catch {
                    viewModelScope.launch {
                        soccerNewsUseCase.save(news)
                    }
                }
                .collect {
                    it.favorite = news.favorite
                    soccerNewsUseCase.save(it)
                }

        }
    }

    fun saveFromApi(listNews: List<News>) {
        listNews.forEach { news ->
            viewModelScope.launch {
                soccerNewsUseCase.saveFromApi(news)
            }
        }
        viewModelScope.launch {
            soccerNewsUseCase.loadNews().collect{
                _data.postValue(it)
            }
        }
    }

    fun listFavorites() {
        viewModelScope.launch {
            soccerNewsUseCase.loadFavoriteNews()
                .onStart {
                }.catch {
                }.collect {
                    _data.postValue(it)
                }
        }
    }
}