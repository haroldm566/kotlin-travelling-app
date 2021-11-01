package op.mobile.app.dev.mandha1.travelling.ui.attractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentAttractionsBinding
import op.mobile.app.dev.mandha1.travelling.helpers.Attraction
import op.mobile.app.dev.mandha1.travelling.helpers.AttractionJSONReader
import op.mobile.app.dev.mandha1.travelling.helpers.AttractionMarkerCluster

class AttractionsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var viewModel: AttractionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAttractionsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_attractions,
            container,
            false
        )

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        val viewModelFactory =
            AttractionsViewModelFactory(
                AttractionsFragmentArgs.fromBundle(requireArguments()).country
            )

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(AttractionsViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            attractionsViewModel = viewModel

            return root
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val attractions = AttractionJSONReader(requireContext()).read()

        /**
         * Only get JSON data with specified country name
         * so the attractions shown are only the ones from said specified country
         *
         * Scalable by adding more JSON entries
         */
        val specificAttractions = mutableListOf<Attraction>()
        for (i in attractions) {
            if (i.country == viewModel.country.name) {
                specificAttractions.add(i)
            }
        }

        val clusterManager: ClusterManager<Attraction> = ClusterManager(requireContext(), googleMap)
        val markerCluster = AttractionMarkerCluster(requireContext(), googleMap, clusterManager)
        clusterManager.renderer = markerCluster
        clusterManager.addItems(specificAttractions)
        clusterManager.cluster()

        /**
         * Snap the camera to the first attraction
         */
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificAttractions[0].position, 10f))
    }
}
