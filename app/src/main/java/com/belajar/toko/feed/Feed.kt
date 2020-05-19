package com.belajar.toko.feed

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.toko.R
import com.belajar.toko.RequestHandler
import com.belajar.toko.config.Config
import kotlinx.android.synthetic.main.feed.*
import org.json.JSONObject

class Feed : Fragment() {

    internal lateinit var toolbar: Toolbar
    private var list:MutableList<FeedModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        activity?.title = getString(R.string.title_beranda)
        val view = inflater.inflate(R.layout.feed, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        toolbar =view.findViewById(R.id.tToolbar)  //aktifkan toolbar tanpa cart and settings

        toolbar.title = "Feed News"
        (activity as AppCompatActivity).setSupportActionBar(toolbar) //untuk tambah cart and settings

        list= mutableListOf()
        get_data_berita().execute()
    }


    inner class get_data_berita : AsyncTask<String, Void, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(activity,"","Memuat Berita",true,true)
//            pd = ProgressDialog.show(activity, "", "Memuat.....", true, true)
        }
        override fun doInBackground(vararg params: String?): String {
            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_berita) //"http://192.168.43.93/newss/index.php/Webservice/select_berita"
            Log.d("String",result)
            return result
        }

        override fun onPostExecute(result: String?) {
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= FeedModel()
                model.id_berita=data.getString("id_berita")
                model.judul=data.getString("judul")
                model.isi=data.getString("isi")
                model.kategori=data.getString("kategori")
                model.gambar=data.getString("gambar")
                model.tanggal=data.getString("tanggal")
                list?.add(model)
                val adapter= list?.let {
                    FeedAdapter(
                        it,
                        requireActivity()
                    )
                }
                rc.layoutManager= LinearLayoutManager(context)
                rc.adapter=adapter
            }
            super.onPostExecute(result)
            pd?.dismiss()

        }

    }




}
