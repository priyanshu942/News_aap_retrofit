package com.example.newsforall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.browser.trusted.ScreenOrientation
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexyuzefovich.stacklayoutmanager.StackLayoutManager
import com.alexyuzefovich.stacklayoutmanager.helper.SmartPagerSnapHelper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    var page=1
    var totalresult=-1
    var Tag="Oew"
    private var list= mutableListOf<Article>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter= NewsAdapter(this@MainActivity,list)
        recycleView.adapter=adapter
//        recycleView.layoutManager=LinearLayoutManager(this@MainActivity)

        val layoutmanager=StackLayoutManager()
        layoutmanager.setScaleFactor(0.5f)
        val smartpage=SmartPagerSnapHelper()
        recycleView.layoutManager=layoutmanager
        smartpage.attachToRecyclerView(recycleView)
//        Log.d(Tag, "total-- ${layoutmanager.itemCount}")
//        Log.d(Tag,"first item --${layoutmanager.getFirstVisibleItemPosition}")
        fetchdata()

    }
    private fun fetchdata()
    {
        val news =getdata.NewsService.instance.data("in",page)

        news.enqueue(object : Callback<Newdata?> {

            override fun onFailure(call: Call<Newdata?>, t: Throwable) {
                Log.d("CHESSYCODE","Error in fetching news")
            }

            override fun onResponse(call: Call<Newdata?>, response: Response<Newdata?>) {
                val News=response.body()
                if(News!=null) {
                    totalresult=News.totalResults
                    Log.d("CHESSYCODE", News.toString())
                    list.addAll(News.articles)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}