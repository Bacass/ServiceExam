package com.lee.serviceexam

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lee.serviceexam.databinding.ActivityService03Binding

class ServiceActivity03 : AppCompatActivity() {

    var mBinding: ActivityService03Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_service03)

        setClickListener()
    }

    fun setClickListener() {
        mBinding?.btnStartService?.setOnClickListener {
            startService()
        }
    }

    fun startService() {
        var intent = Intent(this@ServiceActivity03, MyForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }
}
