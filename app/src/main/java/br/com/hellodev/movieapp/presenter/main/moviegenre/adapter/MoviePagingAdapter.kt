package br.com.hellodev.movieapp.presenter.main.moviegenre.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.hellodev.movieapp.databinding.MovieGenreItemBinding
import br.com.hellodev.movieapp.domain.model.movie.Movie
import br.com.hellodev.movieapp.util.circularProgressDrawable
import com.bumptech.glide.Glide

class MoviePagingAdapter(
    private val context: Context,
    private val movieClickListener: (Int?) -> Unit
) : PagingDataAdapter<Movie, MoviePagingAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MovieGenreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = getItem(position)

        Glide
            .with(context)
            .load("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
            .placeholder(context.circularProgressDrawable())
            .into(holder.binding.movieImage)

        holder.itemView.setOnClickListener { movieClickListener(movie?.id) }
    }

    inner class MyViewHolder(val binding: MovieGenreItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}