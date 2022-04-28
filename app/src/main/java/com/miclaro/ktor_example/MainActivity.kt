package com.miclaro.ktor_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.miclaro.ktor_example.Services.DataModel.Response
import com.miclaro.ktor_example.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.btnLiveData.setOnClickListener {
            getDataLiveData()
        }
        binding.btnStateFlow.setOnClickListener {
            getDataStateFlow()
        }
    }

    private fun getDataLiveData(){
        viewModel.getListDataFromServicesLiveData().observe(this) {
            binding.txtResponse.setTextColor(getColor(R.color.purple_500))
            binding.txtResponse.text = it.toString()
        }
    }

    private fun getDataStateFlow(){
        lifecycleScope.launchWhenStarted {
            viewModel.getListDataFromServicesStateFlow().collect {
                binding.txtResponse.setTextColor(getColor(R.color.teal_200))
                binding.txtResponse.text = it.toString()
            }
        }
    }

}