package com.belajar.toko.profile

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.belajar.toko.R
import com.belajar.toko.Register
import com.belajar.toko.RequestHandler
import com.belajar.toko.config.Config
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.login.*
import org.json.JSONObject

class Login : Fragment() {

    internal lateinit var toolbar: Toolbar

    private var pd: ProgressDialog?=null
    private var params:HashMap<String,String>?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        activity?.title = getString(com.belajar.toko.profile.R.string.title_login)
        return inflater.inflate(R.layout.login,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar =view.findViewById(R.id.tToolbar)  //aktifkan toolbar tanpa cart and settings

        toolbar.title = "Login"
        (activity as AppCompatActivity).setSupportActionBar(toolbar) //untuk tambah cart and settings


        params= hashMapOf()

        login.setOnClickListener {
            get_data_login().execute()
        }

        signuppengaduan.setOnClickListener {
//                        activity?.supportFragmentManager?.beginTransaction()
//                    ?.replace(R.id.container,Signup())
//                    ?.commit()
            startActivity(Intent(activity, Register::class.java))
        }
    }

    inner class get_data_login : AsyncTask<String, Void, String>(){

        override fun onPreExecute() {   //method from asyntask, ecxecuted in first thread before Async excecution
            super.onPreExecute()
            pd= ProgressDialog.show(activity,"","Wait",true,true)
        }

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg param: String?): String {    //method Async

            val handler= RequestHandler()
            params?.put("username",username.text.toString())
            params?.put("password",password.text.toString())

            val result= params?.let { handler.sendPostRequest(Config.url_login, it) }
            return result?:""
        }

        override fun onPostExecute(result: String?) {   //method Async result
            super.onPostExecute(result)
            pd?.dismiss()
            try{
                Log.d("result",result)
                val objek= JSONObject(result)
                if (objek.getInt("status")==1){
                    Toast.makeText(activity, "Login Failed!", Toast.LENGTH_SHORT).show()
                }else{
                    Prefs.putString("user_username",username.text.toString())
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.coordinatorLayout,Profile())
                        ?.commit()
                    Toast.makeText(activity, "Login Success!", Toast.LENGTH_SHORT).show()
                }
            }catch(e:Exception){
                e.printStackTrace()
            }

        }

    }
}
