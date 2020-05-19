package com.belajar.toko

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.belajar.toko.cart.Cart
import com.belajar.toko.category.Category
import com.belajar.toko.category.CategoryModel
import com.belajar.toko.category.DetailCategory
import com.belajar.toko.config.Config
import com.belajar.toko.feed.Feed
import com.belajar.toko.help.Help
import com.belajar.toko.product.DetailProduct
import com.belajar.toko.product.ProductModel
import com.belajar.toko.profile.Login
import com.belajar.toko.profile.Profile
import com.bumptech.glide.Glide
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {


    internal lateinit var toolbar: Toolbar
    lateinit var fav: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)


        toolbar.title = ""
        setSupportActionBar(toolbar)


        supportFragmentManager.beginTransaction()
            .replace(com.belajar.toko.R.id.coordinatorLayout, Home())
            .commit()


        bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                com.belajar.toko.R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.belajar.toko.R.id.coordinatorLayout, Home())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                com.belajar.toko.R.id.navigation_help -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.belajar.toko.R.id.coordinatorLayout, Help())
                        .commit()
//                    intent = Intent(applicationContext, Help::class.java)
//                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }

                com.belajar.toko.R.id.navigation_feed -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.belajar.toko.R.id.coordinatorLayout, Feed())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                com.belajar.toko.R.id.navigation_profile -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(com.belajar.toko.R.id.coordinatorLayout, Login())
//                        .commit()

                    //untuk code jika sudah login maka yg tampil adalah hal data yg sudah login
                    if(Prefs.contains("user_username")){
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.coordinatorLayout,Profile())
                            .commit()
                    }else{
//            judul.text="Login"
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.coordinatorLayout,Login())
                            .commit()
                    }
                    return@setOnNavigationItemSelectedListener true
                }
//
//                com.online.toko.onlineshop.R.id.navigation_category -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(com.online.toko.onlineshop.R.id.container, CategoryFragment())
//                        .commit()
//                    return@setOnNavigationItemSelectedListener true
//                }

//                com.online.toko.onlineshop.R.id.navigation_help-> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(com.online.toko.onlineshop.R.id.container, HelpFragment())
//                        .commit()
//                    return@setOnNavigationItemSelectedListener true
//                }
//
//                com.online.toko.onlineshop.R.id.navigation_profile -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(com.online.toko.onlineshop.R.id.container, ProfilFragment())
//                        .commit()
//                    return@setOnNavigationItemSelectedListener true
//                }


            }
            return@setOnNavigationItemSelectedListener false
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflate = menuInflater
        menuInflate.inflate(R.menu.main_menu, menu)
//        if (menu != null) {
//            fav = menu.add("My Cart")
//        };
//        fav.setIcon(R.drawable.ic_cart_outline);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        intent = Intent(applicationContext, Cart::class.java)
        when(item.getItemId()){
            R.id.menu_cart -> {
                startActivity(intent)
//                Toast.makeText(applicationContext, "Gak Bisa diKlik", Toast.LENGTH_LONG).show()
            }
            R.id.menu_settings -> {
                Toast.makeText(applicationContext, "Feature is locked", Toast.LENGTH_LONG).show()
            }
            R.id.menu_profile -> {
                supportFragmentManager.beginTransaction()
                    .replace(com.belajar.toko.R.id.coordinatorLayout, Profile())
                    .commit()
            }

            R.id.menu_logout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setMessage("Keluar dari Aplikasi?")
        builder.setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->
            //if user pressed "yes", then he is allowed to exit from application
            finish()
        })
        builder.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->
            //if user select "No", just cancel this dialog and continue with app
            dialog.cancel()
        })
        val alert = builder.create()
        alert.show()
    }


}
