package com.work.studyaac.paging.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


class PagingAdapter : PagedListAdapter<PagingAdapter.FeedItem, RecyclerView.ViewHolder>(FeedItem.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PagingViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        (holder as PagingViewHolder).bind(item)
    }

    data class FeedItem(
        val id: String,
        val title: String,
        val description: String,
        val image: String
    ) {

        companion object {
            val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FeedItem?>() {
                override fun areItemsTheSame(old: FeedItem, new: FeedItem): Boolean {
                    return old.id === new.id
                }

                override fun areContentsTheSame(old: FeedItem, new: FeedItem): Boolean {
                    return old.id == new.id
                }
            }
        }

    }

}