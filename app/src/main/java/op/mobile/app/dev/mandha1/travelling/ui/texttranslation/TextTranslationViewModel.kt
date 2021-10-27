package op.mobile.app.dev.mandha1.travelling.ui.texttranslation

import android.speech.tts.TextToSpeech
import android.text.Editable
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

class TextTranslationViewModel(_country: Country, _tts: TextToSpeech) : ViewModel() {

    private val baseUrl = "https://translate.yandex.net/api/v1.5/tr.json/"

    private var country: Country = _country

    private val tts: TextToSpeech = _tts
    private val _translateText = MutableLiveData<String>()
    val translateText: LiveData<String> get() = _translateText

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
    fun getTranslatedText(text: String?) {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = RetrofitInstance(baseUrl).retrofitTranslateService.getResponse(
                    "trnsl.1.1.20200329T025311Z.37f6897b8a99dbd9.bb42d876c007fde0812c365015625fde8c0f0163",
                    text!!,
                    "en-${country.langCode}"
                )
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _status.value = ServiceStatus.ERROR
            }
        }
    }

    //  Get the inputted text from the TextInputEditText component from the layout fragment
    //      and set that to be the text to be translated
    fun setToBeTranslatedText(editable: Editable) {
        _translateText.value = editable.toString()
    }

    //  Have the TTS speak the text out loud
    fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}