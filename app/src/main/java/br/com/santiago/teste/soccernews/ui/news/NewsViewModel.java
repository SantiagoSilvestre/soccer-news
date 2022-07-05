package br.com.santiago.teste.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.santiago.teste.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> mNews;

    public NewsViewModel() {
        mNews = new MutableLiveData<>();

        //TODO remover mock de notícias
        List<News> newsList = new ArrayList<>();
        String descptinMock = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content";
        newsList.add(new News("Ferroviária tem desfalque importante", descptinMock));
        newsList.add(new News("Ferrinha joga no sábado", descptinMock));
        newsList.add(new News("Copa do Mundo Feminina está terminando", descptinMock));

        mNews.setValue(newsList);
    }

    public LiveData<List<News>> getNews() {
        return mNews;
    }
}