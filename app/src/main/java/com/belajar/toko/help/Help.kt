package com.belajar.toko.help

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.toko.R
import com.belajar.toko.RequestHandler
import com.belajar.toko.config.Config
import com.belajar.toko.feed.FeedAdapter
import com.belajar.toko.feed.FeedModel
import kotlinx.android.synthetic.main.feed.*
import org.json.JSONObject

class Help : Fragment() {

    internal lateinit var toolbar: Toolbar
    private var list:MutableList<HelpModel>?=null
    private var pd: ProgressDialog?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        activity?.title = getString(R.string.title_beranda)
        val view = inflater.inflate(R.layout.help, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        toolbar =view.findViewById(R.id.tToolbar)  //aktifkan toolbar tanpa cart and settings

        toolbar.title = "Help"
        (activity as AppCompatActivity).setSupportActionBar(toolbar) //untuk tambah cart and settings

        list= mutableListOf()
        get_data_help().execute()
    }


    inner class get_data_help : AsyncTask<String, Void, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(activity,"","Memuat Help",true,true)
//            pd = ProgressDialog.show(activity, "", "Memuat.....", true, true)
        }
        override fun doInBackground(vararg params: String?): String {
            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_help) //"http://192.168.43.93/newss/index.php/Webservice/select_berita"
            Log.d("String",result)
            return result
        }

        override fun onPostExecute(result: String?) {
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= HelpModel()
                model.help_id=data.getString("help_id")
                model.help_name=data.getString("help_name")
                model.help_description=data.getString("help_description")
                model.help_image=data.getString("help_image")
                list?.add(model)
                val adapter= list?.let {
                    HelpAdapter(
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
