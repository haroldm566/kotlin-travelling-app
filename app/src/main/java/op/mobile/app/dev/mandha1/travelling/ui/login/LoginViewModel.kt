package op.mobile.app.dev.mandha1.travelling.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.mandha1.travelling.model.Login
import op.mobile.app.dev.mandha1.travelling.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    val allLoginDetails: LiveData<List<Login>> = repository.allLoginDetails.asLiveData()

    fun insertLoginDetail(login: Login) = viewModelScope.launch {
        repository.insertLoginDetail(login)
    }
}
