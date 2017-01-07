package com.danielstone.materialaboutlibrary;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.danielstone.materialaboutlibrary.adapters.MaterialAboutListAdapter;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

public abstract class MaterialAboutActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MaterialAboutListAdapter adapter;

    MaterialAboutList list = null;

    protected abstract MaterialAboutList getMaterialAboutList(Context c);

    protected abstract CharSequence getActivityTitle();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mal_material_about_activity);

        CharSequence title = getActivityTitle();
        if (title == null)
            setTitle(R.string.mal_title_about);
        else
            setTitle(title);


        assignViews();
        initViews();

        ListTask task = new ListTask(this);
        task.execute();

    }

    private void assignViews() {
        toolbar = (Toolbar) findViewById(R.id.mal_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.mal_recyclerview);
        recyclerView.setAlpha(0f);
        recyclerView.setTranslationY(20);
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        if (NavUtils.getParentActivityName(this) != null) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        adapter = new MaterialAboutListAdapter(new MaterialAboutList.Builder().build());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private class ListTask extends AsyncTask<String, String, String> {

        Context context;

        public ListTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            list = getMaterialAboutList(context);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            adapter.swapData(list);
            recyclerView.animate().alpha(1f).translationY(0f).setDuration(200).start();
            super.onPostExecute(s);
            context = null;
        }
    }
}
