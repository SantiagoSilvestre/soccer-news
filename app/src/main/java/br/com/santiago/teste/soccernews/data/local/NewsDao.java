package br.com.santiago.teste.soccernews.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.santiago.teste.soccernews.domain.News;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(News... news);

    @Query("SELECT * FROM news WHERE favorite = 1")
    List<News> loadFavoriteNews();

    @Query("SELECT * FROM news")
    List<News> loadNews();

    @Query("SELECT * FROM news WHERE title = :title")
    News loadByTitle(String title);
}
