package br.com.santiago.teste.soccernews.mainviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.santiago.teste.soccernews.domain.News
import br.com.santiago.teste.soccernews.domain.usecases.SoccerNewsServiceUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SoccerNewsServiceViewModel(
    private val soccerNewsServiceUseCase: SoccerNewsServiceUseCase
) : ViewModel() {

    private val _data = MutableLiveData<List<News>>()
    val data: LiveData<List<News>> = _data


    fun getNewsApi() {
        viewModelScope.launch {
            soccerNewsServiceUseCase.getNewsFromApi().onStart {
            }.catch {
            }.collect {
                _data.postValue(it)
            }
        }
    }


}