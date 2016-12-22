package com.danielstone.materialaboutlibrary;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.danielstone.materialaboutlibrary.adapters.MaterialAboutListAdapter;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

public abstract class MaterialAboutActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private MaterialAboutListAdapter mAdapter;

    MaterialAboutList mList = null;

    protected abstract MaterialAboutList getMaterialAboutList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mal_material_about_activity);

        if (TextUtils.isEmpty(getTitle()))
            setTitle(R.string.mal_title_about);

        assignViews();
        initViews();

        mList = getMaterialAboutList();
        mAdapter.swapData(mList);
    }

    private void assignViews() {
        mToolbar = (Toolbar) findViewById(R.id.mal_toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.mal_recyclerview);
    }

    private void initViews() {
        setSupportActionBar(mToolbar);
        if (NavUtils.getParentActivityName(this) != null) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        mAdapter = new MaterialAboutListAdapter(new MaterialAboutList.Builder().build());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
