package com.belajar.toko

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import com.belajar.toko.category.Category
import com.belajar.toko.category.CategoryModel
import com.belajar.toko.category.DetailCategory
import com.belajar.toko.config.Config
import com.belajar.toko.product.DetailProduct
import com.belajar.toko.product.ProductModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class Xx : AppCompatActivity() {

    private var pd: ProgressDialog? = null
    private var list: MutableList<String>? = null
    private var listid: MutableList<String>? = null
    private var listjudul: MutableList<String>? = null

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
        fun newInstance() = MainActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        btnkeranjang.setOnClickListener {
//            startActivity(Intent(this, Category::class.java))
//        }
    }

//    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
//
//        list = mutableListOf()
//        listid = mutableListOf()
//        listjudul = mutableListOf()
//
//        get_data_imageslider().execute()
//
//        return super.onCreateView(name, context, attrs)
//
//
//    }
//
//    inner class get_data_imageslider : AsyncTask<String, Void, String>() {
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            pd = ProgressDialog.show(this@MainActivity, "", "Memuat.....", true, true)
//        }
//
//        override fun doInBackground(vararg params: String?): String {
//
//            val handler = RequestHandler()
//            val result = handler.sendGetRequest(Config.url_category)
//            return result
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            pd?.dismiss()
//            val objek = JSONObject(result)
//            if (objek.getInt("status") == 1) {
//                Toast.makeText(this@MainActivity, "Tidak ada data!", Toast.LENGTH_SHORT).show()
//            } else {
//                val array = objek.getJSONArray("data")
//                for (i in 0 until array.length()) {
//                    val data = array.getJSONObject(i)
//                    val model = CategoryModel()
//                    model.category_id = data.getString("category_id")
//                    model.category_name = data.getString("category_name")
//                    model.category_description = data.getString("category_description")
//                    model.category_image = Config.url_gambar + data.getString("category_image")
//                    list?.add(model.category_image ?: "")
//                    listid?.add(model.category_id ?: "")
//                    listjudul?.add(model.category_name ?: "")
//
//                }
//                try {
//                    setToImageSlider(list, listid)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
//
//
//        private fun setToImageSlider(
//            list: MutableList<String>?,
//            listid: MutableList<String>?
//        ) {
//
//            imageslider.setImageListener { position, imageView ->
//                Log.d("category_image", list?.get(position).toString())
//                this@MainActivity?.let { Glide.with(it).load(list?.get(position)).into(imageView) }
//
//            }
//
//            imageslider.pageCount = list?.size ?: 0
//
//            imageslider.setImageClickListener { it ->
//
//                val intent = Intent(this@MainActivity, DetailCategory::class.java)
//                intent.putExtra(Config.id, listid?.get(it))
//                intent.putExtra("from", "category")
//                startActivity(intent)
//
//            }
//        }
//
//
//    }
}
