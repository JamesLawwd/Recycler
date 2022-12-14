package com.example.recyclerproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    // Declaration for resources

    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var customAdapter: RecyclerAdapter
    lateinit var productList: ArrayList<ItemViewModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        progressBar.visibility = View.VISIBLE

        val client = AsyncHttpClient(true,80,443)
        // pass the productList to adapter
        customAdapter = RecyclerAdapter(applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)

        client.get(this,"http://jameskinyanjui.pythonanywhere.com/products", null, "application/json",
            object: JsonHttpResponseHandler(){
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONArray?
                ) {
                    val gson = GsonBuilder().create()
                    val list= gson.fromJson(response.toString(),
                    Array<ItemViewModel>::class.java).toList()

                    customAdapter.setProductListItems(list)

                    progressBar.visibility = View.GONE

//                    super.onSuccess(statusCode, headers, response)
                }


                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?,
                    throwable: Throwable?
                ) {
                      Toast.makeText(applicationContext, "No data Available" + statusCode, Toast.LENGTH_SHORT).show()
                      progressBar.visibility = View.GONE
//                    super.onFailure(statusCode, headers, responseString, throwable)
                }
            }// end response handler

        )// end client

     recyclerView.adapter = customAdapter

    }
}