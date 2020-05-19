package com.belajar.toko.product

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
import com.belajar.toko.cart.Cart
import com.belajar.toko.cart.CartAdapter
import com.belajar.toko.cart.CartModel
import com.belajar.toko.config.Config
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cart.*
import kotlinx.android.synthetic.main.detail_product.*
import org.json.JSONObject

open class DetailProduct : AppCompatActivity() {

    private var id: String? = null
    private var pd: ProgressDialog? = null
    private var from: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_product)

        id = intent.getStringExtra(Config.id)
        from = intent.getStringExtra("from")
        Toast.makeText(this@DetailProduct, from, Toast.LENGTH_SHORT).show()
        if (from.equals("product", true)) {
            getdetailproduct().execute()
        } else {
            getdetailproduct().execute()
        }

        addtocart.setOnClickListener {
            get_data_cart().execute()
        }
    }

    inner class getdetailproduct : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String {
            val request = RequestHandler()
            return request.sendGetRequest(Config.url_detail_product + id)
        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(this@DetailProduct, "", "Wait...", false, true)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek = JSONObject(result)
            if (objek.getInt("status") == 1) {
                Toast.makeText(this@DetailProduct, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            } else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    name.text = data.getString("product_name")
                    price.text = data.getString("product_price")
                    stock.text = data.getString("product_stock")
                    description.text = data.getString("product_description")
                    Glide.with(this@DetailProduct)
                        .load(Config.url_gambar + data.getString("product_image")).into(image)
                }
            }
        }


    }

    inner class get_data_cart : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(this@DetailProduct, "", "Wait", true, true)
        }

        override fun doInBackground(vararg params: String?): String {
            val param = HashMap<String, String>()
            param["id_produk"] = "id_produk"
            param["id_user"] = "id_user"
            param["quantity"] = "quantity"
            val handler = RequestHandler()
            val result = handler.sendPostRequest(Config.url_addtocart, param)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val jsonObject= JSONObject(result)
            Toast.makeText(this@DetailProduct, jsonObject.getString("response"), Toast.LENGTH_LONG).show()
        }
    }


}
