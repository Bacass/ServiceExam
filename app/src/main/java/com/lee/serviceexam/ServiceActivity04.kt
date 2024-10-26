package com.lee.serviceexam

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import com.lee.serviceexam.databinding.ActivityService04Binding

class ServiceActivity04 : AppCompatActivity() {

    var mService: MyBindService? = null
    var isBind: Boolean? = false

    var mBinding: ActivityService04Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_service04)

        setClickListener()
    }

    override fun onStart() {
        super.onStart()

        // 액티비티가 시작하자마자 MyBindService 와 현재 액티비티를 바인딩 해준다.
        // 서비스는 시작하지 않았어도 MyBindService 와 우선 바인딩은 된다.
        Toast.makeText(this, "MyBindService 와 현재 액티비티를 바인딩 해준다.", Toast.LENGTH_SHORT).show()
        var intent = Intent(this, MyBindService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()

        if (isBind == true) {
            Toast.makeText(this, "unbindService 호출.", Toast.LENGTH_SHORT).show()
            unbindService(mConnection)
            isBind = false
        }
    }

    fun setClickListener() {
        mBinding?.btnStartService?.setOnClickListener {
            startService()
        }

        mBinding?.btnStopService?.setOnClickListener {
            stopService()
        }

        mBinding?.btnGetCount?.setOnClickListener {
            getCountValue()
        }
    }

    fun startService() {
        var intent = Intent(this@ServiceActivity04, MyBindService::class.java)
        startService(intent)
    }

    fun stopService() {
        Toast.makeText(this, "[서비스 정지] 를 눌러도 서비스가 종료되지 않는다.", Toast.LENGTH_SHORT).show()
        var intent = Intent(this@ServiceActivity04, MyBindService::class.java)
        stopService(intent)
    }

    fun getCountValue() {
        if (isBind == true) {
            Toast.makeText(this, "현재 카운팅 : ${mService?.getCount()}", Toast.LENGTH_SHORT).show();
        }
    }

    var mConnection = object: ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            // OS 에 의해서 얘기치 않은 종료가 되었을때 이곳이 호출된다.
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            var binder = service as MyBindService.MyBinder
            mService = binder.service
            isBind = true
        }
    }
}
