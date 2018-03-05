package com.qamar4p.loladapterexample;

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
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
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
                case R.id.textTitle:
                    break;
                default:
                    break;
            }
        };
    }

    LolAdapter<ItemView, ItemModel> adapter = new LolAdapter<>(itemViewClickListener(),ItemView::new);
    LolAdapter<LolViewHold<ItemModel>, ItemModel>
            typeAdapter = new LolTypeAdapter<>(itemViewClickListener()
            ,position -> position == 0 || position == 4 ? 0 : 1,
            ItemHeaderView::new,
            ItemView::new);

    class ItemView extends LolViewHold<ItemModel> {
        @BindView(R.id.textTitle)
        TextView textTitle;
        @BindView(R.id.textDesc)
        TextView textDesc;

        ItemView(ViewGroup parent) {
            super(parent,R.layout.list_item);
        }
        @Override
        public void bind() {
            textTitle.setText(data.fullName+" "+getAdapterPosition());
            textDesc.setText(data.location);
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
