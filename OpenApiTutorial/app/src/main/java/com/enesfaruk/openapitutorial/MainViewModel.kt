package com.enesfaruk.openapitutorial

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesfaruk.openapitutorial.apis.DefaultApi
import com.enesfaruk.openapitutorial.infrastructure.ApiClient
import com.enesfaruk.openapitutorial.models.GetAll200ResponseInner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel: ViewModel() {
    var dataListResponse:List<GetAll200ResponseInner> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getDataList() {
        viewModelScope.launch {
            try {
                val apiClient = ApiClient()
                val defaultApi = apiClient.createService(DefaultApi::class.java)

                val response: Response<List<GetAll200ResponseInner>> = withContext(Dispatchers.IO) {
                    defaultApi.getAll().execute()
                }

                if (response.isSuccessful) {
                    val posts = response.body()
                    dataListResponse = posts!!.toList()
                } else {
                    val errorBody = response.errorBody()
                    errorMessage = errorBody.toString()
                }
            } catch (e: Exception) {
                errorMessage = e.toString()
            }
        }
    }
}


