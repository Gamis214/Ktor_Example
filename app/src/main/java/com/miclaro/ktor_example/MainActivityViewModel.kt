package com.miclaro.ktor_example

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miclaro.ktor_example.Services.DataModel.Response
import com.miclaro.ktor_example.Services.Repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val responseFlow = MutableStateFlow<Response?>(null)
    private val responseLiveData : MutableLiveData<Response?> = MutableLiveData()
    private val context by lazy { getApplication<Application>().applicationContext}

    /**
     * Peticion con StateFlows
     */
    fun getListDataFromServicesStateFlow() : MutableStateFlow<Response?>{
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                Repository.getUserDataFromServices()
            }.onSuccess {
                responseFlow.value = it
            }.onFailure {
                responseFlow.value = null
            }
        }
        return responseFlow
    }

    /**
     * Peticion con liveData
     */
    fun getListDataFromServicesLiveData() : MutableLiveData<Response?>{
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                Repository.getUserDataFromServices()
            }.onSuccess {
                responseLiveData.postValue(it)
            }.onFailure {
                responseLiveData.postValue(null)
            }
            /*val response = withContext(Dispatchers.IO){
                Repository.getUserDataFromServices()
            }
            if(response != null)
                responseLiveData.value = response*/
        }
        return responseLiveData
    }
}