package op.mobile.app.dev.mandha1.travelling.ui.home

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentHomeBinding
import op.mobile.app.dev.mandha1.travelling.helpers.IOnClickListener
import op.mobile.app.dev.mandha1.travelling.helpers.recyclerview.CountryRVAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import op.mobile.app.dev.mandha1.travelling.ui.countrypage.CountryPageFragmentDirections


class HomeFragment : Fragment(), IOnClickListener {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        val viewModel: HomeViewModel by viewModels()

        /**
         * This disables the back button. You can not go to the
         * previous Fragment
         */
        activity?.onBackPressedDispatcher?.addCallback(this) {}

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeViewModel = viewModel

            rvCountries.adapter = CountryRVAdapter(this@HomeFragment)

            return root
        }
    }

    /**
     * Implementing the interface that allows you to click on a
     * RecyclerView item. It gets the Country object bound to that item
     * and is passed to the QuizFragment. This is declared in mobile_navigation.xml
     * using the argument tag
     */
    override fun onItemClick(position: Int) {
        val item = binding.homeViewModel!!.response.value!![position]
        val action =
            HomeFragmentDirections.actionHomeFragmentToCountrypageFragment(item)
        findNavController().navigate(action)
    }
}