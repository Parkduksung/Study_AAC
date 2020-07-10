package com.work.studyaac.paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.work.studyaac.R
import com.work.studyaac.network.model.paging.Item

class PagingViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = view.findViewById(R.id.textViewName)
    private val image: ImageView = view.findViewById(R.id.imageView)

    fun bind(item: Item) {
        name.text = item.owner.displayName
        Glide.with(view)
            .load(item.owner.profileImage)
            .into(image)
    }


    companion object {
        fun create(parent: ViewGroup): PagingViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.paging_item, parent, false)
            return PagingViewHolder(view)
        }
    }
}