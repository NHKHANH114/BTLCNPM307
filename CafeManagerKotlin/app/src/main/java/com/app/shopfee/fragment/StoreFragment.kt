package com.app.shopfee.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.shopfee.R
import com.app.shopfee.activity.MainActivity
import com.app.shopfee.databinding.FragmentStroreBinding


class StoreFragment : BaseFragment() {


    private var mFragmentStoreFragment : FragmentStroreBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mFragmentStoreFragment = FragmentStroreBinding.inflate(inflater, container, false)
        mFragmentStoreFragment!!.btnOderNow.setOnClickListener {
            backToHomeScreen()
        }

        openGGmap()
        return mFragmentStoreFragment!!.root
    }

    override fun initToolbar() {
        if (activity != null) {
            (activity as MainActivity?)!!.setToolBar(false, getString(R.string.nav_store))
        }
    }

    private fun backToHomeScreen() {
        val mainActivity = activity as MainActivity? ?: return
        mainActivity.viewPager2?.currentItem = 0
    }

    private fun openGGmap(){
        mFragmentStoreFragment!!.map.setOnClickListener {
            openGoogleMaps()
        }
    }

    private fun openGoogleMaps() {
        val gmmIntentUri = Uri.parse("geo:21.0538900184449, 105.73357126658817")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        val packageManager = activity?.packageManager
        if (mapIntent.resolveActivity(packageManager!!) != null) {
            startActivity(mapIntent)
        } else {
            Log.d("AAAAAAAA", "openGoogleMaps: is fall")
        }
    }
}