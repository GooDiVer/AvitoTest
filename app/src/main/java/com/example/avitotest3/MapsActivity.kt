package com.example.avitotest3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private lateinit var viewModelMarkers: ViewModelMarkers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModelMarkers = ViewModelProviders.of(this).get(ViewModelMarkers::class.java)

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

        val moscowLng = LatLng(55.7558, 37.6173)


        viewModelMarkers.markersOnMap.observe(this, Observer {
                markers ->
            for(x in markers.pins) {
                val place = LatLng(x.coordinates.lat, x.coordinates.lng)
                mMap.addMarker(MarkerOptions().position(place).title("Сервис ${x.service}"))
            }
        })

        viewModelMarkers.loadMarkers()

        val cameraPosition = CameraPosition.Builder().target(moscowLng)
            .zoom(12F)
            .build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        chooseButton.setOnClickListener {
            view ->
            openServices()
        }
    }

    private fun openServices() {
        val intent = Intent(this, FilterActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            mMap.clear()

            val gotServiceNameList: ArrayList<String> = data?.getStringArrayListExtra(FilterActivity.ARG_RESULT)!!

            val actualMarkersPinList: List<Pin> = viewModelMarkers.markersOnMap.value?.pins!!

            for(x in gotServiceNameList) {
                for (count in actualMarkersPinList.indices) {

                    val marker = actualMarkersPinList[count]

                    if (marker.service == x){
                        val place = LatLng(marker.coordinates.lat, marker.coordinates.lng)
                        mMap.addMarker(MarkerOptions().position(place).title("Сервис ${marker.service}"))
                    }
                }
            }
        }
    }

    companion object {
         const val REQUEST_CODE = 1
    }
}
