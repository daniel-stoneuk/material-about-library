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

import java.net.URL;

public class NiceAboutActivity extends AppCompatActivity {

    // Intent Keys
    private static final String MAKE_APP_CARD = "makeappcard";
    private static final String MAKE_AUTHOR_CARD = "makeauthorcard";
    private static final String MAKE_CONTACT_CARD = "makecontactcard";
    private static final String MAKE_APP_HEADER = "appHeader";
    private static final String MAKE_VERSION_ITEM = "versionItem";
    private static final String AUTHOR = "author";
    private static final String AUTHOR_SUBTEXT = "authorsubtext";
    private static final String FACEBOOK = "facebook";
    private static final String WEBSITE = "website";
    private static final String THEME_ID = "themeid";
    private static final String LICENSES_STRING = "licensestring";
    private static final String LICENSES_URL = "licensesurl";
    private static final String DATAPRIVACY_STRING = "dataprivacystring";
    private static final String DATAPRIVACY_URL = "dataprivacyurl";
    private static final String RATE = "ratethisapp";
    private static final String CONTACT_EMAIL = "contactemail";
    private static final String CONTACT_PHONENUMBER = "contactphonenumber";
    private static final String CONTACT_WEBSITE = "contact_website";

    // Activity Specific Stuff
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MaterialAboutListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int themeResId = getIntent().getIntExtra(THEME_ID, -1);
        if (themeResId != -1) {
            setTheme(themeResId);
        }

        setContentView(R.layout.mal_material_about_activity);
        setTitle(R.string.mal_title_about);

        assignViews();
        initViews();

        adapter.swapData(getMaterialAboutList(getIntent()));
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

    private MaterialAboutList getMaterialAboutList(Intent i) {
        MaterialAboutList.Builder listBuilder = new MaterialAboutList.Builder();

        if(i.getBooleanExtra(MAKE_APP_CARD, false)){
            listBuilder.addCard(getAppCard(i));
        }
        if(i.getBooleanExtra(MAKE_AUTHOR_CARD, false)){
            listBuilder.addCard(getAuthorCard(i));
        }
        if(i.getBooleanExtra(MAKE_CONTACT_CARD, false)){
            listBuilder.addCard(getContactCard(i));
        }

        return listBuilder.build();
    }

    private MaterialAboutCard getContactCard(Intent i) {
        MaterialAboutCard.Builder contactBuilder = new MaterialAboutCard.Builder().title(R.string.mal_contact_title);
        if (i.getStringExtra(CONTACT_WEBSITE) != null) {
            contactBuilder.addItem(ConvenienceBuilder.createWebsiteItem(this, Uri.parse(i.getStringExtra(CONTACT_WEBSITE))));
        }
        if (i.getStringExtra(CONTACT_EMAIL) != null) {
            contactBuilder.addItem(ConvenienceBuilder.createEmailItem(this, i.getStringExtra(CONTACT_EMAIL)));
        }
        if (i.getStringExtra(CONTACT_PHONENUMBER) != null) {
            contactBuilder.addItem(ConvenienceBuilder.createPhoneItem(this, i.getStringExtra(CONTACT_PHONENUMBER)));
        }

        return contactBuilder.build();
    }

    private MaterialAboutCard getAppCard(Intent i) {
        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();
        if (i.getBooleanExtra(MAKE_APP_HEADER, true)) {
            appCardBuilder.addItem(ConvenienceBuilder.createAppTitle(this));
        }
        if (i.getBooleanExtra(MAKE_VERSION_ITEM, true)) {
            try {
                appCardBuilder.addItem(ConvenienceBuilder.createVersionItem(this));
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(this.getClass().getSimpleName(), e.getLocalizedMessage());
            }
        }

        if (i.getStringExtra(LICENSES_URL) != null) {
            appCardBuilder.addItem(ConvenienceBuilder.createLicenseItem(this, i.getStringExtra(LICENSES_URL), true));
        } else if (i.getStringExtra(LICENSES_STRING) != null) {
            appCardBuilder.addItem(ConvenienceBuilder.createLicenseItem(this, i.getStringExtra(LICENSES_STRING), false));
        }

        if (i.getStringExtra(DATAPRIVACY_URL) != null) {
            appCardBuilder.addItem(ConvenienceBuilder.createDataprivacyItem(this, i.getStringExtra(DATAPRIVACY_URL), true));
        } else if (i.getStringExtra(DATAPRIVACY_STRING) != null) {
            appCardBuilder.addItem(ConvenienceBuilder.createDataprivacyItem(this, i.getStringExtra(DATAPRIVACY_STRING), false));
        }

        if (i.getBooleanExtra(RATE, true)) {
            appCardBuilder.addItem(ConvenienceBuilder.createRateButtonItem(this));
        }
        return appCardBuilder.build();
    }

