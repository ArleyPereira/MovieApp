package br.com.hellodev.movieapp.presenter.main.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import br.com.hellodev.movieapp.MainGraphDirections
import br.com.hellodev.movieapp.R
import br.com.hellodev.movieapp.databinding.FragmentSimilarBinding
import br.com.hellodev.movieapp.presenter.main.bottombar.home.adapter.MovieAdapter
import br.com.hellodev.movieapp.util.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SimilarFragment : Fragment() {

    private var _binding: FragmentSimilarBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels()

    private val similarViewModel: SimilarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimilarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        initObservers()
    }

    private fun initObservers() {
        movieDetailsViewModel.movieId.observe(viewLifecycleOwner) { movieId ->
            if (movieId > 0) {
                getSimilar(movieId)
            }
        }
    }

    private fun initRecycler() {
        movieAdapter = MovieAdapter(
            context = requireContext(),
            layoutInflater = R.layout.movie_genre_item,
            movieClickListener = { movieId ->
                movieId?.let {
                    val action = MainGraphDirections
                        .actionGlobalMovieDetailsFragment(movieId)
                    findNavController().navigate(action)
                }
            }
        )

        val lm = GridLayoutManager(requireContext(), 2)

        with(binding.recyclerMovies) {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun getSimilar(movieId: Int) {
        similarViewModel.getSimilar(movieId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {

                }

                is StateView.Success -> {
                    movieAdapter.submitList(stateView.data)
                }

                is StateView.Error -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}