package op.mobile.app.dev.mandha1.travelling.ui.texttranslation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentTextTranslationBinding

class TextTranslationFragment : Fragment() {

    private lateinit var viewModel: TextTranslationViewModel
    private lateinit var binding: FragmentTextTranslationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_text_translation, container, false
        )

        val viewModelFactory =
            TextTranslationViewModelFactory(
                TextTranslationFragmentArgs.fromBundle(requireArguments()).country
            )

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(TextTranslationViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            textTranslationViewModel = viewModel
            // translate things
            // non functional need something here
            return root
        }
    }
}