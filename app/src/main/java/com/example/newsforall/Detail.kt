package com.example.newsforall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_detail.*

class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val url:String?=intent.getStringExtra("URL")
        if (url!=null)
        {
            web.settings.javaScriptEnabled=true

            web.webViewClient=object: WebViewClient()
            {

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility= View.GONE
                    web.visibility=View.VISIBLE
                }
            }
            web.loadUrl(url)
        }
    }
}