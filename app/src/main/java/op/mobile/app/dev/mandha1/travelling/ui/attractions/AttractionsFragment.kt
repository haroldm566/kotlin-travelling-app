package op.mobile.app.dev.mandha1.travelling.ui.attractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.helpers.Company
import op.mobile.app.dev.mandha1.travelling.helpers.CompanyJSONReader
import op.mobile.app.dev.mandha1.travelling.helpers.CompanyMarkerCluster

class AttractionsFragment : Fragment(), OnMapReadyCallback {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_attractions, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val companies = CompanyJSONReader(requireContext()).read()
        val clusterManager: ClusterManager<Company> = ClusterManager(requireContext(), googleMap)
        val markerCluster = CompanyMarkerCluster(requireContext(), googleMap, clusterManager)
        clusterManager.renderer = markerCluster
        clusterManager.addItems(companies)
        clusterManager.cluster()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(companies[0].position, 10f))
    }
}