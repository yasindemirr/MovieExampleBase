package com.example.moviesbaseexample.ui.theme.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesbaseexample.databinding.ItemMoviesBinding
import com.example.moviesbaseexample.ui.theme.domain.model.Movie
import com.example.moviesbaseexample.ui.theme.util.extensions.loadImage
import com.example.moviesbaseexample.ui.theme.util.extensions.placeholderProgressBar
import com.example.moviesbaseexample.ui.theme.util.extensions.setSafeOnClickListener

class MovieAdapter(val itemClick: (Movie) -> Unit):PagingDataAdapter<Movie,MovieAdapter.MovieVH>(HomeDiffUtilCallBack()) {

    inner class MovieVH(val binding:ItemMoviesBinding,val context : Context):RecyclerView.ViewHolder(binding.root) {

       fun bind(model : Movie){

           binding.apply {

               movieIv.loadImage(model.posterPath, placeholder= placeholderProgressBar(context))

               println(model.name)


               binding.root.setSafeOnClickListener {

                   itemClick.invoke(model)
               }

           }
       }

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MovieVH {

        return MovieVH(ItemMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false),parent.context)
    }

    override fun onBindViewHolder(holder : MovieVH, position : Int, payloads : MutableList<Any>) {
        if (payloads.firstOrNull() == ITEM_CHANGE) {
            getItem(position)?.let {
                holder.bind(it)
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }

    }

    override fun onBindViewHolder(holder : MovieVH, position : Int) {
        getItem(position)?.let { holder.bind(it) }
    }
    class HomeDiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie,
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie,
        ) = oldItem == newItem

        override fun getChangePayload(oldItem: Movie, newItem: Movie): Any? {
            return if (oldItem.id != newItem.id) {
                ITEM_CHANGE
            } else {
                super.getChangePayload(oldItem, newItem)
            }
        }
    }
}
private const val ITEM_CHANGE = "itemChange"