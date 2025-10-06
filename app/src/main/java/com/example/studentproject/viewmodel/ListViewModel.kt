package com.example.studentproject.viewmodel

import android.R
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.studentproject.model.Student
import com.example.studentproject.util.FileHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.math.log

class ListViewModel(app: Application): AndroidViewModel(app) {
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val errorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var queue: RequestQueue? = null
    val TAG = "volleytag"

    fun refresh(){
        loadingLD.value = true // progress bar start muncul
        errorLD.value = false // tidak ada error

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMW"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                //sukses
                val sType = object: TypeToken<List<Student>>() {}.type
                val result = Gson().fromJson<List<Student>>(it, sType)
                studentsLD.value = result as ArrayList<Student>
                loadingLD.value = false

                //simpan juga ke file
                val filehelper = FileHelper(getApplication())
                val jsonstring = Gson().toJson(result)
                filehelper.writeToFile(jsonstring)
                Log.d("print_file", jsonstring)

                //baca json string dari file
                val hasil = filehelper.readFromFile()
                val lisStudent = Gson().fromJson<List<Student>>(hasil, sType)
                Log.d("print_file", lisStudent.toString())

            },{
                errorLD.value = true
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)

//        loadingLD.value = false
//        errorLD.value = false
    }

    fun testSaveFile(){
        val fileHelper = FileHelper(getApplication())
        fileHelper.writeToFile("Hello World")
        val content = fileHelper.readFromFile()
        Log.d("print_file", content)
        Log.d("print_file", fileHelper.getFilePath())
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)

    }
}
