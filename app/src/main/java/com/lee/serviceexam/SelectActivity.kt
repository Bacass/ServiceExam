package com.lee.serviceexam

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lee.serviceexam.databinding.ActivitySelectBinding

class SelectActivity : AppCompatActivity() {

    var mBinding: ActivitySelectBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select)

        mBinding?.btnThreadActivity?.setOnClickListener {
            val intent = Intent(this@SelectActivity, ThreadActivity::class.java)
            startActivity(intent)
        }

        mBinding?.btnServiceActivity?.setOnClickListener {
            val intent = Intent(this@SelectActivity, ServiceActivity01::class.java)
            startActivity(intent)
        }
    }
}
