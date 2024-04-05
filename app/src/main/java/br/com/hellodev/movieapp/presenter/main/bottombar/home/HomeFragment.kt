package br.com.hellodev.movieapp.presenter.main.bottombar.home;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.hellodev.movieapp.MainGraphDirections
import br.com.hellodev.movieapp.databinding.FragmentHomeBinding
import br.com.hellodev.movieapp.presenter.main.bottombar.home.adapter.GenreMovieAdapter
import br.com.hellodev.movieapp.util.StateView
import br.com.hellodev.movieapp.util.onNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var genreMovieAdapter: GenreMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        initObservers()
    }

    private fun initObservers() {
        viewModel.homeState.observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Error -> {
                    binding.progressBar.isVisible = false
                    binding.recyclerGenres.isVisible = false
                }

                is StateView.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.recyclerGenres.isVisible = false
                }

                is StateView.Success -> {
                    binding.progressBar.isVisible = false
                    binding.recyclerGenres.isVisible = true
                }

            }
        }

        viewModel.movieList.observe(viewLifecycleOwner) { moviesByGenre ->
            genreMovieAdapter.submitList(moviesByGenre)
        }
    }

    private fun initRecycler() {
        genreMovieAdapter = GenreMovieAdapter(
            showAllListener = { genreId, name ->
                val action = HomeFragmentDirections
                    .actionMenuHomeToMovieGenreFragment(genreId, name)
                findNavController().onNavigate(action)
            },
            movieClickListener = { movieId ->
                movieId?.let {
                    val action = MainGraphDirections
                        .actionGlobalMovieDetailsFragment(movieId)
                    findNavController().onNavigate(action)
                }
            }
        )

        with(binding.recyclerGenres) {
            setHasFixedSize(true)
            adapter = genreMovieAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}










