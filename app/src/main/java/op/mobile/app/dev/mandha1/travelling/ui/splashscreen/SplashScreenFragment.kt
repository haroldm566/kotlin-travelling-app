package op.mobile.app.dev.mandha1.travelling.ui.splashscreen
package com.kotlincodes.splashscreenwithkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import op.mobile.app.dev.mandha1.travelling.R
import android.content.Intent
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class SplashScreenFragment : Fragment() {
    private val SPLASH_TIME_OUT:Long=3000 // 3 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            val action = SplashScreenFragmentDirections
                .actionSplashScreenFragmentToLoginFragment()
            view?.findNavController()?.navigate(action) // Calling the navigation action declared in mobile_navigation.xml

        }, SPLASH_TIME_OUT)
    }
}