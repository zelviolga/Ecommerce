package com.belajar.toko.help

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.belajar.toko.R
import com.belajar.toko.RequestHandler
import com.belajar.toko.config.Config
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_help.*
import org.json.JSONObject

class DetailHelp : AppCompatActivity() {

    private var id:String?=null
    private var pd: ProgressDialog?=null
    private var from :String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_help)

        id=intent.getStringExtra(Config.id)
        from=intent.getStringExtra("from")
        Toast.makeText(this@DetailHelp, from, Toast.LENGTH_SHORT).show()
        if (from.equals("help",true)){
            getdetailhelp().execute()
        }else{
            getdetailhelp().execute()
        }
    }


    inner class getdetailhelp : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String {
            val request = RequestHandler()
            return request.sendGetRequest(Config.url_detail_help
                    + id)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailHelp,"","Wait...",false,true)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            if (objek.getInt("status")==1){
                Toast.makeText(this@DetailHelp, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    name.text = data.getString("help_name")
                    description.text = data.getString("help_description")
                    Glide.with(this@DetailHelp)
                        .load(Config.url_gambar+ data.getString("help_image")).into(image)
                }
            }
        }



    }


}
