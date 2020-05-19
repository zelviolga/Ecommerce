package com.belajar.toko.product

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
import kotlinx.android.synthetic.main.product_adapter.view.*

class ProductAdapter (private val list:MutableList<ProductModel>, private val context: Context):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductAdapter.ViewHolder {


        val v = LayoutInflater.from(context).inflate(R.layout.product_adapter, p0, false)
        return ProductAdapter.ViewHolder(v)

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

    override fun onBindViewHolder(p0: ProductAdapter.ViewHolder, p1: Int) {
        val item = list[p1]
        p0.productname.text = item.product_name
        p0.productprice.text = item.product_price
        p0.relative.setOnClickListener {
            val intent = Intent(context, DetailProduct::class.java)
            intent.putExtra(Config.id, item.product_id)
            intent.putExtra("from", "product")
            context.startActivity(intent)
        }
        Glide.with(context).load(Config.url_gambar_product + item.product_image).into(p0.productimage)
    }
}
