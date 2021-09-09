package op.mobile.app.dev.mandha1.travelling.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.os.*
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentLoginBinding
import op.mobile.app.dev.mandha1.travelling.login.LoginAdapter
import op.mobile.app.dev.mandha1.travelling.login.LoginApplication
import op.mobile.app.dev.mandha1.travelling.model.Login

class LoginFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        //return inflater.inflate(R.layout.fragment_login, container, false)

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val btnLogin: Button = view.findViewById(R.id.btn_login)
        val emailAddressEdtTxt: EditText = view.findViewById(R.id.et_email_address)
        val passwordEdtTxt: EditText = view.findViewById(R.id.et_password)

        // This is an example of an on click listener bound to a Button
        btnLogin.setOnClickListener {
            if (emailAddressEdtTxt.text.toString() == "a@email.com"
                && passwordEdtTxt.text.toString() == "a"
            ) {
                // Get the action specified in mobile_navigation.xml
                val action = LoginFragmentDirections
                    .actionLoginFragmentToHomeFragment()
                // Navigate from the login screen to the home screen
                view?.findNavController()?.navigate(action)
            } else {
                Toast.makeText(
                    // Get the host activity - MainActivity
                    activity,
                    // The text you want to display
                    "Incorrect email address and/or password. Please try again",
                    // The time you want the toast to appear for
                    Toast.LENGTH_LONG // Toast.LENGTH_SHORT
                    // Remember to call the show() function
                ).show()
            }
        }

        return view
    }
}