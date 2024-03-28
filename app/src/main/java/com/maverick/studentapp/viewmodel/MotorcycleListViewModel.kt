package com.maverick.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maverick.studentapp.model.Motorcycle
import com.maverick.studentapp.model.Student
import org.json.JSONArray
import org.json.JSONObject

class MotorcycleListViewModel(application: Application):AndroidViewModel(application) {
    val motorcyclesLD = MutableLiveData<ArrayList<Motorcycle>>()
    val motorcycleLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh(){
        motorcycleLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue((getApplication()))
        val url = "http://10.0.2.2/WebProjects/motorcycle/motorcycle.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Motorcycle>>(){ }.type
                val result = Gson().fromJson<List<Motorcycle>>(it, sType)
//                val count = result.size

                motorcyclesLD.value = result as ArrayList<Motorcycle>?
                loadingLD.value = false

                Log.d("showMotorcycle", result.toString())
            },
            {
                Log.d("showMotorcycle", it.toString())
                motorcycleLoadErrorLD.value = true
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}