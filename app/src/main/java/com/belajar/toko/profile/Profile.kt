package com.belajar.toko.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.belajar.toko.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.profile.*

class Profile : Fragment() {

    internal lateinit var toolbar: Toolbar

    companion object {
        val TAG: String = Profile::class.java.simpleName
        fun newInstance() = Profile()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        activity?.title = getString(R.string.title_beranda)
        activity?.title = "Profil"
        val view = inflater.inflate(R.layout.profile, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        toolbar =view.findViewById(R.id.tToolbar)  //aktifkan toolbar tanpa cart and settings

        toolbar.title = "My Profile"
        (activity as AppCompatActivity).setSupportActionBar(toolbar) //untuk tambah cart and settings

        username.text="" + Prefs.getString("user_username", "")
//        email.text="Your Email :" + Prefs.getString("user_email", "")
//        fullname.text="Full Name :" + Prefs.getString("user_fullname", "")
//        phone.text="Phone :" + Prefs.getString("user_phone", "")
//        address.text="Address :" + Prefs.getString("user_address", "")
        logout.setOnClickListener { Prefs.clear()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.coordinatorLayout,Login())
                ?.commit()
        }
    }


}
