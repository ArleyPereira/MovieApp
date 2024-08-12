package br.com.hellodev.movieapp.presenter.main.moviedetails.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import br.com.hellodev.movieapp.databinding.FragmentCommentsBinding
import br.com.hellodev.movieapp.presenter.main.moviedetails.adapter.CommentsAdapter
import br.com.hellodev.movieapp.presenter.main.moviedetails.details.MovieDetailsViewModel
import br.com.hellodev.movieapp.util.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels()
    private val viewModel: CommentsViewModel by viewModels()

    private lateinit var commentsAdapter: CommentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
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
                getMovieReviews(movieId)
            }
        }
    }

    private fun initRecycler() {
        commentsAdapter = CommentsAdapter()

        with(binding.recyclerComments) {
            adapter = commentsAdapter
        }
    }

    private fun getMovieReviews(movieId: Int) {
        viewModel.getMovieReviews(movieId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {

                }

                is StateView.Success -> {
                    commentsAdapter.submitList(stateView.data)
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