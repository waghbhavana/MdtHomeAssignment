package com.example.mdthomeassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.mdthomeassignment.data.UserPreferences
import com.example.mdthomeassignment.ui.auth.AuthActivity
import com.example.mdthomeassignment.ui.dashbord.DashboardActivity
import com.example.mdthomeassignment.util.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userPreferences = UserPreferences(this);
        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity =
                if (it == null) AuthActivity::class.java else DashboardActivity::class.java
            startNewActivity(activity)
        })


    }
}