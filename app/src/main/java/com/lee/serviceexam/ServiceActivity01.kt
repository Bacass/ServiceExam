package com.lee.serviceexam

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lee.serviceexam.databinding.ActivityService01Binding

class ServiceActivity01 : AppCompatActivity() {

    var mBinding: ActivityService01Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_service01)

        setClickListener()
    }

    fun setClickListener() {
        mBinding?.btnStartService?.setOnClickListener {
            startService()
        }

        mBinding?.btnStopService?.setOnClickListener {
            stopService()
        }
    }

    fun startService() {
        var intent = Intent(this@ServiceActivity01, MyService::class.java)
        startService(intent)
    }

    fun stopService() {
        var intent = Intent(this@ServiceActivity01, MyService::class.java)
        stopService(intent)
    }
}
