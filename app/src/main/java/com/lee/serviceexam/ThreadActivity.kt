package com.lee.serviceexam

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.lee.serviceexam.databinding.ActivityMainBinding

class ThreadActivity : AppCompatActivity() {
    val TAG: String = "Lee"

    var mBinding: ActivityMainBinding? = null
    var mThread: Thread? = null

    var mCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_thread)

        setClickListener()
    }

    override fun onDestroy() {
        /**
         * 쓰레드를 종료하지 않고 back키를 눌러서 앱을 종료 후 다시 앱을 실행해도
         * 기존의 쓰레드는 종료되지 않는 현상이 있다.
         * 이게 쓰레드의 문제점이다.
         */
        // stopThread()

        super.onDestroy()
    }

    fun setClickListener() {
        mBinding?.btnStartThread?.setOnClickListener {
            startThread()
        }

        mBinding?.btnStopThread?.setOnClickListener {
            stopThread()
        }
    }

    fun startThread() {
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
    }

    fun stopThread() {
        mThread?.interrupt()
        mThread = null
        mCount = 0
    }
}
