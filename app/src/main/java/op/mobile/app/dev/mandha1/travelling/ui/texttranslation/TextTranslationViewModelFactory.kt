package op.mobile.app.dev.mandha1.travelling.ui.texttranslation

import android.speech.tts.TextToSpeech
import op.mobile.app.dev.mandha1.travelling.model.Country
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class TextTranslationViewModelFactory(
    private val country: Country,
    private val tts: TextToSpeech
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TextTranslationViewModel::class.java))
            return TextTranslationViewModel(country, tts) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
