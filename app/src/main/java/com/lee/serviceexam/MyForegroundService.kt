package com.lee.serviceexam

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.app.PendingIntent
import android.content.Context
import android.support.v4.app.NotificationCompat



class MyForegroundService : Service() {

    val TAG: String = "Lee"

    var mThread: Thread? = null

    var mCount: Int = 0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundService()

        mThread = object : Thread("My Thread") {
            override fun run() {
                for (i in 0..99) {
                    try {
                        mCount++
                        // 1초 마다 쉬기
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        // 스레드에 인터럽트가 걸리면
                        // 오래 걸리는 처리 종료
                        break
                    }

                    // 1초 마다 로그 남기기
                    Log.d(TAG, "포그라운드 서비스 동작 중 $mCount")
                }
            }
        }
        mThread?.start()

        /**
         * START_STICKY
         * Service가 강제 종료되었을 경우 시스템이 다시 Service를 재시작 시켜 주지만
         * intent 값을 null로 초기화 시켜서 재시작 합니다.
         */
        return START_STICKY
    }

    override fun onDestroy() {
        /**
         * 서비스가 정지되면 onDestroy 가 호출되므로 이곳에서 쓰레드를 정지시켜 준다.
         */
        mThread?.interrupt()
        mThread = null
        mCount = 0

        Log.d(TAG, "포그라운드 서비스 정지")
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private fun startForegroundService() {
        // default 채널 ID로 알림 생성
        val builder = NotificationCompat.Builder(this, "channelID_10294")
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentTitle("포그라운드 서비스")
        builder.setContentText("포그라운드 서비스 실행 중")

        // 노티를 클릭했을때의 처리를 위한 pendingIntent
        val notificationIntent = Intent(this, ServiceActivity03::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        builder.setContentIntent(pendingIntent)

        // 오레오에서는 알림 채널을 매니저에 생성해야 한다
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(
                NotificationChannel(
                    "channelID_10294",
                    "기본채널",
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    vibrationPattern = longArrayOf(0)
                    enableVibration(true)
                }
            )
        }

        // 포그라운드로 시작
        // id 가 절대 0이면 안된다.
        // 액티비티에서 startForegroundService() 를 호출후 이것이 5초이내에 실행되지 않으면 ANR이 발생한다.
        startForeground(1, builder.build())
    }
}
