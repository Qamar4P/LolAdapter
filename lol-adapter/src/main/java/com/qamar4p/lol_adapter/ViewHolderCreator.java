package com.qamar4p.lol_adapter;

import android.view.ViewGroup;

public interface ViewHolderCreator<VH>{
        VH create(ViewGroup parent);
    }