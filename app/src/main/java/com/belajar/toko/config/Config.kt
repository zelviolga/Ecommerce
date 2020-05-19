package com.belajar.toko.config

object Config {

    private const val Host="http://192.168.18.16/ecommerce/" //yang bakal berganti
    const val url_gambar= Host+"assets/upload_berita/"
    const val url_gambar_category= Host+"assets/upload_berita/"
    const val id="id"
    val url_detail_category = Host+"index.php/Webservice/select_by_get_category/"
    val url_category = Host+"index.php/Webservice/select_category/"

    const val url_gambar_product= Host+"assets/upload_berita/"
    val url_detail_product = Host+"index.php/Webservice/select_by_get_product/"
    val url_product = Host+"index.php/Webservice/select_product/"

    const val url_gambar_berita= Host+"assets/upload_berita/"
    val url_berita = Host+"index.php/Webservice/select_berita/"

    val url_help = Host+"index.php/Webservice/select_help/"
    const val url_gambar_help= Host+"assets/upload_berita/"
    val url_detail_help = Host+"index.php/Webservice/select_by_get_help/"

    val url_login =  Host+"index.php/Login/select/"

    val url_register = Host+"api/register.php/"

    val url_detail_layanan = Host+"index.php/Webservice/select_by_get_layanan/"

    val url_addtocart = Host+"index.php/Webservice/select_addtocart/"
}