package com.example.kucingapes.belajarmvp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.BitmapRequestListener
import com.example.kucingapes.belajarmvp.model.User
import kotlinx.android.synthetic.main.item_row.view.*

class UserAdapter(var list: ArrayList<User>,
                  var onitemClick: RecyclerItemClick) : RecyclerView.Adapter<UserAdapter.Holder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_row, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: Holder, p1: Int) {
        val user = list[p1]
        val avatar = p0.itemView.image_profil
        val name = p0.itemView.name
        val url = p0.itemView.url

        name.text = "${user.first_name} ${user.last_name}"
        url.text = user.avatar
        p0.itemView.setOnClickListener {
            onitemClick.onItemClick(user)
        }
        getImage(user.avatar, avatar)
    }

    private fun getImage(url: String, image: ImageView) {
        AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .setBitmapConfig(Bitmap.Config.ARGB_8888)
                .build()
                .getAsBitmap(object : BitmapRequestListener {
                    override fun onResponse(response: Bitmap?) {
                        image.setImageBitmap(response)
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("anjir", anError!!.message)
                    }

                })
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}