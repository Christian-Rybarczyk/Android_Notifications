package com.rybarstudios.androidnotifications.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.rybarstudios.androidnotifications.ui.MainActivity
import com.rybarstudios.androidnotifications.R
import com.rybarstudios.androidnotifications.ui.FullscreenActivity

object NotificationGenerator {

    fun getNotification(context: Context) {
        val intent = Intent(context, FullscreenActivity::class.java)
        intent.putExtra("object", "Notification Launched")
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val actionIntent = Intent(context, FullscreenActivity::class.java)
        actionIntent.putExtra("object", "Action intent displayed!")
        val actionPendingIntent = PendingIntent.getActivity(context, 22, actionIntent, PendingIntent.FLAG_ONE_SHOT)


        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "${context.packageName}.simplenotification"

        //Create required notification channel for versions O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val description = "Notification description goes here"

            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description

            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
            .setContentTitle("Content title goes here")
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setColor(context.resources.getColor(R.color.colorAccent))
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentIntent(pendingIntent)
            .addAction(android.R.drawable.ic_menu_agenda, "Action intent title", actionPendingIntent)
        notificationManager.notify(MainActivity.NOTIFICATION_ID, builder.build())
    }
}