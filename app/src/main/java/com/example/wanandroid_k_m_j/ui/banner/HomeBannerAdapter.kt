package com.example.wanandroid_k_m_j.ui.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.ui.main.home.BannerData
import com.youth.banner.adapter.BannerAdapter

/**
 * home banner adapter
 */
class HomeBannerAdapter(mDatas: List<BannerData>) : BannerAdapter<BannerData, HomeBannerAdapter.ImageTitleHolder>(mDatas) {
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ImageTitleHolder {
        return ImageTitleHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_banner, parent, false)
        )
    }

    override fun onBindView(holder: HomeBannerAdapter.ImageTitleHolder, data: BannerData, position: Int, size: Int) {
        Glide.with(holder.itemView).load(data.imagePath).into(holder.imageView)
        holder.title.setText(data.title)
    }

    inner class ImageTitleHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView
        var title: TextView

        init {
            imageView = view.findViewById(R.id.banner_img)
            title = view.findViewById(R.id.banner_title)
        }
    }
}