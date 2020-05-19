package com.belajar.toko.category

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belajar.toko.R
import com.belajar.toko.config.Config
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.category_adapter.view.*

class CategoryAdapter (private val list:MutableList<CategoryModel>, private val context: Context):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {


        val v = LayoutInflater.from(context).inflate(R.layout.category_adapter, p0, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return list.size

    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView =itemView.image
        val name: TextView =itemView.name
        val relative : RelativeLayout = itemView.relative
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item = list[p1]
        p0.name.text = item.category_name
        p0.relative.setOnClickListener {
            val intent = Intent(context, DetailCategory::class.java)
            intent.putExtra(Config.id, item.category_id)
            intent.putExtra("from", "category")
            context.startActivity(intent)

        }
        Glide.with(context).load(Config.url_gambar_category + item.category_image).into(p0.image)
    }
}
