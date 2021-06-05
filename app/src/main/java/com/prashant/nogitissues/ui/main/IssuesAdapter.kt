package com.prashant.nogitissues.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prashant.nogitissues.data.IssueItem
import com.prashant.nogitissues.databinding.ItemIssueBinding

class IssuesAdapter(private val clickListener: ItemClickListener) :
    RecyclerView.Adapter<IssuesAdapter.IssueViewHolder>() {

    interface ItemClickListener {
        fun onItemClicked(item:IssueItem)
    }

    private var issueList = listOf<IssueItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val binding = ItemIssueBinding.inflate(LayoutInflater.from(parent.context))
        return IssueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        with(holder.binding){
            model = issueList[position]
//            itemClick = clickListener

        }
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = issueList.size

    fun set(profileList: List<IssueItem>) {
        this.issueList = profileList
        notifyDataSetChanged()
    }

    class IssueViewHolder(val binding: ItemIssueBinding) : RecyclerView.ViewHolder(binding.root)
}