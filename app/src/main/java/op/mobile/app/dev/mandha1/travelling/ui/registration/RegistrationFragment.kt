package op.mobile.app.dev.mandha1.travelling.ui.registration

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import op.mobile.app.dev.mandha1.travelling.R

class RegistrationFragment: Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        auth = FirebaseAuth.getInstance()

        val btnRegister: Button = view.findViewById(R.id.btnReg)
        val etEmailAddress: EditText = view.findViewById(R.id.reg_email)
        val etPassword: EditText = view.findViewById(R.id.reg_password)
        val etConfirmPassword: EditText = view.findViewById(R.id.reg_confirm_password)

        btnRegister.setOnClickListener {
            /**
             * Get fields in registration fragment
             */
            val email = etEmailAddress.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            /**
             * Error checking for fields
             * Allow input when requirements met
             */
            when {
                email.isEmpty() ->
                    etEmailAddress.error = "Email is required."
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                    etEmailAddress.error = "Invalid email address format."
                password.isEmpty() ->
                    etPassword.error = "Password is required."
                password.length < 8 ->
                    etPassword.error = "Password needs to be at least eight characters."
                password != confirmPassword ->
                    etConfirmPassword.error = "Passwords do not match."
                else -> {
                    register(email, password)
                }
            }
        }

        return view
    }

    /**
     * Use Firebase function for registration
     * Data stored in Firebase database
     */
    private fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        activity,
                        "User created.",
                        Toast.LENGTH_LONG
                    ).show()
                    /**
                     * Redirect user to login screen when done
                     */
                    view?.findNavController()?.navigate(
                        RegistrationFragmentDirections.actionRegisterFragmentToLoginFragment()
                    )
                } else {
                    Toast.makeText(
                        activity,
                        "Email address already exists.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}