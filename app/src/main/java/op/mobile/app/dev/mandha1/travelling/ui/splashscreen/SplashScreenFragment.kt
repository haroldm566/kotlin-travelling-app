package op.mobile.app.dev.mandha1.travelling.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import op.mobile.app.dev.mandha1.travelling.R

class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        Handler().postDelayed({
            view?.post {
                view?.findNavController()
                    ?.navigate(R.id.action_splash_screen_fragment_to_login_fragment) // Calling the navigation action declared in mobile_navigation.xml
            }
        }, 3000)

        return view
    }
}