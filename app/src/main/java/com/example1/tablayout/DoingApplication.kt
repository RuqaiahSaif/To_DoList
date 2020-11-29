package com.example1.tablayout

import android.app.Application

class DoingApplication: Application() {
    override fun onCreate() {
        super.onCreate()
            DoingRepositry.initialize(this)
    }
}