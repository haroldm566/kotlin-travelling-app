package op.mobile.app.dev.mandha1.travelling.ui.quiz.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentQuizResultsBinding
import op.mobile.app.dev.mandha1.travelling.ui.countrypage.CountryPageFragmentDirections

class QuizResultsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentQuizResultsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_quiz_results,
            container,
            false
        )

        val bundle = QuizResultsFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory =
            QuizResultsViewModelFactory(bundle.score)

        val viewModel: QuizResultsViewModel by viewModels { viewModelFactory }

        val action = QuizResultsFragmentDirections
            .actionQuizFragmentToHomeFragment()

        /**
         * This disables the back button. You can not go to the
         * previous Fragment (not working)
         */
        activity?.onBackPressedDispatcher?.addCallback(this) {}

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            quizResultsViewModel = viewModel

            //  Crashes
            btnBackToHome.setOnClickListener {
                it.findNavController().navigate(action)
            }

            return root
        }
    }
}
