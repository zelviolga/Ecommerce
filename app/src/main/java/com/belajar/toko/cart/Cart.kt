package com.belajar.toko.cart

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.toko.R
import com.belajar.toko.RequestHandler
import com.belajar.toko.config.Config
import kotlinx.android.synthetic.main.cart.*
import org.json.JSONObject

class Cart : AppCompatActivity() {

    private var list:MutableList<CartModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart)

        list= mutableListOf()
//        get_data_cart().execute()

        val actionBar = supportActionBar
        actionBar!!.title = "My Cart"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
