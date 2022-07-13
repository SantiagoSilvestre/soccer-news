package br.com.santiago.teste.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import br.com.santiago.teste.soccernews.MainActivity;
import br.com.santiago.teste.soccernews.databinding.FragmentNewsBinding;
import br.com.santiago.teste.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsViewModel newsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        observer();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void observer() {
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news ->
                binding.rvNews.setAdapter(new NewsAdapter(news,
                        updateNews -> {
                            MainActivity activity = (MainActivity) getActivity();
                            assert activity != null;
                            activity.getDb().newsDao().save(updateNews);
                        }
                )));


        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case DOING:
                    // TODO: INCLUIR SwipeRefreshLayout (loading)
                    break;
                case DONE:
                    // TODO: finalizar SwipeRefreshLayout (loading)
                    break;
                case ERROR:
                    // TODO: finalizar SwipeRefreshLayout (loading)
                    // TODO: mostrar error genérico
            }
        });
    }
}