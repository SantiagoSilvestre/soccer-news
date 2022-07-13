package br.com.santiago.teste.soccernews.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import br.com.santiago.teste.soccernews.MainActivity;
import br.com.santiago.teste.soccernews.databinding.FragmentFavoritesBinding;
import br.com.santiago.teste.soccernews.domain.News;
import br.com.santiago.teste.soccernews.ui.adapter.NewsAdapter;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel dashboardViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;
        loadFavoritesNews(activity);


        return binding.getRoot();
    }

    private void loadFavoritesNews(MainActivity activity) {
        assert activity != null;
        List<News> favoritesNews = activity.getDb().newsDao().loadFavoriteNews();
        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvNews.setAdapter(new NewsAdapter(favoritesNews,
                updateNews -> {
                    activity.getDb().newsDao().save(updateNews);
                    loadFavoritesNews(activity);
                }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}