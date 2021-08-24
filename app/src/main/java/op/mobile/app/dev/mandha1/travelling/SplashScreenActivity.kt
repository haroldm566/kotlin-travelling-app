package op.mobile.app.dev.mandha1.travelling

package com.kotlincodes.splashscreenwithkotlin

import androidx.navigation.findNavController
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import op.mobile.app.dev.mandha1.travelling.R
import android.content.Intent
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    private val splashtimeout:Long=3000 // 3 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            val action = SplashScreenFragmentDirections
                .actionSplashScreenFragmentToLoginFragment()
            view?.findNavController()?.navigate(action) // Calling the navigation action declared in mobile_navigation.xml

        }, splashtimeout)
    }
}