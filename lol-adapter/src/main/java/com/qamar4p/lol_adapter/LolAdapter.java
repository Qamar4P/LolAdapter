package com.qamar4p.lol_adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class LolAdapter<VH extends LolAdapter.ViewHold,D> extends RecyclerView.Adapter<VH>{

    private final ItemViewClickListener<D> viewClickListener;
    private final ViewHolderCreator<VH> viewHolderCreator;
    public List<D> items = new ArrayList<>();

    public LolAdapter(ViewHolderCreator<VH> viewHolderCreator,ItemViewClickListener<D> itemViewClickListener){
        this.viewHolderCreator = viewHolderCreator;
        this.viewClickListener = itemViewClickListener;
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

    public static abstract class ViewHold<T> extends RecyclerView.ViewHolder{
        public ItemViewClickListener<T> viewClickListener;
        public T data;
        public ViewHold(ViewGroup parent,@LayoutRes int viewResId) {
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
}