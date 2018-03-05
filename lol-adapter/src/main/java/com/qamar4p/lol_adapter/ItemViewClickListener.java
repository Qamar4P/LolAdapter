package com.qamar4p.lol_adapter;

import android.view.View;

/**
 * @author Qamar4P
 */
public interface ItemViewClickListener<D> {
    void onViewClicked(View v, D item, int position);
}