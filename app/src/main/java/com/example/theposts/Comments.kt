package com.example.theposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Comments : AppCompatActivity() {
    lateinit var tvPostTitle:TextView
    lateinit var tvPostBody:TextView

    var post_id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        post_id =intent.getIntExtra("post_id",0)
        castComments()
        getPost()
        getComments()


    }
    fun castComments(){
        tvPostTitle=findViewById(R.id.tvPostTitle)
        tvPostBody=findViewById(R.id.tvPostBody)
    }
    fun getPost() {
        if (post_id == 0) {
            finish()
        }

        var client = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = client.getPost(post_id)
        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    var post = response.body()
                    tvPostBody.text = post?.body
                    tvPostBody.text = post?.title

                }

            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

               fun getComments(){
                   var rvComments = findViewById<RecyclerView>(R.id.rvComments)

                   var client =ApiClient.buildApiClient(ApiInterface::class.java)
                   var request=client.getComments(post_id)
                   request.enqueue(object:Callback<List<Comment>> {
                       override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>
                       ) {
                           var comment = response.body()!!
                           var commentsadapter = CommentsAdapter(comment)
                           rvComments.adapter = commentsadapter
                           rvComments.layoutManager = LinearLayoutManager(baseContext)

                       }

                       override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                           Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                       }





           })
//getPost()

       }
}

