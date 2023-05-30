package com.mikerusetsky.appcinema


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.mikerusetsky.appcinema.databinding.FilmItemBinding

//В конструктор класс передается layout, который мы создали(film_item.xml)
class FilmViewHolder (itemView: View) : ViewHolder(itemView) {

    //Привязываем View из layout к переменным
        val filmBinding = FilmItemBinding.bind(itemView)


    //В этом методе кладем данные из Film в наши View
        fun bind(film: Film) {

        //Устанавливаем заголовок
            filmBinding.title.text = film.title

        //Устанавливаем постер
            //filmBinding.poster.setImageResource(film.poster)

        //Указываем контейнер, в котором будет "жить" наша картинка
        Glide.with(itemView)
            //Загружаем сам ресурс
            .load(film.poster)
            //Центруем изображение
            .centerCrop()
            //Указываем ImageView, куда будем загружать изображение
            .into(filmBinding.poster)

        //Устанавливаем описание
            filmBinding.description.text = film.description
        }
    }
