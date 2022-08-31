package com.devoratmaa.swiper

import android.app.ActionBar
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_item_show.view.*
import kotlinx.android.synthetic.main.layout_item.view.*

class SwpierRecyclerAdapter : RecyclerView.Adapter<SwpierRecyclerAdapter.ViewHolder> {

    private val TAG = "SwpierRecyclerAdapter"

    var firstPosition = -1
    var middlePosition = 0
    var secondPosition = 1

    var firstView: ViewHolder? = null
    var middleView: ViewHolder? = null
    var secondView: ViewHolder? = null

    var lastPostion = 0

    var cachedVH = HashMap<Int, ViewHolder>()

    private var context: Context
    private var items : ArrayList<ItemModel>

    constructor(context: Context,viewPager: ViewPager2,items:ArrayList<ItemModel>) {
        this.context = context
        this.items = items
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)

//        Log.i(TAG, "onViewAttachedToWindow: " + holder.adapterPosition)
//
////        Handler().postDelayed(Runnable { holder.itemView.cardView.setBackgroundColor(Color.RED) },1000)
//
//        var newPosition = holder.adapterPosition
//
//        cachedVH.put(newPosition, holder)
//
//        if (lastPostion < newPosition ) {
//            secondPosition = newPosition
//            middlePosition = newPosition - 1
//            firstPosition = newPosition - 2
//        }else if(lastPostion == newPosition){
//            secondPosition = newPosition
//            middlePosition = newPosition - 1
//            firstPosition = newPosition - 2
//            if (newPosition == itemCount-3 ){
//                firstPosition = newPosition
//                middlePosition = newPosition + 1
//                secondPosition = newPosition + 2
//            }
//        } else {
//            firstPosition = newPosition
//            middlePosition = newPosition + 1
//            secondPosition = newPosition + 2
//        }
//
//        firstView = cachedVH.get(firstPosition)
//        middleView = cachedVH.get(middlePosition)
//        secondView = cachedVH.get(secondPosition)
//
//        setNormalView(firstView)
//        setNormalView(secondView)
//        setZoomView(middleView)
//        lastPostion = newPosition
    }

    private fun setNormalView(holder : ViewHolder?){

        val parent = holder?.itemView
        parent?.alpha = 0.6f
//        val params = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.MATCH_PARENT)
//        params.setMargins(dpToPx(8f,context),dpToPx(180f,context),dpToPx(8f,context),dpToPx(8f,context))
//
//        parent?.layoutParams = params

    }

    private fun setZoomView(holder : ViewHolder?){

        val parent = holder?.itemView
        parent?.alpha = 1f
//        val params = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.MATCH_PARENT)
//        params.setMargins(dpToPx(8f,context),dpToPx(80f,context),dpToPx(8f,context),dpToPx(8f,context))
//
//        parent?.layoutParams = params

    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
////        Handler().postDelayed(Runnable { holder.itemView.cardView.setBackgroundColor(Color.BLACK) },1000)
//        var detachedPostion = holder.adapterPosition
//        if (detachedPostion == 2 && lastPostion <= detachedPostion) {
//            firstPosition = -1
//            middlePosition = 0
//            secondPosition = 1
//        }else if (detachedPostion == itemCount - 3 && lastPostion >= detachedPostion){
//            firstPosition = itemCount-2
//            middlePosition = itemCount-1
//            secondPosition = itemCount
//        }
//
//        firstView = cachedVH.get(firstPosition)
//        middleView = cachedVH.get(middlePosition)
//        secondView = cachedVH.get(secondPosition)
//
//        setNormalView(firstView)
//        setNormalView(secondView)
//        setZoomView(middleView)

    }

    fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
            .toInt()
    }


    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        Glide.with(context).load(items.get(position).imageURL).centerCrop()
            .into(holder.itemView.img)

        holder.itemView.img.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (item.showInfo){
                    item.showInfo = false
                    hideInfo(holder.itemView)
                }else{
                    item.showInfo = true
                    showInfo(holder.itemView)
                }
            }

        })

        holder.itemView.watchButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(context,ItemShowActivity::class.java)
                intent.putExtra(ItemShowActivity.ITEM_IMAGE,item.imageURL)
                val act : Activity = context as Activity
                val options = ActivityOptions.makeSceneTransitionAnimation(act,holder.itemView.parentView,"itemImage")

                act.startActivity(intent,options.toBundle())
            }

        })

    }

    fun hideInfo(view : View){

        val tr = TranslateAnimation(0.0f,0.0f,0.0f,view.info.height.toFloat())
        tr.duration = 300
        tr.fillAfter = true
        view.info.startAnimation(tr)
        tr.reset()
        tr.start()
        tr.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                view.info.visibility = View.GONE
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }

    fun showInfo(view : View){
        view.info.visibility = View.VISIBLE
        val tr = TranslateAnimation(0.0f,0.0f,view.info.height.toFloat(),0.0f)
        tr.duration = 300
        tr.fillAfter = true
        view.info.startAnimation(tr)
        tr.reset()
        tr.start()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}