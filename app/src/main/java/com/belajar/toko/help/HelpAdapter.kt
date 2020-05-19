package com.belajar.toko.help

import android.content.Context
import android.content.Intent
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
import com.belajar.toko.category.CategoryAdapter
import com.belajar.toko.category.CategoryModel
import com.belajar.toko.category.DetailCategory
import com.belajar.toko.config.Config
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.category_adapter.view.*

class HelpAdapter (private val list:MutableList<HelpModel>, private val context: Context):
    RecyclerView.Adapter<HelpAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HelpAdapter.ViewHolder {


        val v = LayoutInflater.from(context).inflate(R.layout.help_adapter, p0, false)
        return HelpAdapter.ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return list.size

    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView =itemView.image
        val name: TextView =itemView.name
        val relative : RelativeLayout = itemView.relative
    }

    override fun onBindViewHolder(p0: HelpAdapter.ViewHolder, p1: Int) {
        val item = list[p1]
        p0.name.text = item.help_name
        p0.relative.setOnClickListener {
            val intent = Intent(context, DetailHelp::class.java)
            intent.putExtra(Config.id, item.help_id)
            intent.putExtra("from", "help")
            context.startActivity(intent)

        }
        Glide.with(context).load(Config.url_gambar_help + item.help_image).into(p0.image)
    }
}
