package op.mobile.app.dev.mandha1.travelling.ui.texttranslation

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentTextTranslationBinding
import java.util.*

class TextTranslationFragment : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var viewModel: TextTranslationViewModel
    private lateinit var binding: FragmentTextTranslationBinding

    private lateinit var tts: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_text_translation, container, false
        )

        tts = TextToSpeech(activity, this)

        val viewModelFactory =
            TextTranslationViewModelFactory(
                TextTranslationFragmentArgs.fromBundle(requireArguments()).country,
                tts as TextToSpeech
            )

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(TextTranslationViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            textTranslationViewModel = viewModel

            return root
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage((Locale.US))

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                Toast.makeText(activity, "Sorry, this language isn't supported.", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            Toast.makeText(activity, "Something broke.", Toast.LENGTH_SHORT).show()
        }
    }
}