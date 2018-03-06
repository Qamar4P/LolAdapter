package com.qamar4p.lol_adapter;

import android.view.ViewGroup;

/**
 * @author Qamar4P
 */
public interface DefineItemViewType/*<VH extends LolViewHold>*/{
    int getItemViewType(int position);
//    VH create(ViewGroup parent,int type);
}