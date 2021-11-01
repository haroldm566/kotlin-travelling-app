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

        /**
         * Directions to go to multiple fragments
         * In order:
         * - To quiz
         * - To text translation
         * - To popular phrases
         * - To attractions
         */
        val toQuiz = CountryPageFragmentDirections
            .actionCountrypageFragmentToQuizFragment(
                viewModel.country
            )

        val toTxtTr = CountryPageFragmentDirections
            .actionCountrypageFragmentToTexttranslationFragment(
                viewModel.country
            )

        val toPplrPhr = CountryPageFragmentDirections
            .actionCountrypageFragmentToPopularphrasesFragment(
                viewModel.country
            )

        val toAttr = CountryPageFragmentDirections
            .actionCountrypageFragmentToAttractionsFragment(
                viewModel.country
            )

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            countryPageViewModel = viewModel

            /**
             *  Set which fragments the buttons should lead to
             *  In same order:
             * - To quiz
             * - To text translation
             * - To popular phrases
             * - To attractions
             */
            btnPlay.setOnClickListener {
                it.findNavController().navigate(toQuiz)
            }

            btnTt.setOnClickListener {
                it.findNavController().navigate(toTxtTr)
            }

            btnPp.setOnClickListener {
                it.findNavController().navigate(toPplrPhr)
            }

            btnAttractions.setOnClickListener {
                it.findNavController().navigate(toAttr)
            }

            return root
        }
    }
}