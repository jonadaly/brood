package com.jonadaly.brood.core

import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jonadaly.brood.R


abstract class BasePagedListAdapter(diffCallback: DiffUtil.ItemCallback<BaseEntity> = BaseDiffCallback()) : PagedListAdapter<BaseEntity, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = getViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<*>).binding.root.setTag(R.string.position, position)
        bind(holder.binding, position)
    }

    open fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = BaseViewHolder(createBinding(parent, viewType))

    abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding

    protected abstract fun bind(binding: ViewDataBinding, position: Int)

    fun setList(list: PagedList<*>?) {
        submitList(list as PagedList<BaseEntity>)
    }

    companion object {
        /**
         * Diff callback informs PagedListAdapter how to compute list differences when new
         * PagedLists arrive. This allows efficient animation and rebinding of new items.
         */
        open class BaseDiffCallback : DiffUtil.ItemCallback<BaseEntity>() {
            override fun areItemsTheSame(oldItem: BaseEntity, newItem: BaseEntity): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: BaseEntity, newItem: BaseEntity): Boolean = oldItem == newItem
        }
    }
}