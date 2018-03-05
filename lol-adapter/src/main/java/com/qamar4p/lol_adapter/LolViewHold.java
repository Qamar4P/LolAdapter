package com.qamar4p.lol_adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Qamar4P on 3/5/2018.
 *
 * @author qamar.dev@gmail.com
 */
public abstract class LolViewHold<T> extends RecyclerView.ViewHolder{
    public ItemViewClickListener<T> viewClickListener;
    public T data;
    public LolViewHold(ViewGroup parent, @LayoutRes int viewResId) {
        super(LayoutInflater.from(parent.getContext()).inflate(viewResId, parent, false));
        itemView.setOnClickListener(v -> {
            viewClicked(v);
            if(viewClickListener != null) {
                viewClickListener.onViewClicked(v, data, getAdapterPosition());
            }
        });
        ButterKnife.bind(this,itemView);
    }

    protected void viewClicked(View v){}

    void bind(T data) {
        this.data = data;
        bind();
    }
    public abstract void bind();
}