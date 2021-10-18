//package op.mobile.app.dev.mandha1.travelling.ui.registration
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.MenuItem
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.constraintlayout.helper.widget.MotionEffect.TAG
//import androidx.databinding.DataBindingUtil
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.findNavController
//import com.google.android.gms.common.api.ApiException
//import com.google.firebase.auth.FirebaseAuth
//import op.mobile.app.dev.mandha1.travelling.R
//
//class RegistrationFragment : Fragment() {
//
//    private lateinit var auth: FirebaseAuth
//
//    override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if(currentUser != null){
//            //reload();
//        }
//    }
//
//    override fun onCreateView(
//            inflater: LayoutInflater,
//            container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View {
//
//        //  FIREBASE AUTH
//        auth = FirebaseAuth.getInstance()
//
//        btnReg.setOnClickListener {
//
//            auth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this) { task ->
//                        if (task.isSuccessful) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success")
//                            val user = auth.currentUser
//                            updateUI(user)
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.exception)
//                            Toast.makeText(baseContext, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show()
//                            updateUI(null)
//                        }
//                    }
//        }
//
//        return binding.root
//    }
//}