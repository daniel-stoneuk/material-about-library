package com.danielstone.materialaboutlibrarydemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;
import com.danielstone.materialaboutlibrarydemo.custom.MyCustomItem;
import com.danielstone.materialaboutlibrarydemo.custom.MyViewTypeManager;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import net.yslibrary.licenseadapter.Library;
import net.yslibrary.licenseadapter.LicenseAdapter;
import net.yslibrary.licenseadapter.Licenses;

import java.util.ArrayList;
import java.util.List;

public class ExampleMaterialAboutActivity extends MaterialAboutActivity {

    public static final String THEME_EXTRA = "";
    public static final int THEME_LIGHT = 0;
    public static final int THEME_DARK = 1;
    public static final int THEME_DAYNIGHT = 2;
    public static final int THEME_CUSTOM_CARDVIEW = 3;

    @NonNull
    @Override
    protected MaterialAboutList getMaterialAboutList(@NonNull final Context c) {
        MaterialAboutCard.Builder advancedCardBuilder = new MaterialAboutCard.Builder();
        advancedCardBuilder.title("Advanced");

        advancedCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text("TitleItem OnClickAction")
                .icon(R.mipmap.ic_launcher)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("http://www.danstone.uk")))
                .build());

        advancedCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Snackbar demo")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_code_tags)
                        .sizeDp(18))
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        Snackbar.make(((ExampleMaterialAboutActivity) c).findViewById(R.id.mal_material_about_activity_coordinator_layout), "Test", Snackbar.LENGTH_SHORT).show();
                    }
                })
                .build());

        advancedCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("OnLongClickAction demo")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_hand_pointing_right)
                        .sizeDp(18))
                .setOnLongClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        Toast.makeText(c, "Long pressed", Toast.LENGTH_SHORT).show();
                    }
                })
                .build());

        advancedCardBuilder.addItem(new MyCustomItem.Builder()
                .text("Custom Item")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_code_braces)
                        .sizeDp(18))
                .build());

        advancedCardBuilder.addItem(createDynamicItem("Tap for a random number & swap position", c));

        MaterialAboutCard.Builder customAdapterCardBuilder = new MaterialAboutCard.Builder();
        // Create list of libraries
        List<Library> libraries = new ArrayList<>();

        // Add libraries that are hosted on GitHub with an Apache v2 license.
        libraries.add(Licenses.fromGitHubApacheV2("yshrsmz/LicenseAdapter"));
        libraries.add(Licenses.fromGitHubApacheV2("daniel-stoneuk/material-about-library"));

        customAdapterCardBuilder.title("Custom Adapter (License Adapter)");
        customAdapterCardBuilder.customAdapter(new LicenseAdapter(libraries));

        return Demo.createMaterialAboutList(c, getIntent().getIntExtra(THEME_EXTRA, THEME_LIGHT)).addCard(advancedCardBuilder.build()).addCard(customAdapterCardBuilder.build());
    }

    private MaterialAboutActionItem createDynamicItem(String subText, final Context c) {
        final MaterialAboutActionItem item = new MaterialAboutActionItem.Builder()
                .text("Dynamic UI")
                .subText(subText)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_refresh)
                        .sizeDp(18))
                .build();
        item.setOnClickAction(new MaterialAboutItemOnClickAction() {
            @Override
            public void onClick() {
                getList().getCards().get(4).getItems().remove(getList().getCards().get(4).getItems().indexOf(item));
                int newIndex = ((int) (Math.random() * 5));
                getList().getCards().get(4).getItems().add(newIndex, item);
                item.setSubText("Random number: " + ((int) (Math.random() * 10)));
                setMaterialAboutList(getList());
            }
        });

        return item;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        switch (getIntent().getIntExtra(THEME_EXTRA, THEME_LIGHT)) {
            case THEME_LIGHT:
                setTheme(R.style.AppTheme_MaterialAboutActivity_Light);
                break;
            case THEME_DARK:
                setTheme(R.style.AppTheme_MaterialAboutActivity_Dark);
                break;
            case THEME_DAYNIGHT:
                setTheme(R.style.AppTheme_MaterialAboutActivity_DayNight);
                break;
            case THEME_CUSTOM_CARDVIEW:
                setTheme(R.style.AppTheme_MaterialAboutActivity_Light_DarkActionBar_CustomCardView);
                break;
        }

        super.onCreate(savedInstanceState);

//        Call this method to let the scrollbar scroll off screen
//        setScrollToolbar(true);
    }

    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.mal_title_about);
    }

    @NonNull
    @Override
    protected ViewTypeManager getViewTypeManager() {
        return new MyViewTypeManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_example_material_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            refreshMaterialAboutList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
