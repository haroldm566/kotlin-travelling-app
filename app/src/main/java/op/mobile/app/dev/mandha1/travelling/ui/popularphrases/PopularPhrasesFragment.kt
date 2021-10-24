package op.mobile.app.dev.mandha1.travelling.ui.popularphrases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentPopularphrasesBinding

class PopularPhrasesFragment : Fragment() {
    private lateinit var viewModel: PopularPhrasesViewModel
    private lateinit var binding: FragmentPopularphrasesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_popularphrases,
            container,
            false
        )

        val viewModelFactory =
            PopularPhrasesViewModelFactory(
                PopularPhrasesFragmentArgs.fromBundle(requireArguments()).country
            )

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(PopularPhrasesViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            popularPhrasesViewModel = viewModel

            return root
        }
    }
}