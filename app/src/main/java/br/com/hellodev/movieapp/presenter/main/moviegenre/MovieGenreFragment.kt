package br.com.hellodev.movieapp.presenter.main.moviegenre

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import br.com.hellodev.movieapp.MainGraphDirections
import br.com.hellodev.movieapp.R
import br.com.hellodev.movieapp.databinding.FragmentMovieGenreBinding
import br.com.hellodev.movieapp.presenter.main.moviegenre.adapter.LoadStatePagingAdapter
import br.com.hellodev.movieapp.presenter.main.moviegenre.adapter.MoviePagingAdapter
import br.com.hellodev.movieapp.util.hideKeyboard
import br.com.hellodev.movieapp.util.initToolbar
import br.com.hellodev.movieapp.util.onNavigate
import com.ferfalk.simplesearchview.SimpleSearchView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieGenreFragment : Fragment() {

    private var _binding: FragmentMovieGenreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieGenreViewModel by viewModels()

    private val args: MovieGenreFragmentArgs by navArgs()
    private lateinit var moviePagingAdapter: MoviePagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar = binding.toolbar)
        binding.toolbar.title = args.name

        initRecycler()

        getMoviesByGenrePagination()

        initSearchView()
    }

    private fun initRecycler() {
        moviePagingAdapter = MoviePagingAdapter(
            context = requireContext(),
            movieClickListener = { movieId ->
                movieId?.let {
                    val action = MainGraphDirections
                        .actionGlobalMovieDetailsFragment(movieId)
                    findNavController().onNavigate(action)
                }
            }
        )

        lifecycleScope.launch {
            moviePagingAdapter.loadStateFlow.collectLatest { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        binding.recyclerMovies.isVisible = false
                        binding.shimmer.startShimmer()
                        binding.shimmer.isVisible = true
                    }

                    is LoadState.NotLoading -> {
                        binding.shimmer.stopShimmer()
                        binding.shimmer.isVisible = false
                        binding.recyclerMovies.isVisible = true
                    }

                    is LoadState.Error -> {
                        binding.recyclerMovies.isVisible = false
                        binding.shimmer.stopShimmer()
                        binding.shimmer.isVisible = false
                        val error = (loadState.refresh as LoadState.Error).error.message
                            ?: "Ocorreu um erro. Tente novamente mais tarde."
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        with(binding.recyclerMovies) {
            setHasFixedSize(true)

            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            layoutManager = gridLayoutManager

            val footerAdapter = moviePagingAdapter.withLoadStateFooter(
                footer = LoadStatePagingAdapter()
            )

            adapter = footerAdapter

            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == moviePagingAdapter.itemCount && footerAdapter.itemCount > 0) {
                        2
                    } else {
                        1
                    }
                }
            }
        }
    }

    private fun initSearchView() {
        binding.simpleSearchView.setOnQueryTextListener(object :
            SimpleSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                hideKeyboard()
                if (query.isNotEmpty()) {
                    searchMovies(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.d("SimpleSearchView", "Text changed:$newText")
                return false
            }

            override fun onQueryTextCleared(): Boolean {
                Log.d("SimpleSearchView", "Text cleared")
                return false
            }
        })

        binding.simpleSearchView.setOnSearchViewListener(object :
            SimpleSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                Log.d("SimpleSearchView", "onSearchViewShown")
            }

            override fun onSearchViewClosed() {
                getMoviesByGenrePagination()
            }

            override fun onSearchViewShownAnimation() {
                Log.d("SimpleSearchView", "onSearchViewShownAnimation")
            }

            override fun onSearchViewClosedAnimation() {
                Log.d("SimpleSearchView", "onSearchViewClosedAnimation")
            }
        })
    }

    private fun getMoviesByGenrePagination(forceRequest: Boolean = false) {
        lifecycleScope.launch {
            viewModel.getMoviesByGenrePagination(genreId = args.genreId, forceRequest = forceRequest)
            viewModel.movieList.collectLatest { pagingData ->
                moviePagingAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
            }
        }
    }

    private fun searchMovies(query: String?) {
        lifecycleScope.launch {
            viewModel.searchMovies(query).collectLatest { pagingData ->
                moviePagingAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search_view, menu)
        val item = menu.findItem(R.id.action_search)
        binding.simpleSearchView.setMenuItem(item)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}