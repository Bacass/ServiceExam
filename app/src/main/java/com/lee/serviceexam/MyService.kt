package com.lee.serviceexam

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    val TAG: String = "Lee"

    var mThread: Thread? = null

    var mCount: Int = 0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
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
                    Log.d(TAG, "서비스 동작 중 $mCount")
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

        Log.d(TAG, "서비스 정지")
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
