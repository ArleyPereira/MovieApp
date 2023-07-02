package br.com.hellodev.movieapp.presenter.main.movie_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.hellodev.movieapp.R
import br.com.hellodev.movieapp.databinding.FragmentMovieDetailsBinding
import br.com.hellodev.movieapp.domain.model.Movie
import br.com.hellodev.movieapp.presenter.main.movie_details.adapter.CastAdapter
import br.com.hellodev.movieapp.util.StateView
import br.com.hellodev.movieapp.util.initToolbar
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val viewModel: MovieDetailsViewModel by viewModels()

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var castAdapter: CastAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar = binding.toolbar, lightIcon = true)

        getMovieDetails()

        initRecyclerCredits()
    }

    private fun getMovieDetails() {
        viewModel.getMovieDetails(movieId = args.movieId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {

                }

                is StateView.Success -> {
                    configData(movie = stateView.data)
                }

                is StateView.Error -> {

                }
            }
        }
    }

    private fun initRecyclerCredits() {
        castAdapter = CastAdapter()

        with(binding.recyclerCast) {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = castAdapter
        }
    }

    private fun getCredits() {
        viewModel.getCredits(movieId = args.movieId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {

                }

                is StateView.Success -> {
                    castAdapter.submitList(stateView.data?.cast)
                }

                is StateView.Error -> {

                }
            }
        }
    }

    private fun configData(movie: Movie?) {
        Glide
            .with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
            .into(binding.imageMovie)

        binding.textMovie.text = movie?.title

        binding.textVoteAverage.text = String.format("%.1f", movie?.voteAverage)

        val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
        val data = originalFormat.parse(movie?.releaseDate ?: "")

        val yearFormat = SimpleDateFormat("yyyy", Locale.ROOT)
        val year = yearFormat.format(data)

        binding.textReleaseDate.text = year
        binding.textProductionCountry.text = movie?.productionCountries?.get(0)?.name ?: ""

        val genres = movie?.genres?.map { it.name }?.joinToString(", ")
        binding.textGenres.text = getString(R.string.text_all_genres_movie_details_fragment, genres)

        binding.textDescription.text = movie?.overview

        getCredits()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}