package op.mobile.app.dev.mandha1.travelling.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentLoginBinding
import op.mobile.app.dev.mandha1.travelling.login.LoginApplication
import op.mobile.app.dev.mandha1.travelling.model.Login

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        val viewModelFactory =
            LoginViewModelFactory((activity?.applicationContext as LoginApplication).repository)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.loginViewModel = viewModel

        /**
         * Instantiate Firebase auth
         */
        auth = FirebaseAuth.getInstance()

        /**
         * Prompt user that they need to fill in the fields if they're empty,
         * else allow input for login
         */
        binding.btnLogin.setOnClickListener {
            val username = binding.etEmailaddr.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            when {
                username.isEmpty() -> binding.etEmailaddr.error = "Please enter a username"
                password.isEmpty() -> binding.etPassword.error = "Please enter a password"
                else -> viewModel.insertLoginDetail(Login(username, password))
            }

            /**
             * Call function from Firebase if login credentials are valid
             */
            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "Successfully Logged In", Toast.LENGTH_LONG).show()

                    val action = LoginFragmentDirections
                        .actionLoginFragmentToHomeFragment()

                    view?.findNavController()?.navigate(action)
                } else {
                    Toast.makeText(activity, "Login Failed", Toast.LENGTH_LONG).show()
                }
            }
        }

        /**
         * Send user to account registration page if they want to make an account
         */
        binding.btnRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            it.findNavController().navigate(action)
        }

        return binding.root
    }
}