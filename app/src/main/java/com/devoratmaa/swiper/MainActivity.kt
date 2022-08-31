package com.devoratmaa.swiper

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.*
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item.view.*


class MainActivity : AppCompatActivity() {
    var items : ArrayList<ItemModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        items.add(ItemModel("https://static.wikia.nocookie.net/cinemorgue/images/7/7c/Godzilla_vs_kong_ver12_xlg.jpg/revision/latest?cb=20210404161628"))
        items.add(ItemModel("https://cdn.europosters.eu/image/750/posters/black-widow-unfinished-business-i108309.jpg"))
        items.add(ItemModel("https://m.media-amazon.com/images/I/818KRi8rtnL._SL1500_.jpg"))
        items.add(ItemModel("https://i.ytimg.com/vi/Lan7rVoUnC0/movieposter.jpg"))
        items.add(ItemModel("https://lumiere-a.akamaihd.net/v1/images/p_shangchiandthelegendofthetenringshomeentupdate_22055_7b62fa67.jpeg?region=0%2C0%2C540%2C800"))
        items.add(ItemModel("https://s3.amazonaws.com/static.rogerebert.com/uploads/movie/movie_poster/luck-2022/large_luck-movie-poster-2022.jpeg"))
        items.add(ItemModel("https://qqcdnpictest.mxplay.com/pic/452b1863d450b2bb76aeb9dff933db82/en/2x3/320x480/509de1e1cf9157c15a9ea442f34446bb_1280x1920.webp"))
//        items.add(ItemModel("https://cdn.europosters.eu/image/750/posters/black-widow-unfinished-business-i108309.jpg"))
//        items.add(ItemModel("https://m.media-amazon.com/images/I/818KRi8rtnL._SL1500_.jpg"))

        viewPager.adapter = SwpierRecyclerAdapter(this,viewPager,items)

        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(object : ViewPager2.PageTransformer{
            override fun transformPage(page: View, position: Float) {
//                Toast.makeText(applicationContext,"POS : "+page.numberTxt,Toast.LENGTH_SHORT).show()
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f+r*0.15f

            }

        })
        viewPager.setPageTransformer(compositePageTransformer)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                Glide.with(baseContext).load(items.get(position).imageURL).centerCrop()
                    .into(img)

            }
        })


    }

}