package com.example.broadcastrec

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeBroadRec(val p :(Intent?) -> Unit) : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneEnabled = intent?.getBooleanExtra("state" , false) ?: return
//        val isAir : Boolean = intent.action?.intern() == Intent.ACTION_AIRPLANE_MODE_CHANGED
//        val anyAction = intent.action == Intent.ACTION_POWER_CONNECTED

            p.invoke(intent)

//        if (anyAction) {
//            Toast.makeText(context , "Power is charging", Toast.LENGTH_LONG).show()
//        }

        if (isAirplaneEnabled){
            Toast.makeText(context , "Airplane mode is enabled", Toast.LENGTH_LONG).show()
        }else {
            Toast.makeText(context , "Airplane mode is disabled", Toast.LENGTH_LONG).show()
        }

//        Toast.makeText(context , "PUTIN XUILO" , Toast.LENGTH_LONG).show()
    }


}