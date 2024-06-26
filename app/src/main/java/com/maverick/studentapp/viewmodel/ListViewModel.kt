package com.maverick.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maverick.studentapp.model.Student

class ListViewModel(application: Application):AndroidViewModel(application) {
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>() //bertugas untuk menampung apakah error atau tidak
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh(){
        studentLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
//                loadingLD.value = false
//                Log.d("showvolley", it)

                val sType = object : TypeToken<List<Student>>(){ }.type
                val result = Gson().fromJson<List<Student>>(it, sType)
                studentsLD.value = result as ArrayList<Student>?
                loadingLD.value = false

                Log.d("showvolley", result.toString())
            },
            {
                Log.d("showvolley", it.toString())
                studentLoadErrorLD.value = true
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