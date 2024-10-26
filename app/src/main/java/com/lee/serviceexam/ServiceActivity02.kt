package com.lee.serviceexam

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lee.serviceexam.databinding.ActivityService02Binding

class ServiceActivity02 : AppCompatActivity() {

    var mBinding: ActivityService02Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_service02)

        setClickListener()
    }

    fun setClickListener() {
        mBinding?.btnStartService?.setOnClickListener {
            startService()
        }
    }

    fun startService() {
        var intent = Intent(this@ServiceActivity02, MyIntentService::class.java)
        startService(intent)
    }

}
