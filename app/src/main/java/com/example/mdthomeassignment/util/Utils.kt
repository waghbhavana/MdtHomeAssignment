package com.example.mdthomeassignment.util

import android.app.Activity
import android.content.Intent
import android.view.View

fun <A: Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this,activity).also{
        it.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.enable(enabled:Boolean){
    isEnabled=enabled
    alpha = if (enabled) 1f else 0.5f
}