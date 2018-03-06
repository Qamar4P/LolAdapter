package com.qamar4p.lol_adapter

import android.view.View

/**
 * @author Qamar4P
 */
interface ItemViewClickListener<D> {
    fun onViewClicked(v: View, item: D?, position: Int)
}