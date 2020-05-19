package com.belajar.toko.category

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
import com.belajar.toko.config.Config
import com.belajar.toko.product.ProductModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detailcategory_adapter.view.*

class DetailcategoryAdapter (private val list:MutableList<ProductModel>, private val context: Context):
    RecyclerView.Adapter<DetailcategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DetailcategoryAdapter.ViewHolder {

        val v = LayoutInflater.from(context).inflate(R.layout.detailcategory_adapter, p0, false)
        return DetailcategoryAdapter.ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return list.size

    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val productimage: ImageView =itemView.productimage
        val productname: TextView =itemView.productname
        val productprice: TextView =itemView.productprice
        val relative : RelativeLayout = itemView.relative
    }

    override fun onBindViewHolder(p0: DetailcategoryAdapter.ViewHolder, p1: Int) {
        val item = list[p1]
        p0.productname.text = item.product_name
        p0.productprice.text = item.product_price
        p0.relative.setOnClickListener {
            val intent = Intent(context, DetailcategoryAdapter::class.java)
            intent.putExtra(Config.id, item.product_id)
            intent.putExtra("from", "product")
            context.startActivity(intent)
        }
        Glide.with(context).load(Config.url_gambar_product + item.product_image).into(p0.productimage)
    }


}
