package br.com.santiago.teste.soccernews.mainviewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.santiago.teste.soccernews.domain.News
import br.com.santiago.teste.soccernews.domain.usecases.SoccerNewsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SoccerNewsViewModel(
    private val soccerNewsUseCase: SoccerNewsUseCase
) : ViewModel() {

    fun save(news: News) {
        viewModelScope.launch {
            soccerNewsUseCase.loadByTitle(news.title)
                .catch {
                    viewModelScope.launch {
                        soccerNewsUseCase.save(news)
                    }
                }
                .collect{
                    soccerNewsUseCase.save(it)
                }

        }


    }


}