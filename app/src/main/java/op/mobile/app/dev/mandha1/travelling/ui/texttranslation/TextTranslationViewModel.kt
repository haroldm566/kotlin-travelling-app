package op.mobile.app.dev.mandha1.travelling.ui.texttranslation

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.mandha1.travelling.api.ITranslate
import op.mobile.app.dev.mandha1.travelling.api.RetrofitInstance
import op.mobile.app.dev.mandha1.travelling.api.ServiceStatus
import op.mobile.app.dev.mandha1.travelling.model.Translate
import java.util.*

class TextTranslationViewModel : ViewModel() {
    //test
    private val localProperties = Properties()

    private val baseUrl =
        "https://translate.yandex.net/api/v1.5/tr.json/translate/" + localProperties.getProperty("YANDEX_API_KEY")

    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<List<Translate>>()
    val response: LiveData<List<Translate>> get() = _response

    init {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = RetrofitInstance(baseUrl).retrofitTranslateService.getResponse()
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _status.value = ServiceStatus.ERROR
            }
        }
    }
}