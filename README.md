# LolAdapterExample
Demo project to showcase my stupid simple LolAdapter for recycler view

This library use Java8 Lambdas and depends ButterKnife optional

### How to use LolAdapter
##### XML
```
<android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:clipToPadding="false"
        android:paddingTop="@dimen/standard_wall_space"
        android:paddingBottom="@dimen/standard_wall_space"
        android:paddingEnd="@dimen/standard_wall_space"
        android:paddingStart="@dimen/standard_wall_space"
        app:layoutManager="LinearLayoutManager"
        tools:listitem="@layout/list_item" />
```
#### Simple List Adapter
##### Code
```
...
LolAdapter<ItemView, ItemModel> adapter = new LolAdapter<>(itemViewClickListener(),ItemView::new);
adapter.items.addAll(data);
recyclerView.setAdapter(typeAdapter);
...

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

```

That's it

#### Type List Adapter
##### Code
```
...
LolAdapter<LolViewHold<ItemModel>, ItemModel>
            typeAdapter = new LolTypeAdapter<>(itemViewClickListener()
            ,position -> position == 0 || position == 4 ? 0 : 1,
            ItemHeaderView::new,
            ItemView::new);
adapter.items.addAll(data);
recyclerView.setAdapter(typeAdapter);
...

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

```

That's it


![Scrennshot](https://github.com/Qamar4P/LolAdapterExample/blob/master/screens/demo.png)