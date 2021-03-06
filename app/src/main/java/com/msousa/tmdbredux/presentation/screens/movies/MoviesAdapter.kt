package com.msousa.tmdbredux.presentation.screens.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msousa.tmdbredux.LayoutResource
import com.msousa.tmdbredux.extensions.loadImageUrl
import com.msousa.tmdbredux.presentation.models.vo.MovieVO
import kotlinx.android.synthetic.main.recycler_movies_item_view.view.*

class MoviesAdapter(
    val action: (String) -> Unit
) : ListAdapter<MovieVO, MoviesAdapter.ViewHolder>(
    diffCallback
) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MovieVO>() {
            override fun areItemsTheSame(oldItem: MovieVO, newItem: MovieVO) = oldItem == newItem

            override fun areContentsTheSame(oldItem: MovieVO, newItem: MovieVO) = oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(LayoutResource.recycler_movies_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: MovieVO) = with(itemView) {
            item.run {
                movieName?.text = title
                movieRate?.text = voteAverage
                moviePoster?.loadImageUrl(url = posterPath, radius = radius)
                moviePoster?.setOnClickListener { action(id) }
            }
        }
    }
}