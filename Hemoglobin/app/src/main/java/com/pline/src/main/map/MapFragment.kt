package com.pline.src.main.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentMapBinding

class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::bind, R.layout.fragment_map), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    data class Place(
        val longitude: Double,
        val latitude : Double,
        val name : String
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.fragmentMapBtnMyLocation.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                moveCameraToMyLocation()
            } else {
                // Request location permission
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            }
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val seoul = LatLng(37.556, 126.97)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 10.3f))
        setCentersInMap()
        /*val seoulBloodDonationCenter = LatLng(37.565306, 126.984377)
        mMap.addMarker(MarkerOptions().position(seoulBloodDonationCenter).title("서울 헌혈의 집"))*/

    }
    private fun setCentersInMap() {
        val places = arrayListOf<Place>(
            Place(126.870715, 37.5480421, "중앙센터(원내)"),
            Place(126.969422, 37.5562730, "서울역센터"),
            Place(126.937461, 37.5572962, "신촌연대앞센터"),
            Place(126.937813, 37.5556205, "신촌센터"),
            Place(126.920225, 37.6190483, "연신내센터"),
            Place(126.922818, 37.5558368, "홍대센터"),
            Place(127.043562, 37.5555670, "한양대역센터"),
            Place(127.029483, 37.5856733, "고려대앞센터"),
            Place(127.000160, 37.583352, "대학로 센터"),
            Place(126.981370, 37.5710970, "광화문센터"),
            Place(127.061421, 37.6541450, "노해로센터"),
            Place(127.090342, 37.5980767, "망우역센터"),
            Place(126.890296, 37.5069587, "신도림테크노마트센터"),
            Place(126.835817, 37.5478091, "우장산역센터"),
            Place(126.957125, 37.4533208, "서울대학교센터"),
            Place(126.906174, 37.5167664, "영등포센터"),
            Place(126.875795, 37.5281626, "목동센터"),
            Place(126.838279, 37.5600930, "발산역센터"),
            Place(127.049083, 37.4820165, "매봉센터(원내)"),
            Place(127.127009, 37.5378483, "천호센터"),
            Place(127.061846, 37.6470721, "중계센터(원내)"),
            Place(127.062714, 37.6559824, "노원센터"),
            Place(127.017665, 37.5919554, "돈암센터"),
            Place(127.024439, 37.6372287, "수유센터"),
            Place(127.056795, 37.5897451, "회기센터"),
            Place(127.025437, 37.5013321, "강남센터"),
            Place(127.070450, 37.5407691, "건대역센터"),
            Place(127.028625, 37.4966372, "강남2센터"),
            Place(127.145555, 37.5503329, "강동센터"),
            Place(126.981754, 37.4863493, "이수센터"),
            Place(127.101202, 37.5137262, "잠실역센터"),
            Place(127.058796, 37.5125020, "코엑스센터"),
            Place(126.942962, 37.5133935, "노량진역센터")
        )
        for(a in places) {
            Log.d("places", "$a");
            addCenterMarker(a)
        }
    }
    private fun moveCameraToMyLocation() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        val currentLatLng = LatLng(location.latitude, location.longitude)
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14f))

                        // Add this line to enable showing current location
                        mMap.isMyLocationEnabled = true
                    }
                }
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }
    private fun addCenterMarker(co : Place) {
        // 마커 추가
        val markerOptions = MarkerOptions()
        markerOptions.position(LatLng(co.latitude, co.longitude))
            .title(co.name)
        mMap.addMarker(markerOptions)
    }
}