    private MaterialAboutCard getAuthorCard(Intent i) {
        return ConvenienceBuilder.createAuthorCard(this,
                i.getStringExtra(AUTHOR),
                i.getStringExtra(AUTHOR_SUBTEXT),
                Uri.parse(i.getStringExtra(WEBSITE)),
                i.getStringExtra(FACEBOOK));
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


        public AboutActivityIntentBuilder(Context ctx) {
            this.ctx = ctx;
            aboutIntent = new Intent(ctx, NiceAboutActivity.class);
        }

        public AboutActivityIntentBuilder makeAppCard(boolean makeAppCard) {
            aboutIntent.putExtra(MAKE_APP_CARD, makeAppCard);
            return this;
        }

        private AboutActivityIntentBuilder makeAuthorCard(boolean makeAuthorCard){
            aboutIntent.putExtra(MAKE_AUTHOR_CARD, makeAuthorCard);
            return this;
        }

        private AboutActivityIntentBuilder makeContactCard(boolean makeContactCard){
            aboutIntent.putExtra(MAKE_CONTACT_CARD, makeContactCard);
            return this;
        }

        public AboutActivityIntentBuilder makeAppHeader(boolean makeAppHeader) {
            aboutIntent.putExtra(MAKE_APP_HEADER, makeAppHeader);
            return makeAppCard(true);
        }

        public AboutActivityIntentBuilder makeVersionItem(boolean makeVersionItem) {
            aboutIntent.putExtra(MAKE_VERSION_ITEM, makeVersionItem);
            return makeAppCard(true);
        }

        public AboutActivityIntentBuilder makeAuthorCard(String author, String authorSubText, String facebookId, Uri websiteUrl) {
            aboutIntent.putExtra(AUTHOR, author);
            aboutIntent.putExtra(AUTHOR_SUBTEXT, authorSubText);
            aboutIntent.putExtra(FACEBOOK, facebookId);
            aboutIntent.putExtra(WEBSITE, websiteUrl.toString());
            return makeAuthorCard(true);
        }

        public AboutActivityIntentBuilder setTheme(int themeResId) {
            aboutIntent.putExtra(THEME_ID, themeResId);
            return this;
        }

        public AboutActivityIntentBuilder setLicensesString(String htmlString) {
            aboutIntent.putExtra(LICENSES_STRING, htmlString);
            return makeAppCard(true);
        }

        /**
         * This method overrides setLicensesString()
         */
        public AboutActivityIntentBuilder setLicensesUrl(String url) {
            aboutIntent.putExtra(LICENSES_URL, url);
            return makeAppCard(true);
        }

        public AboutActivityIntentBuilder setDataprivacyString(String htmlString) {
            aboutIntent.putExtra(DATAPRIVACY_STRING, htmlString);
            return makeAppCard(true);
        }

        /**
         * This method overrides setDataprivacyString()
         */
        public AboutActivityIntentBuilder setDataprivacyUrl(String url) {
            aboutIntent.putExtra(DATAPRIVACY_URL, url);
            return makeAppCard(true);
        }

        public AboutActivityIntentBuilder makeRateButton(boolean rateButton) {
            aboutIntent.putExtra(RATE, rateButton);
            return makeAppCard(true);
        }

        public AboutActivityIntentBuilder makeContactCard(String phoneNumber, URL website, String email) {
            aboutIntent.putExtra(CONTACT_PHONENUMBER, phoneNumber);
            aboutIntent.putExtra(CONTACT_WEBSITE, website == null ? null : website.toString());
            aboutIntent.putExtra(CONTACT_EMAIL, email);
            return makeContactCard(true);
        }

        public void start() {
            ctx.startActivity(aboutIntent);
        }

    }
}
