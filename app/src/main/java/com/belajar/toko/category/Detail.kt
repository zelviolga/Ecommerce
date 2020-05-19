package com.belajar.toko.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.belajar.toko.R
import com.belajar.toko.product.DetailProduct

class Detail : DetailProduct() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)
    }
}
