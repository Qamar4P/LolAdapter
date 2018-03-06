package com.qamar4p.lol_adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import butterknife.ButterKnife

/**
 * Created by Qamar4P on 3/5/2018.
 *
 * @author qamar.dev@gmail.com
 */
abstract class LolViewHold<T>(parent: ViewGroup, @LayoutRes viewResId: Int)
    : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(viewResId, parent, false)) {
    var viewClickListener: ItemViewClickListener<T>? = null
    var data: T? = null

    init {
        itemView.setOnClickListener { v ->
            viewClicked(v)
            viewClickListener?.onViewClicked(v, data, adapterPosition)
        }
    }

    protected fun viewClicked(v: View) {}

    internal fun bind(data: T) {
        this.data = data
        bind()
    }

    abstract fun bind()
}