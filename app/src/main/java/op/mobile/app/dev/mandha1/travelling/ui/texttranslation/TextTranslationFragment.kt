package op.mobile.app.dev.mandha1.travelling.ui.texttranslation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.ui.home.HomeFragmentDirections
import op.mobile.app.dev.mandha1.travelling.ui.texttranslation.TextTranslationViewModel
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentTextTranslationBinding
import op.mobile.app.dev.mandha1.travelling.helpers.recyclerview.CountryRVAdapter
import op.mobile.app.dev.mandha1.travelling.ui.home.HomeViewModel

class TextTranslationFragment : Fragment(){

    //private val TAG = "MainActivity"
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

        val viewModel: TextTranslationViewModel by viewModels()

        Log.d("TESTRESPONSE", viewModel.response.value.toString())

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            textTranslationViewModel = viewModel
            return root
        }

//        val viewModel = ViewModelProvider(this).get(TextTranslationViewModel::class.java)
//        binding.lifecycleOwner = viewLifecycleOwner
//        binding.textTranslationViewModel = viewModel


//        return binding.root
    }
}