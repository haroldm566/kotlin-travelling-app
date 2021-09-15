package op.mobile.app.dev.mandha1.travelling.ui.login

import android.content.Intent
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

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentLoginBinding
import op.mobile.app.dev.mandha1.travelling.login.LoginAdapter
import op.mobile.app.dev.mandha1.travelling.login.LoginApplication
import op.mobile.app.dev.mandha1.travelling.model.Login

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

//        //return inflater.inflate(R.layout.fragment_login, container, false)

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        val viewModelFactory =
            LoginViewModelFactory((activity?.applicationContext as LoginApplication).repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.loginViewModel = viewModel

        binding.rvLoginDetails.adapter = LoginAdapter()

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        val emailAddressEdtTxt: EditText = view.findViewById(R.id.et_email_address)
        val passwordEdtTxt: EditText = view.findViewById(R.id.et_password)
        val btnLogin: Button = view.findViewById(R.id.btn_login)
        val btnGoogleLogin: SignInButton = view.findViewById(R.id.btn_google_login)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername
            val password = binding.etPassword
            when {
                username.text.toString().trim().isEmpty() -> binding.etUsername.error = "Please enter a username"
                password.text.toString().trim().isEmpty() -> binding.etPassword.error = "Please enter a password"
                else -> {
                    viewModel.insertLoginDetail(Login(username.text.toString().trim(),  password.text.toString().trim()))
                    username.text.clear()
                    password.text.clear()
                }
            }
        }

        // This is an example of an on click listener bound to a Button
//        btnLogin.setOnClickListener {
//            if (emailAddressEdtTxt.text.toString() == ""
//                && passwordEdtTxt.text.toString() == ""
//            ) {
//                // Get the action specified in mobile_navigation.xml
//                val action = LoginFragmentDirections
//                    .actionLoginFragmentToHomeFragment()
//                // Navigate from the login screen to the home screen
//                view?.findNavController()?.navigate(action)
//            } else {
//                Toast.makeText(
//                    // Get the host activity - MainActivity
//                    activity,
//                    // The text you want to display
//                    "Incorrect email address and/or password. Please try again",
//                    // The time you want the toast to appear for
//                    Toast.LENGTH_LONG // Toast.LENGTH_SHORT
//                    // Remember to call the show() function
//                ).show()
//            }
//        }

        btnGoogleLogin.setOnClickListener {
            signIn()
        }

        return binding.root
    }

    // Prompt the user to sign in using their Google account's email
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    // Once the user has chosen their Google account's email
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception

            // Authenticate with Firebase if Google sign in is successful
            if (task.isSuccessful) {
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                    // Catch Google sign in issues
                } catch (e: ApiException) {
                    Toast.makeText(
                        activity,
                        "Failed to sign in with Google",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // Display a message if there is Firebase Authentication issues
            } else {
                Toast.makeText(activity, exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * After a user successfully signs in, get an idToken from the GoogleSignInAccount
     * object, exchange it for a Firebase credential, and authenticate with Firebase
     * using the Firebase credential
     */
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) {
                // Navigate to HomeFragment
                if (it.isSuccessful) {
                    val action =
                        LoginFragmentDirections
                            .actionLoginFragmentToHomeFragment()
                    view?.findNavController()?.navigate(action)
                } else {
                    Toast.makeText(
                        activity,
                        "Failed to authenticate with Google",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    companion object {
        const val RC_SIGN_IN = 9001
    }
}