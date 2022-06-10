package com.example.broadcastrec

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var reciever: BroadcastReceiver
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder : Notification.Builder
    private val CHANNEL_ID = "com.example.broadcastrec"
    private val description = "test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        reciever = AirplaneModeBroadRec {
            alo(it)
        }

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(reciever, it)
        }

//        IntentFilter(Intent.ACTION_BATTERY_LOW).also {
//            registerReceiver(reciever , it)
//        }

//        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
//            registerReceiver(reciever , it)
//        }

    }

    private fun alo(intent: Intent?) {
//        Toast.makeText(this, "info recieved : ${intent?.extras}", Toast.LENGTH_LONG).show()
        val bundle = intent?.extras
        val state = bundle?.getBoolean("state")
        var airplaneMode = ""
        airplaneMode = if (state!!){
            "airplane mode is on"
        }else {
            "airplane mode is off"
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(CHANNEL_ID , description , NotificationManager.IMPORTANCE_HIGH )
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this,CHANNEL_ID)
                .setContentTitle("airplane mode")
                .setContentText("$airplaneMode")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources , R.mipmap.ic_launcher))

        }else {
            builder = Notification.Builder(this)
                .setContentTitle("airplane mode")
                .setContentText("$airplaneMode")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources , R.mipmap.ic_launcher))
        }
        notificationManager.notify(1234 , builder.build())
        aloooo.text = "${intent?.extras}"
    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(reciever)

    }
}