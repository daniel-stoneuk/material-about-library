package com.danielstone.materialaboutlibrary;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.danielstone.materialaboutlibrary.adapters.MaterialAboutListAdapter;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

public class NiceAboutActivity extends AppCompatActivity {

    // Intent Keys
    private static final String MAKE_APP_HEADER = "appHeader";
    private static final String MAKE_VERSION_ITEM = "versionItem";
    private static final String AUTHOR = "author";
    private static final String AUTHOR_SUBTEXT = "authorsubtext";
    private static final String FACEBOOK = "facebook";
    private static final String WEBSITE = "website";
    private static final String THEME_ID = "themeid";
    private static final String LICENSES_STRING = "licensestring";
    private static final String LICENSES_URL = "licensesurl";
    private static final String RATE = "ratethisapp";

    // Activity Specific Stuff
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MaterialAboutListAdapter adapter;

    MaterialAboutList list = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int themeResId = getIntent().getIntExtra(THEME_ID, -1);
        if (themeResId != -1){
            setTheme(themeResId);
        }

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
        } else {
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

        if(i.getStringExtra(LICENSES_URL) != null) {
            appCardBuilder.addItem(ConvenienceBuilder.createLicenseItem(this, i.getStringExtra(LICENSES_URL), true));
        } else if (i.getStringExtra(LICENSES_STRING) != null) {
            appCardBuilder.addItem(ConvenienceBuilder.createLicenseItem(this, i.getStringExtra(LICENSES_STRING), false));
        }

        if (i.getBooleanExtra(RATE, false)) {
            appCardBuilder.addItem(ConvenienceBuilder.createRateButtonItem(this));
        }

        MaterialAboutCard authorCard = null;
        if(i.getStringExtra(AUTHOR) != null){
            authorCard = ConvenienceBuilder.createAuthorCard(this,
                    i.getStringExtra(AUTHOR),
                    i.getStringExtra(AUTHOR_SUBTEXT),
                    Uri.parse(i.getStringExtra(WEBSITE)),
                    i.getStringExtra(FACEBOOK));
        }

        MaterialAboutList.Builder listBuilder = new MaterialAboutList.Builder()
                .addCard(appCardBuilder.build());

        if (authorCard != null) {
            listBuilder.addCard(authorCard);
        }
        return listBuilder.build();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

        public AboutActivityIntentBuilder makeAuthorCard(String author, String authorSubText, String facebookId, Uri websiteUrl) {
            aboutIntent.putExtra(AUTHOR, author);
            aboutIntent.putExtra(AUTHOR_SUBTEXT, authorSubText);
            aboutIntent.putExtra(FACEBOOK, facebookId);
            aboutIntent.putExtra(WEBSITE, websiteUrl.toString());
            return this;
        }

        public AboutActivityIntentBuilder setTheme(int themeResId){
            aboutIntent.putExtra(THEME_ID, themeResId);
            return this;
        }

        public AboutActivityIntentBuilder setLicensesString (String htmlString) {
            aboutIntent.putExtra(LICENSES_STRING, htmlString);
            return this;
        }

        /**
         * This method overrides setLicensesUrl()
         */
        public AboutActivityIntentBuilder setLicensesUrl (String url) {
            aboutIntent.putExtra(LICENSES_URL, url);
            return this;
        }

        public AboutActivityIntentBuilder makeRateButton (boolean rateButton) {
            aboutIntent.putExtra(RATE, rateButton);
            return this;
        }

        public void start(){
            ctx.startActivity(aboutIntent);
        }

    }
}
