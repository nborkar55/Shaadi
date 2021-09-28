package com.nikhil.shaadi_demo_app

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.nikhil.shaadi_demo_app.adapters.CarouselAdapter
import com.nikhil.shaadi_demo_app.model.Profiles
import com.nikhil.shaadi_demo_app.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    lateinit var carousalAdapter: CarouselAdapter
    val resultList = ArrayList<Profiles.Result>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        close.setOnClickListener {
            finish()
        }

        refresh.setOnClickListener {
            if(isNetworkAvailable()) {
                CoroutineScope(Dispatchers.IO).launch {
                    mainViewModel.getProfile()
                }
            }
        }
        LinearSnapHelper().attachToRecyclerView(recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        carousalAdapter = CarouselAdapter(this@MainActivity, resultList)
        recyclerview.adapter = carousalAdapter
        CoroutineScope(Dispatchers.IO).launch {
            if (isNetworkAvailable()) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Making Network call", Toast.LENGTH_LONG)
                        .show()
                }
                val response = mainViewModel.getProfile()

                if (response != null) {
                    withContext(Dispatchers.Main) {
                        resultList.addAll(response.results)
                        carousalAdapter = CarouselAdapter(this@MainActivity, resultList)
                        recyclerview.adapter = carousalAdapter
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "No Network", Toast.LENGTH_LONG)
                }
            }
            withContext(Dispatchers.Main) {

                mainViewModel.getProfilesFromDB().observe(this@MainActivity, { results ->
                    resultList.clear()
                    resultList.addAll(results)
                    carousalAdapter.notifyDataSetChanged()

                })
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val cm =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nInfo = cm.activeNetworkInfo
        return nInfo != null && nInfo.isAvailable && nInfo.isConnected
    }

    fun updateStatus(status: Int,phone:String) {
        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.updateStatus(status,phone)
        }
    }
}