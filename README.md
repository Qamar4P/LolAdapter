# LolAdapterExample
Demo project to showcase my stupid simple LolAdapter for recycler view also avail in Kotlin, check out branch

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
LolAdapter<ItemModel,ItemViewHold> adapter = new LolAdapter<>(itemViewClickListener(),ItemViewHold::new);
adapter.items.addAll(data);
recyclerView.setAdapter(typeAdapter);
...

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
    }

```

That's it

#### Type List Adapter
##### Code
```
...
LolAdapter<ItemModel, LolViewHold<ItemModel>>
            typeAdapter = new LolTypeAdapter<>(itemViewClickListener()
            ,position -> position == 0 || position == 4 ? 0 : 1,
            HeaderViewHold::new,
            ItemViewHold::new);
adapter.items.addAll(data);
recyclerView.setAdapter(typeAdapter);
...

    class HeaderViewHold extends LolViewHold<ItemModel> {
        @BindView(R.id.textTitle)
        TextView textTitle;

        HeaderViewHold(ViewGroup parent) {
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