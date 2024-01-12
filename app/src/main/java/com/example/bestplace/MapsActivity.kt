package com.example.bestplace

import android.icu.text.Transliterator.Position
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.bestplace.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in stockholm and move the camera
        val stockholm = LatLng(59.33258, 18.0649)
        mMap.addMarker(MarkerOptions().position(stockholm).title("Sveriges Huvudstad"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(stockholm))

        createMarkers()

    }

    fun createMarkers() {
        var gbg =LatLng(57.7,11.9)

        var marker1 = mMap.addMarker(
            MarkerOptions()
                .position(gbg)
                .title("Göteborg")
                .snippet("Sveriges 2störta stad")
        )


        var marker3 = mMap.addMarker(
            MarkerOptions()
                .position(LatLng(58.5, 16.1))
                .title("Norrköping")
                .snippet("Godast Glass")
        )
    }

    fun createPlaces() {

        val p1 = placeInfo("hemma", "bäst", LatLng(59.3, 17.8), R.drawable.baseline_house_24)

        val p2 = placeInfo("The yeti", "Italiensk glass", LatLng(58.5, 16.1), R.drawable.baseline_icecream_24)

        val placeList = listOf<placeInfo>(p1,p2)
    }


   data class placeInfo(val name: String, val info: String, val position: LatLng, val image: Int)

}