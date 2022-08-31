package com.devoratmaa.swiper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_item_show.*

class ItemShowActivity : AppCompatActivity() {

    companion object{
        val ITEM_IMAGE = "itemImage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_show)

        Glide.with(this).load(intent.extras?.getString(ITEM_IMAGE)).centerCrop()
            .into(imageView)



    }
}