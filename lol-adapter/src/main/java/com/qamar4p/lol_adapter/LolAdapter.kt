package com.qamar4p.lol_adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

import butterknife.ButterKnife

/**
 * Created by Qamar4P on 3/5/2018.
 *
 * @author qamar.dev@gmail.com
 */
open class LolAdapter<D, VH : LolViewHold<D>>(private val viewClickListener: ItemViewClickListener<D>?,
                                              internal var viewHolderCreator: (ViewGroup) -> VH)
    : RecyclerView.Adapter<VH>() {

    var items: List<D> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val vh = viewHolderCreator.invoke(parent)
//        vh.viewClickListener = viewClickListener
        return vh
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}