package com.example.retrofitcoroutinepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcoroutinepractice.api.ApiInterface
import com.example.retrofitcoroutinepractice.api.RetrofitClient
import com.example.retrofitcoroutinepractice.model.Data

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        getUsersList(recyclerView)
    }

    fun getUsersList(recyclerView: RecyclerView) {

        var retrofit = RetrofitClient.getInstance()
        var endPointPath = retrofit.create(ApiInterface::class.java)

        lifecycleScope.launchWhenCreated {
            try {
                val response = endPointPath.getAllUsers()
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        setAdapter(it, recyclerView)
                    }
                } else {
                    Toast.makeText(this@MainActivity, response.errorBody().toString(), Toast.LENGTH_SHORT).show()
                }
            } catch (Exception: Error) {
                Log.e("Error", endPointPath.getAllUsers().errorBody().toString())
            }
        }
    }

    fun setAdapter(listUsers: List<Data>, recyclerView: RecyclerView) {

        recyclerView.adapter = UsersAdapter(listUsers)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}