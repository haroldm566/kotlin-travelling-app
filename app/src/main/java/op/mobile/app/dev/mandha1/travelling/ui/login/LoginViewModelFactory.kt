package op.mobile.app.dev.mandha1.travelling.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.mandha1.travelling.repository.LoginRepository

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val repository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}