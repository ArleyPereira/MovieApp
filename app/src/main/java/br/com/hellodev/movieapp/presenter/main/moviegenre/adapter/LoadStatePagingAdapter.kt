package br.com.hellodev.movieapp.presenter.main.moviegenre.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.hellodev.movieapp.databinding.LoadStatePagingAdapterBinding

class LoadStatePagingAdapter : LoadStateAdapter<LoadStatePagingAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.binding.apply {
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LoadStatePagingAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class LoadStateViewHolder(val binding: LoadStatePagingAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

}