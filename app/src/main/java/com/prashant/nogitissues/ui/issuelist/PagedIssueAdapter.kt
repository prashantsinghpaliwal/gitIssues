package com.prashant.nogitissues.ui.issuelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prashant.nogitissues.data.IssueItem
import com.prashant.nogitissues.databinding.ItemIssueBinding

class PagedIssueAdapter(private val clickListener: ItemClickListener) :
    PagedListAdapter<IssueItem, PagedIssueAdapter.IssueViewHolder>(IssueItem.CALLBACK) {

    interface ItemClickListener {
        fun onItemClicked(item: IssueItem)
    }

    class IssueViewHolder(val binding: ItemIssueBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val binding = ItemIssueBinding.inflate(LayoutInflater.from(parent.context))
        return IssueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        with(holder.binding){
            model = getItem(position)
            itemClick = clickListener

        }
        holder.binding.executePendingBindings()
    }
}