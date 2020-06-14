package com.example.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request

class Main2Activity : AppCompatActivity() {
    private val okhttp=OkHttpClient()
    private var request:Request=Request.Builder().url("https://www.baidu.com/").get().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun startNet(view: View) {

        lifecycleScope.launch(Dispatchers.Main) {
            val job=async(Dispatchers.Default) {
                okhttp.newCall(request).execute().body()?.string()
            }
            tv_content.text=job.await()
        }
    }




    fun clearContent(view: View) {
            tv_content.text=null
    }

}
