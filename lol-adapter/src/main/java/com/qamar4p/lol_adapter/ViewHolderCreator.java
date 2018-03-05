package com.qamar4p.lol_adapter;

import android.view.ViewGroup;

/**
 * @author Qamar4P
 */
public interface ViewHolderCreator<VH>{
        VH create(ViewGroup parent);
    }