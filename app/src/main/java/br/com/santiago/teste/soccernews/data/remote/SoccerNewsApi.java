package br.com.santiago.teste.soccernews.data.remote;

import java.util.List;
import br.com.santiago.teste.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerNewsApi {
    @GET("news.json")
    Call<List<News>> getNews();
}
