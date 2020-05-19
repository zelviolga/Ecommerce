package com.belajar.toko

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.belajar.toko.category.Category
import com.belajar.toko.category.CategoryModel
import com.belajar.toko.category.DetailCategory
import com.belajar.toko.config.Config
import com.belajar.toko.product.DetailProduct
import com.belajar.toko.product.Product
import com.belajar.toko.product.ProductModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home.*
import org.json.JSONObject
import java.util.*

class Home : Fragment(), View.OnClickListener {

    internal lateinit var greetText: TextView
    internal lateinit var toolbar: Toolbar
    lateinit var fav: MenuItem

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnkeranjang->{
                var i: Intent
                i= Intent(activity,Category::class.java)
                activity?.startActivity(i)
            }
            R.id.btnproduct->{
                var i: Intent
                i= Intent(activity, Product::class.java)
                activity?.startActivity(i)
            }
//            R.id.button_web->{
//                activity?.supportFragmentManager?.beginTransaction()
//                    ?.replace(R.id.container_layout, WebWisata())?.commit()
//                activity?.nav_view
//                    ?.menu?.getItem(2)?.isChecked=true
//            }
//            R.id.button_lokasi->{
//                activity?.supportFragmentManager?.beginTransaction()
//                    ?.replace(R.id.container_layout, PetaKebumen())?.commit()
//                activity?.nav_view
//                    ?.menu?.getItem(3)?.isChecked=true
//            }
//            R.id.button_video->{
//                var i: Intent
//                activity?.nav_view
//                    ?.menu?.getItem(4)?.isChecked=true
//                i= Intent(activity,VideoWisata::class.java)
//                activity?.startActivity(i)
//            }
//            R.id.button_galeri->{
//                activity?.supportFragmentManager?.beginTransaction()
//                    ?.replace(R.id.container_layout, GaleriWisata())?.commit()
//                activity?.nav_view
//                    ?.menu?.getItem(5)?.isChecked=true
//            }
//            R.id.button_tentang->{
//                activity?.supportFragmentManager?.beginTransaction()
//                    ?.replace(R.id.container_layout, Profile())?.commit()
//                activity?.nav_view
//                    ?.menu?.getItem(6)?.isChecked=true
//            }
        }
    }

    private var pd: ProgressDialog? = null
    private var list: MutableList<String>? = null
    private var listid: MutableList<String>? = null
    private var listjudul: MutableList<String>? = null
    lateinit var btnkeranjang: ImageView
    lateinit var btnproduct: ImageView


    companion object {
        val TAG: String = Home::class.java.simpleName
        fun newInstance() = Home()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        activity?.title = getString(R.string.title_beranda)
        val view = inflater.inflate(R.layout.home, container, false)
        return view

    }

    private fun greeting() {
        val calendar = Calendar.getInstance()
        val timeOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        if (timeOfDay >= 0 && timeOfDay < 11) {
            greetText.text = getString(R.string.salam_pagi)
        } else if (timeOfDay >= 11 && timeOfDay < 15) {
            greetText.text = getString(R.string.salam_siang)
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            greetText.text = getString(R.string.salam_sore)
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            greetText.text = getString(R.string.salam_malam)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        btnkeranjang=view.findViewById(R.id.btnkeranjang)
        btnproduct=view.findViewById(R.id.btnproduct)
        greetText =view.findViewById(R.id.greeting_text)
        toolbar =view.findViewById(R.id.toolbar)  //aktifkan toolbar tanpa cart and settings

        btnkeranjang.setOnClickListener(this)
        btnproduct.setOnClickListener(this)

        


        toolbar.title = ""
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        list = mutableListOf()
        listid = mutableListOf()
        listjudul = mutableListOf()

//        setHasOptionsMenu(true)  //aktifkan tambah menu dibawah settings (titik tiga)

        get_data_imageslider().execute()

        greeting()
    }


//    //menambahkan menu di bagian titik tiga settings
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        fav = menu.add("Cart");
//        fav.setIcon(R.drawable.ic_cart_outline);
//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        var menuInflate = menuInflater
//        menuInflate.inflate(R.menu.main_menu, menu)
//        return true
//    }



    inner class get_data_imageslider : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(activity, "", "Memuat.....", true, true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler = RequestHandler()
            val result = handler.sendGetRequest(Config.url_product)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek = JSONObject(result)
            if (objek.getInt("status") == 1) {
                Toast.makeText(activity, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            } else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    val model = ProductModel()
                    model.product_id = data.getString("product_id")
                    model.product_name = data.getString("product_name")
                    model.product_description = data.getString("product_description")
                    model.product_stock = data.getString("product_stock")
                    model.product_price = data.getString("product_price")
                    model.product_image = Config.url_gambar + data.getString("product_image")
                    model.category_id = data.getString("category_id")
                    list?.add(model.product_image ?: "")
                    listid?.add(model.product_id ?: "")
                    listjudul?.add(model.product_name ?: "")

                }
                try {
                    setToImageSlider(list, listid)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

        private fun setToImageSlider(
            list: MutableList<String>?,
            listid: MutableList<String>?
        ) {

            imageslider.setImageListener { position, imageView ->
                Log.d("product_image", list?.get(position).toString())
                activity?.let { Glide.with(it).load(list?.get(position)).into(imageView) }

            }

            imageslider.pageCount = list?.size ?: 0

            imageslider.setImageClickListener { it ->

                val intent = Intent(context, DetailProduct::class.java)
                intent.putExtra(Config.id, listid?.get(it))
                intent.putExtra("from", "product")
                context?.startActivity(intent)

            }
        }

    }


}
