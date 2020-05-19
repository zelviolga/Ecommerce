package com.belajar.toko.feed

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.belajar.toko.R
import com.belajar.toko.config.Config
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.feed_adapter.view.*
import kotlinx.android.synthetic.main.toolbarfeeddown.view.*
import java.security.AccessController.getContext


class FeedAdapter (private  val list:MutableList<FeedModel>, private val context: Context):RecyclerView.Adapter<FeedAdapter.ViewHolder>(){

    internal lateinit var toolbarfeeddown: Toolbar


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.feed_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: FeedAdapter.ViewHolder, p1: Int) {
        val item = list[p1]
        p0.judul.text = item.judul
        p0.tanggal.text = item.tanggal
        p0.holder.setOnClickListener {
            val intent = Intent(context, DetailFeed::class.java)
            intent.putExtra(Config.id, item.id_berita)
            intent.putExtra("from", "berita")
            context.startActivity(intent)

        }
        Glide.with(context).load(Config.url_gambar_berita + item.gambar).into(p0.image)
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView =itemView.image
        val judul: TextView =itemView.judul
        val tanggal: TextView =itemView.tanggal
        //        val btnread: Button =itemView.btnread
        val holder: CardView=itemView.holder

    }



}
