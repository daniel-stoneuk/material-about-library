package com.danielstone.materialaboutlibrary;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.danielstone.materialaboutlibrary.adapters.MaterialAboutListAdapter;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

public class NiceAboutActivity extends AppCompatActivity {

    // Intent Keys
    private static final String MAKE_APP_HEADER = "appHeader";
    private static final String MAKE_VERSION_ITEM = "versionItem";

    // Activity Specific Stuff
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MaterialAboutListAdapter adapter;

    MaterialAboutList list = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mal_material_about_activity);
        setTitle(R.string.mal_title_about);

        assignViews();
        initViews();

        list = getMaterialAboutList(getIntent());
        adapter.swapData(list);
    }

    private void assignViews() {
        toolbar = (Toolbar) findViewById(R.id.mal_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.mal_recyclerview);
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

    private MaterialAboutList getMaterialAboutList(Intent i){
        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();
        if (i.getBooleanExtra(MAKE_APP_HEADER, false)){
            appCardBuilder.addItem(ConvenienceBuilder.createAppTitle(this));
        }
        if (i.getBooleanExtra(MAKE_VERSION_ITEM, false)){
            try {
                appCardBuilder.addItem(ConvenienceBuilder.createVersionItem(this));
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(this.getClass().getSimpleName(), e.getLocalizedMessage());
            }
        }

        return new MaterialAboutList.Builder()
                .addCard(appCardBuilder.build())
                .build();
    }

    public static class AboutActivityIntentBuilder {
        private Intent aboutIntent;
        private Context ctx;


        public AboutActivityIntentBuilder(Context ctx){
            this.ctx = ctx;
            aboutIntent = new Intent(ctx, NiceAboutActivity.class);
        }

        public AboutActivityIntentBuilder makeAppHeader(boolean makeAppHeader) {
            aboutIntent.putExtra(MAKE_APP_HEADER, makeAppHeader);
            return this;
        }

        public AboutActivityIntentBuilder makeVersionItem (boolean makeVersionItem) {
            aboutIntent.putExtra(MAKE_VERSION_ITEM, makeVersionItem);
            return this;
        }

        public void start(){
            ctx.startActivity(aboutIntent);
        }

    }
}
