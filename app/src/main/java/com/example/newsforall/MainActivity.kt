package com.example.newsforall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchdata()

    }
    private fun fetchdata()
    {
        val news =getdata.NewsService.instance.data("in",1)

        news.enqueue(object : Callback<Newdata?> {

            override fun onFailure(call: Call<Newdata?>, t: Throwable) {
                Log.d("CHESSYCODE","Error in fetching news")
            }

            override fun onResponse(call: Call<Newdata?>, response: Response<Newdata?>) {
                val News=response.body()
                if(News!=null) {
                    Log.d("CHESSYCODE", News.toString())
                    adapter= NewsAdapter(this@MainActivity,News.articles)
                    recycleView.adapter=adapter
                    recycleView.layoutManager=LinearLayoutManager(this@MainActivity)
                }
            }
        })
    }
}