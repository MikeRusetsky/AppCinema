package com.mikerusetsky.appcinema


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mikerusetsky.appcinema.databinding.FilmItemBinding

class FilmViewHolder (itemView: View) : ViewHolder(itemView) {

        val filmBinding = FilmItemBinding.bind(itemView)

        fun bind(film: Film) {

            filmBinding.title.text = film.title

            filmBinding.poster.setImageResource(film.poster)

            filmBinding.description.text = film.description
        }
    }
