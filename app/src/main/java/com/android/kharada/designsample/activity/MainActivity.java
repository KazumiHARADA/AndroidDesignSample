package com.android.kharada.designsample.activity;

import android.content.Intent;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.kharada.designsample.R;
import com.android.kharada.designsample.adapter.MainAdapter;
import com.android.kharada.designsample.gson.MainListModel;
import com.android.kharada.designsample.util.AssetUtil;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String jsonString = AssetUtil.assetLoadString(getApplicationContext(), "main_list.json");

        Gson gson = new Gson();
        MainListModel list = gson.fromJson(jsonString, MainListModel.class);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        MainAdapter adapter = new MainAdapter(this, list.getList());
        adapter.setOnItemClickListener((view, position, item) -> {
            try {
                Class activityClass = Class.forName(item.getActivityName());
                Intent intent = new Intent(this, activityClass);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("description", item.getDescription());
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                return;
            }

        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        return true;
    }
}
