package br.com.hellodev.movieapp.presenter.main.bottombar.download.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.hellodev.movieapp.databinding.MovieDownloadItemBinding
import br.com.hellodev.movieapp.domain.model.Movie
import com.bumptech.glide.Glide

class DownloadMovieAdapter(
    private val context: Context,
    private val detailsClickListener: (Int?) -> Unit,
    private val deleteClickListener: (Int?) -> Unit
) : ListAdapter<Movie, DownloadMovieAdapter.MyViewHolder>(DIFF_CALLBACK) {

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
            MovieDownloadItemBinding.inflate(
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
            .load("https://image.tmdb.org/t/p/w200${movie.posterPath}")
            .into(holder.binding.ivMovie)

        holder.binding.textMovie.text = movie.title
        holder.binding.textDuration.text = movie.runtime.toString()
        holder.binding.textSize.text = movie.runtime.toString()
        holder.binding.ibDelete.setOnClickListener { deleteClickListener(movie.id) }

        holder.itemView.setOnClickListener { detailsClickListener(movie.id) }
    }

    inner class MyViewHolder(val binding: MovieDownloadItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}