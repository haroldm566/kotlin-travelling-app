package op.mobile.app.dev.mandha1.travelling.ui.login

import android.os.*
import android.view.*
import androidx.fragment.app.Fragment
import op.mobile.app.dev.mandha1.travelling.R

class LoginFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
}