package br.com.santiago.teste.soccernews.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.santiago.teste.soccernews.databinding.FragmentFavoritesBinding
import br.com.santiago.teste.soccernews.domain.News
import br.com.santiago.teste.soccernews.mainviewmodel.SoccerNewsViewModel
import br.com.santiago.teste.soccernews.ui.adapter.ListernerAdapter
import br.com.santiago.teste.soccernews.ui.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {
    private val binding by lazy { FragmentFavoritesBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<SoccerNewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding.rvNews.layoutManager = LinearLayoutManager(context)
        viewModel.listFavorites()

        viewModel.data.observe(viewLifecycleOwner) { news ->
            binding.rvNews.adapter = NewsAdapter(news, object : ListernerAdapter<News> {
                override fun onClick(t: News) {
                    viewModel.save(t)
                    viewModel.listFavorites()
                }

            })
        }

        return binding.root
    }
}