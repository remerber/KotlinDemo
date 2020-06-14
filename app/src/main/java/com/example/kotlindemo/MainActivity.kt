package com.example.kotlindemo

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

//1111
class MainActivity : AppCompatActivity() {



    override fun onDestroy() {

        super.onDestroy()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())//通过Gson转换请求结果
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
            .create(GitHubService::class.java)
            btn_search.setOnClickListener {
            GlobalScope.launch (Dispatchers.IO){

                var result=retrofit.getListRepos("google").await()
                launch (Dispatchers.Main){
                    tv_content.append(result?.get(0)?.fullName)
                }

              }
            }
//        GlobalScope.launch {
//            val result1=async {
//                delay(1000)
//                1
//            }
//            val result2=async {
//                delay(2000)
//                2
//            }
//            val result=result1.await()+result2.await();
//            Log.e("tag","结果"+result)
//        }
//        Thread.sleep(5000)
//        //////////delay  和sleep类似，但不会阻塞线程
//        Log.e("tag","1  current thread is ${Thread.currentThread().name}")
//        GlobalScope.launch {
//            Log.e("tag","3  current thread is ${Thread.currentThread().name}")
//            delay(1000)
//            Log.e("tag","4  current thread is ${Thread.currentThread().name}")
//        }
//        Log.e("tag","2  current thread is ${Thread.currentThread().name}")
//        Thread.sleep(2000)
//        Log.e("tag","5  current thread is ${Thread.currentThread().name}")
        ///////////////父子协程
//        val job = GlobalScope.launch {
////
////            // 第一个使用不同的上下文
////            val job1 = GlobalScope.launch {
////
////                println("job1: I have my own context and execute independently!")
////                delay(1000)
////                println("job1: I am not affected by cancellation of the job")
////            }
////
////            // 第二个继承父级上下文
////            val job2 = launch(coroutineContext) {
////
////                println("job2: I am a child of the job coroutine")
////                delay(1000)
////                println("job2: I will not execute this line if my parent job is cancelled")
////            }
////
////            job1.join()
////            job2.join()
////        }
////
////        Thread.sleep(500)
////
////        job.cancel() // 取消job
////
////        Thread.sleep(2000)
        ////////////CoroutineContext+Job  Job进行管理

//        runBlocking {
//            val job= Job()
//            launch (coroutineContext+job){
//                delay(500)
//                println("job1 is done")
//            }
//            launch  (coroutineContext+job){
//                delay(800)
//                println("job2 is done")
//            }
//            launch(Dispatchers.Default+job) {
//
//                delay(1500)
//                println("job3 is done")
//            }
//            delay(500)
//            job.cancel()
//        }

    }
}
