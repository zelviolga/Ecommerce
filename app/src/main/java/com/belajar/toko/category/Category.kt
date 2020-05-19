package com.belajar.toko.category

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

class Category : AppCompatActivity() {

    private var list:MutableList<CategoryModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category)



        list= mutableListOf()
        get_data_category().execute()

        val actionBar = supportActionBar
        actionBar!!.title = "Category"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    inner class get_data_category : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@Category,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_category) //"http://192.168.43.93/newss/index.php/Webservice/select_berita"
            Log.d("String",result)
            return result
        }

        override fun onPostExecute(result: String?) {
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= CategoryModel()
                model.category_id=data.getString("category_id")
                model.category_name=data.getString("category_name")
                model.category_description=data.getString("category_description")
                model.category_image=data.getString("category_image")
                list?.add(model)
                val adapter= list?.let {
                    CategoryAdapter(
                        it,
                        this@Category//buat dulu adpaterny
                    )
                }
                rcl.layoutManager= LinearLayoutManager(this@Category)
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
