package com.danielstone.materialaboutlibrary;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import com.danielstone.materialaboutlibrary.adapters.MaterialAboutListAdapter;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

public abstract class MaterialAboutActivity extends AppCompatActivity {

    private MaterialAboutList list = new MaterialAboutList.Builder().build();
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MaterialAboutListAdapter adapter;

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
        appBarLayout = (AppBarLayout) findViewById(R.id.mal_appbarlayout);
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

        Resources.Theme theme = getTheme();
        TypedArray lightTheme = theme.obtainStyledAttributes(new int[]{R.attr.mal_lightActionBar});

        TypedValue popupOverlay = new TypedValue();
        theme.resolveAttribute(R.attr.mal_popupOverlay, popupOverlay, true);
        toolbar.setPopupTheme(popupOverlay.data);

        if (lightTheme.getBoolean(0, true)) {
            toolbar.setTitleTextColor(Color.BLACK);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        } else {
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        }

        adapter = new MaterialAboutListAdapter(list, getViewTypeManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    protected ViewTypeManager getViewTypeManager() {
        return new DefaultViewTypeManager();
    }

    protected MaterialAboutList getMaterialAboutList() {
        return list;
    }

    protected void setMaterialAboutList(MaterialAboutList materialAboutList) {
        list = materialAboutList;
        adapter.swapData(materialAboutList);
    }

    protected void setScrollToolbar(boolean scrollToolbar) {
        if (toolbar != null) {
            AppBarLayout.LayoutParams params =
                    (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
            if (scrollToolbar) {
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
            } else {
                params.setScrollFlags(0);
            }
        }
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
            recyclerView.animate().alpha(1f).translationY(0f).setDuration(400).setInterpolator(new FastOutSlowInInterpolator()).start();
            super.onPostExecute(s);
            context = null;
        }
    }
}
