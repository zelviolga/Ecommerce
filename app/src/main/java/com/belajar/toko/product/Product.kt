package com.belajar.toko.product

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.toko.R
import com.belajar.toko.RequestHandler
import com.belajar.toko.config.Config
import kotlinx.android.synthetic.main.category.*
import org.json.JSONObject

class Product : AppCompatActivity() {

    private var list:MutableList<ProductModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product)



        list= mutableListOf()
        get_data_product().execute()

        val actionBar = supportActionBar
        actionBar!!.title = "Product"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }


    inner class get_data_product : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@Product,"","Wait",true,true)
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
                        this@Product//buat dulu adpaterny
                    )
                }
                rcl.layoutManager= LinearLayoutManager(this@Product)
                rcl.adapter=adapter
            }
            super.onPostExecute(result)
            pd?.dismiss()

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
