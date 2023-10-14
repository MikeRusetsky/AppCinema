package com.mikerusetsky.appcinema.view.notification

import android.Manifest
import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.mikerusetsky.appcinema.R
import com.mikerusetsky.appcinema.domain.Film
import com.mikerusetsky.appcinema.view.MainActivity
import com.mikerusetsky.remote_module.entity.ApiConstants

object NotificationHelper {
    fun createNotification(context: Context, film: Film) {

        val mIntent = Intent(context, MainActivity::class.java)

        val pendingIntent: PendingIntent = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(context,
                0, mIntent, PendingIntent
                    .FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity (context,
                0,mIntent, PendingIntent
                    .FLAG_UPDATE_CURRENT)
        }

        val builder = NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID).apply {
            setSmallIcon(R.drawable.baseline_watch_later_24)
            setContentTitle("Не забудьте посмотреть!")
            setContentText(film.title)
            priority = NotificationCompat.PRIORITY_DEFAULT
            setContentIntent(pendingIntent)
            setAutoCancel(true)
        }

        val notificationManager = NotificationManagerCompat.from(context)

        Glide.with(context)
            //говорим что нужен битмап
            .asBitmap()
            //указываем откуда загружать, это ссылка как на загрузку с API
            .load(ApiConstants.IMAGES_URL + "w500" + film.poster)
            .into(object : CustomTarget<Bitmap>() {

                //Этот коллбэк отрабатоет когда мы успешно получим битмап
                override fun onLoadCleared(placeholder: Drawable?) {
                }

                //Этот коллбэк отрабатоет когда мы успешно получим битмап
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    //Создаем нотификации в стиле big picture
                    builder.setStyle(NotificationCompat.BigPictureStyle().bigPicture(resource))
                    //Обновляем нотификацю
                    if (ActivityCompat.checkSelfPermission(context,
                            Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(context as Activity,
                            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                            101)
                        return
                    }
                    notificationManager.notify(film.id, builder.build())
                }
            })
        //Отправляем изначальную нотификацю в стандартном исполнении
        notificationManager.notify(film.id, builder.build())
    }
}