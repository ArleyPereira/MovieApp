package br.com.hellodev.movieapp.presenter.main.moviegenre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import br.com.hellodev.movieapp.R
import br.com.hellodev.movieapp.databinding.FragmentMovieGenreBinding
import br.com.hellodev.movieapp.presenter.main.bottombar.home.adapter.MovieAdapter
import br.com.hellodev.movieapp.util.StateView
import br.com.hellodev.movieapp.util.initToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieGenreFragment : Fragment() {

    private var _binding: FragmentMovieGenreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieGenreViewModel by viewModels()

    private val args : MovieGenreFragmentArgs by navArgs()
    private lateinit var movieAdapter: MovieAdapter

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
        binding.textTitle.text = args.name

        initRecycler()

        getMoviesByGenre()
    }

    private fun initRecycler() {
        movieAdapter = MovieAdapter(
            context = requireContext(),
            layoutInflater = R.layout.movie_genre_item
        )

        with(binding.recyclerMovies) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun getMoviesByGenre() {
        viewModel.getMoviesByGenre(args.genreId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is StateView.Success -> {
                    binding.progressBar.isVisible = false
                    movieAdapter.submitList(stateView.data)
                }

                is StateView.Error -> {
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}