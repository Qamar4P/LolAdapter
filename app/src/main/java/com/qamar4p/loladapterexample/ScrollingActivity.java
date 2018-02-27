package com.qamar4p.loladapterexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qamar4p.lol_adapter.ItemViewClickListener;
import com.qamar4p.lol_adapter.LolAdapter;
import com.qamar4p.lol_adapter.LolTypeAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class ScrollingActivity extends AppCompatActivity {


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

    private void setupUi() {
        recyclerView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        ArrayList<ItemModel> data = new ArrayList<>();
        data.add(new ItemModel());
        data.add(new ItemModel());
        data.add(new ItemModel());

        adapter.items.addAll(data);
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

    final LolAdapter<LolAdapter.ViewHold, ItemModel> adapter = new LolTypeAdapter<LolAdapter.ViewHold, ItemModel>(position -> position > 3 ? 0 : 1,itemViewClickListener(),VH::new,VH2::new);

    class VH2 extends LolAdapter.ViewHold<ItemModel>{
        @BindView(R.id.textTitle)
        TextView textTitle;
        @BindView(R.id.textDesc)
        TextView textDesc;

        VH2(ViewGroup parent) {
            super(parent,R.layout.list_item);
        }
        @Override
        public void bind() {
            textTitle.setText(data.fullName+" "+getAdapterPosition());
            textDesc.setText(data.location);
        }

    }

    class VH extends LolAdapter.ViewHold<ItemModel>{
        @BindView(R.id.textTitle)
        TextView textTitle;
        @BindView(R.id.textDesc)
        TextView textDesc;

        VH(ViewGroup parent) {
            super(parent,R.layout.list_item);
        }
        @Override
        public void bind() {
            textTitle.setText(data.fullName+" "+getAdapterPosition());
            textDesc.setText(data.location);
        }

    }
}
