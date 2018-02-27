package com.qamar4p.lol_adapter;

import android.view.View;

public interface ItemViewClickListener<D> {
    void onViewClicked(View v, D item, int position);
}