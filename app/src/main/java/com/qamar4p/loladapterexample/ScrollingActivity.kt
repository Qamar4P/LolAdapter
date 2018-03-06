package com.qamar4p.loladapterexample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList

import com.qamar4p.lol_adapter.ItemViewClickListener
import com.qamar4p.lol_adapter.LolAdapter
import com.qamar4p.lol_adapter.LolTypeAdapter
import com.qamar4p.lol_adapter.LolViewHold

import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * @author Qamar4P
 */
class ScrollingActivity : BaseActivity() {

    private var adapter = LolAdapter<ItemModel, ItemViewHold>(itemViewClickListener(),{ItemViewHold(it)})

    /**
     * recycle view adapter for multiple view types
     */
    private var typeAdapter: LolAdapter<ItemModel, LolViewHold<ItemModel>>
            = LolTypeAdapter(itemViewClickListener(), {
        if (it == 0 || it == 4) 0 else 1
    },{ItemHeaderView(it) },
      {ItemViewHold(it)})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)

        loadData()
        setupUi()
    }

    private fun setupUi() {
        recyclerView!!.adapter = adapter
        fab.setOnClickListener({
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("smsto:" + AUTHOR_CELL)))
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        if (id == R.id.action_single_view_type) {
            recyclerView!!.adapter = adapter
            return true
        }
        if (id == R.id.action_multi_view_type) {
            recyclerView!!.adapter = typeAdapter
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadData() {
        val data = arrayListOf<ItemModel>()
        data.add(ItemModel())
        data.add(ItemModel())
        data.add(ItemModel())
        data.add(ItemModel())
        data.add(ItemModel())
        data.add(ItemModel())
        data.add(ItemModel())
        data.add(ItemModel())
        data.add(ItemModel())

        adapter.items += data
        typeAdapter.items += data
    }

    private fun itemViewClickListener(): ItemViewClickListener<ItemModel> {
        return object:ItemViewClickListener<ItemModel>{
            override fun onViewClicked(v: View, item: ItemModel?, position: Int) {
                when (v.getId()) {
                    R.id.buttonCall -> startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + AUTHOR_CELL)))
                    else -> {
                    }
                }
            }
        }
    }

    companion object {
        val AUTHOR_CELL = "+923088006866"
    }
}

class ItemViewHold(parent: ViewGroup) : LolViewHold<ItemModel>(parent, R.layout.list_item) {

    init {
        itemView.buttonCall.setOnClickListener { viewClickListener?.onViewClicked(it, data, adapterPosition) }
    }

    override fun bind() {
        itemView.textTitle!!.text = data?.fullName + " " + adapterPosition
        itemView.textDesc!!.text = data?.location
    }
}

class ItemHeaderView(parent: ViewGroup) : LolViewHold<ItemModel>(parent, R.layout.list_item2) {
    override fun bind() {
        itemView.textTitle!!.text = data?.fullName + " " + adapterPosition
    }
}
