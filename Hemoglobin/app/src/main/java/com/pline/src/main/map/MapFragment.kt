package com.pline.src.main.map

import android.os.Bundle
import android.view.View
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val seoul = LatLng(37.556, 126.97)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 10.5f))
    }

    fun addCenterMarker(co : LatLng) {
        // 마커 추가
        /*val markerOptions = MarkerOptions()
        markerOptions.position(seoul)
            .title("서울")
            .snippet("한국 수도")
        mMap.addMarker(markerOptions)*/
    }
}