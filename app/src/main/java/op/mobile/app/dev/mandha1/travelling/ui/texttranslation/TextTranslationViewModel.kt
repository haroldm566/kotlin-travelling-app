package op.mobile.app.dev.mandha1.travelling.ui.texttranslation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.mandha1.travelling.api.RetrofitInstance
import op.mobile.app.dev.mandha1.travelling.api.ServiceStatus
import op.mobile.app.dev.mandha1.travelling.model.Translate
import retrofit2.Call
import java.util.*

class TextTranslationViewModel : ViewModel() {
    //test
    private val localProperties = Properties()

    //private val baseUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + localProperties.getProperty("YANDEX_API_KEY")
    private val baseUrl = "https://translate.yandex.net/api/v1.5/tr.json/getLangs?key=trnsl.1.1.20200329T025311Z.37f6897b8a99dbd9.bb42d876c007fde0812c365015625fde8c0f0163"

    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<Call<List<Translate>>>()
    val response: LiveData<Call<List<Translate>>> get() = _response

    init {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = RetrofitInstance(baseUrl).retrofitTranslateService.getLangs()
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _status.value = ServiceStatus.ERROR
            }
        }
    }
}