package com.example.theposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var data: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPosts()
    }
    fun getPosts(){
        data=findViewById(R.id.rvPosts)
        val retrofit = ApiClient.buildApiClient(ApiInterface::class.java) //instance of retrofit
        val request = retrofit.getPosts() //request is made here
        request.enqueue(object : retrofit2.Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful){
                    var postsList =response.body()!!
                    var postAdapter=PostAdapter(postsList,baseContext)
                    data.adapter=postAdapter
                    data.layoutManager= LinearLayoutManager(baseContext)

                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(baseContext,t.message, Toast.LENGTH_LONG).show()

            }
        })
    }
}