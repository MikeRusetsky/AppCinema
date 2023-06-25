package com.mikerusetsky.appcinema.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mikerusetsky.appcinema.R
import com.mikerusetsky.appcinema.databinding.FilmItemBinding
import com.mikerusetsky.appcinema.domain.Film
import com.mikerusetsky.appcinema.view.rv_viewholders.FilmViewHolder

//в параметр передаем слушатель, чтобы мы потом могли обрабатывать нажатия из класса Activity
class FilmListRecyclerAdapter (private val clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //Здесь у нас хранится список элементов для RV
    private val items = mutableListOf<Film>()

    //Этот метод нужно переопределить на возврат количества элементов в списке RV
    override fun getItemCount() = items.size


    //В этом методе мы привязываем наш ViewHolder и передаем туда "надутую" верстку нашего фильма
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(FilmItemBinding.inflate (
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    //В этом методе будет привязка полей из объекта Film к View из film_item.xml
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        //Проверяем какой у нас ViewHolder
        when (holder) {
        is FilmViewHolder -> {

            //Вызываем метод bind(), который мы создали, и передаем туда объект
            //из нашей базы данных с указанием позиции

            holder.bind(items[position])

            //Обрабатываем нажатие на весь элемент целиком(можно сделать на отдельный элемент
            //например, картинку) и вызываем метод нашего листенера, который мы получаем из
            //конструктора адаптера

            holder.itemView.findViewById<CardView>(R.id.item_container).setOnClickListener {
                clickListener.click(items[position])
            }
        }
    }
}

    //Метод для добавления объектов в наш список
    fun addItems(list: List<Film>) {

        items.clear()

        items.addAll(list)

        notifyDataSetChanged()
    }

    //Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(film: Film)
    }
}