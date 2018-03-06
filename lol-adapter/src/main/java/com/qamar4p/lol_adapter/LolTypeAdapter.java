package com.qamar4p.lol_adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qamar4P on 3/5/2018.
 *
 * @author qamar.dev@gmail.com
 */
public class LolTypeAdapter<D,VH extends LolViewHold<D>> extends LolAdapter<D,VH>{

    private final ViewHolderCreator<VH> viewHolderCreators[];
    private final DefineItemViewType whatItemViewType;

    public LolTypeAdapter(ItemViewClickListener<D> itemClickListener, @NonNull DefineItemViewType whatItemType,@NonNull ViewHolderCreator<VH>... viewHolderCreators){
        super(itemClickListener, viewHolderCreators[0]);
        this.whatItemViewType = whatItemType;
        this.viewHolderCreators = viewHolderCreators;
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = whatItemViewType.getItemViewType(position);
        if(itemViewType < 0 || itemViewType >= viewHolderCreators.length) {
            throw new RuntimeException("ItemType must be less or equal to number of viewHolderCreators ie 0,1,2");
        }
        return itemViewType;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolderCreator = viewHolderCreators[viewType];
        return super.onCreateViewHolder(parent,viewType);
    }
}