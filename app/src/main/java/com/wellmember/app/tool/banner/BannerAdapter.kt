package com.wellmember.app.tool.banner

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.wellmember.app.R

class BannerAdapter : BaseBannerAdapter<Int, BannerAdapter.ViewHolder>() {

    override fun getLayoutId(viewType: Int) = R.layout.item_banner_small

    override fun onBind(holder: ViewHolder, data: Int, position: Int, pageSize: Int) {

        holder.imageView.setImageResource(data)
    }


    override fun createViewHolder(parent: ViewGroup, itemView: View, viewType: Int): ViewHolder {
        return ViewHolder(itemView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.iv_banner)
    }
}
