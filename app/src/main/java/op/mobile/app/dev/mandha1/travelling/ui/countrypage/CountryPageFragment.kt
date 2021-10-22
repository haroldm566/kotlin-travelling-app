package op.mobile.app.dev.mandha1.travelling.ui.countrypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentCountrypageBinding
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentHomeBinding
import op.mobile.app.dev.mandha1.travelling.helpers.recyclerview.CountryRVAdapter
import op.mobile.app.dev.mandha1.travelling.ui.home.HomeViewModel
import op.mobile.app.dev.mandha1.travelling.ui.quiz.QuizFragmentDirections

class CountryPageFragment : Fragment() {
    private lateinit var viewModel: CountryPageViewModel
    private lateinit var binding: FragmentCountrypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
//        val binding: FragmentCountrypageBinding = DataBindingUtil.inflate(
//            inflater,
//            R.layout.fragment_countrypage,
//            container,
//            false
//        )
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_countrypage,
            container,
            false
        )

        val viewModel: HomeViewModel by viewModels()
//        val viewModelFactory =
//            CountryPageViewModelFactory(
//                CountrypageFragmentArgs.fromBundle(requireArguments()).country
//            )
//
//        viewModel = ViewModelProvider(
//            this,
//            viewModelFactory
//        ).get(CountryPageViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            countryPageViewModel = viewModel

            homeViewModel = viewModel

            rvCountries.adapter = CountryRVAdapter(this@CountryPageFragment)

            return root
        }
    }

    private fun doQuiz(position: Int) {
        //val item = binding.countryPageViewModel!!.response.value!![position]
        val item = binding.homeViewModel!!.response.value!![position]
        val action = CountryPageFragmentDirections.actionCountrypageFragmentToQuizFragment(item)
        findNavController().navigate(action)
    }
}