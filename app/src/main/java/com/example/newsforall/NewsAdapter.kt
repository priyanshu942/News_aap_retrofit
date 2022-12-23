package com.example.newsforall

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import java.util.zip.Inflater

class NewsAdapter(val context: Context,val article: List<Article>):
    RecyclerView.Adapter<NewsAdapter.newsView>() {

    class newsView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage=itemView.findViewById<ImageView>(R.id.newsimage)
        val title=itemView.findViewById<TextView>(R.id.newstitle)
        val decription=itemView.findViewById<TextView>(R.id.newsdescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsView {
        val view=LayoutInflater.from(context).inflate(R.layout.items_layout,parent,false)

        return newsView(view)
    }

    override fun onBindViewHolder(holder: newsView, position: Int) {
        val arti=article[position]
        holder.title.text=arti.title
        holder.decription.text=arti.description
        Glide.with(context).load(arti.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener {
            val builder= CustomTabsIntent.Builder()
            val customtab=builder.build()
            customtab.launchUrl(context, Uri.parse(arti.url))

        }


    }

    override fun getItemCount(): Int {
       return article.size
    }
}