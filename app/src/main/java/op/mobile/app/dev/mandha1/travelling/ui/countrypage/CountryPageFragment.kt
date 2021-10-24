package op.mobile.app.dev.mandha1.travelling.ui.countrypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentCountrypageBinding

class CountryPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentCountrypageBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_countrypage,
            container,
            false
        )

        val viewModelFactory =
            CountryPageViewModelFactory(
                CountryPageFragmentArgs.fromBundle(requireArguments()).country
            )

        val viewModel: CountryPageViewModel by viewModels { viewModelFactory }

        //  Directions to go to the Quiz fragment,
        //      passing country object to said fragment
        val action = CountryPageFragmentDirections
            .actionCountrypageFragmentToQuizFragment(
                viewModel.country
            )

        //  Directions to go to the text translation fragment,
        //        passing country object to said fragment
        val action2 = CountryPageFragmentDirections
            .actionCountrypageFragmentToTexttranslationFragment(
                viewModel.country
            )

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            countryPageViewModel = viewModel

            //  Go to the quiz fragment, passing country object to it
            //      when button is pressed
            btnPlay.setOnClickListener {
                it.findNavController().navigate(action)
            }

            //  Go to the text translation fragment, passing country object to it
            //      when button is pressed
            btnTt.setOnClickListener {
                it.findNavController().navigate(action2)
            }

            return root
        }
    }
}