package com.belajar.toko.category

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.toko.R
import com.belajar.toko.RequestHandler
import com.belajar.toko.config.Config
import com.belajar.toko.product.Product
import com.belajar.toko.product.ProductAdapter
import com.belajar.toko.product.ProductModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.category.*
import kotlinx.android.synthetic.main.detail_category.*
import kotlinx.android.synthetic.main.detail_category.rcl
import org.json.JSONObject

class DetailCategory : AppCompatActivity() {

    private var list:MutableList<ProductModel>?=null
    private var pd: ProgressDialog?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_category)

        list= mutableListOf()
        get_data_product().execute()
    }

    inner class get_data_product : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailCategory,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_product) //"http://192.168.43.93/newss/index.php/Webservice/select_berita"
            Log.d("String",result)
            return result
        }

        override fun onPostExecute(result: String?) {
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= ProductModel()
                model.product_id=data.getString("product_id")
                model.product_name=data.getString("product_name")
                model.product_description=data.getString("product_description")
                model.product_stock=data.getString("product_description")
                model.product_price=data.getString("product_price")
                model.product_image=data.getString("product_image")
                list?.add(model)
                val adapter= list?.let {
                    ProductAdapter(
                        it,
                        this@DetailCategory//buat dulu adpaterny
                    )
                }
                rcl.layoutManager= LinearLayoutManager(this@DetailCategory)
                rcl.adapter=adapter
            }
            super.onPostExecute(result)
            pd?.dismiss()

        }
    }
}
