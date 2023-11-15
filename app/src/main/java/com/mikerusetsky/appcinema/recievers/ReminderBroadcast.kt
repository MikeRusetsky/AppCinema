package com.mikerusetsky.appcinema.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.mikerusetsky.appcinema.domain.Film
import com.mikerusetsky.appcinema.view.notification.NotificationConstants
import com.mikerusetsky.appcinema.view.notification.NotificationHelper

class ReminderBroadcast: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val bundle = intent?.getBundleExtra(NotificationConstants.FILM_BUNDLE_KEY)
        val film: Film = bundle?.get(NotificationConstants.FILM_KEY) as Film

        NotificationHelper.createNotification(context!!, film)
    }
}
