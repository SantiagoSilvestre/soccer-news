package br.com.santiago.teste.soccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.santiago.teste.soccernews.databinding.FragmentNewsBinding
import br.com.santiago.teste.soccernews.domain.News
import br.com.santiago.teste.soccernews.mainviewmodel.SoccerNewsServiceViewModel
import br.com.santiago.teste.soccernews.mainviewmodel.SoccerNewsViewModel
import br.com.santiago.teste.soccernews.ui.adapter.ListernerAdapter
import br.com.santiago.teste.soccernews.ui.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {
    private val binding by lazy { FragmentNewsBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<SoccerNewsViewModel>()
    private val viewModelService by viewModel<SoccerNewsServiceViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val root: View = binding.root
        binding.rvNews.layoutManager = LinearLayoutManager(context)
        viewModelService.getNewsApi()
        observer()
        return root
    }

    private fun observer() {

        viewModelService.data.observe(viewLifecycleOwner) { news ->
            binding.rvNews.adapter = NewsAdapter(news, object : ListernerAdapter<News> {
                override fun onClick(t: News) {
                    viewModel.save(t)
                }

            })
        }

        viewModelService.getState().observe(viewLifecycleOwner) { state ->
            when (state) {
                SoccerNewsServiceViewModel.State.DOING -> {}
                SoccerNewsServiceViewModel.State.ERROR -> {}
                SoccerNewsServiceViewModel.State.DONE -> {}
            }
        }
    }
}