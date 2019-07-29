package com.lee.serviceexam

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast
import android.R.id.message
import android.os.Handler
import android.os.Looper




/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class MyIntentService : IntentService("MyIntentService") {
    val TAG = "Lee"

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "인텐트 서비스 onHandleIntent")
        for (i in 0..5) {
            try {
                Log.d(TAG, "인텐트 서비스 동작중 $i")
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }

        Handler(Looper.getMainLooper()).post(Runnable {
            Toast.makeText(this@MyIntentService, "인텐트 서비스 동작", Toast.LENGTH_SHORT).show()
        })
    }
}
