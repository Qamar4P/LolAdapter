package com.qamar4p.loladapterexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qamar4p.lol_adapter.ItemViewClickListener;
import com.qamar4p.lol_adapter.LolAdapter;
import com.qamar4p.lol_adapter.LolTypeAdapter;
import com.qamar4p.lol_adapter.LolViewHold;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Qamar4P
 */
public class ScrollingActivity extends BaseActivity{

    public static final String AUTHOR_CELL = "+923088006866";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        setSupportActionBar(toolbar);

        setupUi();
        loadData();
    }

    @OnClick(R.id.fab)
    public void tapOnFab(View view){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:"+ AUTHOR_CELL)));
    }

    private void setupUi() {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_single_view_type) {
            recyclerView.setAdapter(adapter);
            return true;
        }
        if (id == R.id.action_multi_view_type) {
            recyclerView.setAdapter(typeAdapter);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        ArrayList<ItemModel> data = new ArrayList<>();
        data.add(new ItemModel());
        data.add(new ItemModel());
        data.add(new ItemModel());
        data.add(new ItemModel());
        data.add(new ItemModel());
        data.add(new ItemModel());
        data.add(new ItemModel());
        data.add(new ItemModel());
        data.add(new ItemModel());

        adapter.items.addAll(data);
        typeAdapter.items.addAll(data);
    }

    @NonNull
    private ItemViewClickListener<ItemModel> itemViewClickListener() {
        return (v, item, position) -> {
            switch (v.getId()) {
                case R.id.buttonCall:
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ AUTHOR_CELL)));
                    break;
                default:
                    break;
            }
        };
    }

    LolAdapter<ItemModel,ItemViewHold> adapter = new LolAdapter<>(itemViewClickListener(), ItemViewHold::new);

    /**
     * recycle view adapter for multiple view types
     */
    LolAdapter<ItemModel,LolViewHold<ItemModel>>
            typeAdapter = new LolTypeAdapter<>(itemViewClickListener()
            ,position -> position == 0 || position == 4 ? 0 : 1,
            ItemHeaderView::new,
            ItemViewHold::new);

    class ItemViewHold extends LolViewHold<ItemModel> {
        @BindView(R.id.textTitle)
        TextView textTitle;
        @BindView(R.id.textDesc)
        TextView textDesc;

        ItemViewHold(ViewGroup parent) {
            super(parent,R.layout.list_item);
        }
        @Override
        public void bind() {
            textTitle.setText(data.fullName+" "+getAdapterPosition());
            textDesc.setText(data.location);
        }

        @OnClick({R.id.buttonCall})
        public void tapOnView(View v){
            viewClickListener.onViewClicked(v,data,getAdapterPosition());
        }
    }

    class ItemHeaderView extends LolViewHold<ItemModel> {
        @BindView(R.id.textTitle)
        TextView textTitle;

        ItemHeaderView(ViewGroup parent) {
            super(parent,R.layout.list_item2);
        }

        @Override
        public void bind() {
            textTitle.setText(data.fullName+" "+getAdapterPosition());
        }
    }
}
