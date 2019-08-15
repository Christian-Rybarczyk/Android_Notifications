package com.rybarstudios.androidnotifications.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rybarstudios.androidnotifications.R
import com.rybarstudios.androidnotifications.util.NotificationGenerator.getNotification
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NOTIFICATION_ID = 42
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_get_notification.setOnClickListener {
            getNotification(this)
        }
    }
}
