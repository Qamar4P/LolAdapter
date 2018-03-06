package com.qamar4p.lol_adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Qamar4P on 3/5/2018.
 *
 * @author qamar.dev@gmail.com
 */
public class LolAdapter<D,VH extends LolViewHold<D>> extends RecyclerView.Adapter<VH>{

    public List<D> items = new ArrayList<>();

    ViewHolderCreator<VH> viewHolderCreator;
    private final ItemViewClickListener<D> viewClickListener;

    public LolAdapter(@Nullable ItemViewClickListener<D> itemViewClickListener, @NonNull ViewHolderCreator<VH> viewHolderCreator){
        this.viewClickListener = itemViewClickListener;
        this.viewHolderCreator = viewHolderCreator;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH vh = viewHolderCreator.create(parent);
        vh.viewClickListener = viewClickListener;
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}