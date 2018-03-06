package com.qamar4p.lol_adapter

import android.view.ViewGroup

import java.util.ArrayList

/**
 * Created by Qamar4P on 3/5/2018.
 *
 * @author qamar.dev@gmail.com
 */
class LolTypeAdapter<D, VH : LolViewHold<D>>(itemClickListener: ItemViewClickListener<D>,
                                             private val whatItemViewType: (Int) -> Int,
                                             private vararg val viewHolderCreators: (ViewGroup) -> VH)
    : LolAdapter<D, VH>(itemClickListener, viewHolderCreators[0]) {

    override fun getItemViewType(position: Int): Int {
        val itemViewType = whatItemViewType.invoke(position)
        if (itemViewType < 0 || itemViewType >= viewHolderCreators.size) {
            throw RuntimeException("ItemType must be less or equal to number of viewHolderCreators ie 0,1,2")
        }
        return itemViewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        viewHolderCreator = viewHolderCreators[viewType]
        return super.onCreateViewHolder(parent, viewType)
    }
}