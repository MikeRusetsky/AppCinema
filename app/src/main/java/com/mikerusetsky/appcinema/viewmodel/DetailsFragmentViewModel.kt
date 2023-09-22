package com.mikerusetsky.appcinema.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DetailsFragmentViewModel : ViewModel() {

    //Помечаем функцию как suspend, потому как у нас будет логика показа Прогресс-бара,
    // а также тоста в конце, и нам необходима прерывающаяся функция, которая будет возвращать объект Bitmap.
    suspend fun loadWallpaper(url: String): Bitmap {


        return suspendCoroutine {

            //загружаем файл из Сети.
            // В URL из представления мы будем передавать адрес картинки.
            val url = URL(url)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            it.resume(bitmap)
        }
    }
}