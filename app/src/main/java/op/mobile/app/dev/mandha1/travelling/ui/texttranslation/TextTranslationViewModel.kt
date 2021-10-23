package op.mobile.app.dev.mandha1.travelling.ui.texttranslation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.mandha1.travelling.api.RetrofitInstance
import op.mobile.app.dev.mandha1.travelling.api.ServiceStatus
import op.mobile.app.dev.mandha1.travelling.model.Country
import op.mobile.app.dev.mandha1.travelling.model.Translate
import retrofit2.Call
import java.util.*

class TextTranslationViewModel(_country: Country) : ViewModel() {

    private val localProperties = Properties()
    //"https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + localProperties.getProperty("YANDEX_API_KEY")
    //"https://translate.yandex.net/api/v1.5/tr.json/getLangs?key=" + localProperties.getProperty("YANDEX_API_KEY")
    private val baseUrl = "https://translate.yandex.net/api/v1.5/tr.json/"

    private val country: Country = _country

    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<Translate>()
    val response: LiveData<Translate> get() = _response

    init {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                //_response.value = RetrofitInstance(baseUrl).retrofitTranslateService.getLangs()
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _status.value = ServiceStatus.ERROR
            }
        }
    }

    //  Put together completed request url for translated text
    //  and send a get request
    fun getTranslatedText(text: String) {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = RetrofitInstance(baseUrl).retrofitTranslateService.getResponse(
                    localProperties.getProperty("YANDEX_API_KEY"),
                    text,
                    "en-${country.langCode}"
                )
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _status.value = ServiceStatus.ERROR
            }
        }
    }
}