package br.com.hellodev.movieapp.presenter.main.moviedetails.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.hellodev.movieapp.R
import br.com.hellodev.movieapp.databinding.FragmentMovieDetailsBinding
import br.com.hellodev.movieapp.domain.model.Movie
import br.com.hellodev.movieapp.presenter.main.moviedetails.adapter.CastAdapter
import br.com.hellodev.movieapp.presenter.main.moviedetails.adapter.ViewPagerAdapter
import br.com.hellodev.movieapp.presenter.main.moviedetails.comments.CommentsFragment
import br.com.hellodev.movieapp.presenter.main.moviedetails.similar.SimilarFragment
import br.com.hellodev.movieapp.presenter.main.moviedetails.trailers.TrailersFragment
import br.com.hellodev.movieapp.util.StateView
import br.com.hellodev.movieapp.util.ViewPager2ViewHeightAnimator
import br.com.hellodev.movieapp.util.initToolbar
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val viewModel: MovieDetailsViewModel by activityViewModels()

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

        configTabLayout()
    }

    private fun configTabLayout() {
        viewModel.setMovieId(movieId = args.movieId)

        val adapter = ViewPagerAdapter(requireActivity())
        val mViewPager = ViewPager2ViewHeightAnimator()

        mViewPager.viewPager2 = binding.viewPager
        mViewPager.viewPager2?.adapter = adapter

        adapter.addFragment(
            fragment = TrailersFragment(),
            title = R.string.title_trailers_tab_layout
        )

        adapter.addFragment(
            fragment = SimilarFragment(),
            title = R.string.title_similar_tab_layout
        )

        adapter.addFragment(
            fragment = CommentsFragment(),
            title = R.string.title_comments_tab_layout
        )

        binding.viewPager.offscreenPageLimit = adapter.itemCount

        mViewPager.viewPager2?.let { viewPager ->
            TabLayoutMediator(
                binding.tabs, viewPager
            ) { tab, position ->
                tab.text = getString(adapter.getTitle(position))
            }.attach()
        }
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
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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