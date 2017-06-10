package com.danielstone.materialaboutlibrarydemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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

public class ExampleMaterialAboutActivity extends MaterialAboutActivity {

    public static final String THEME_EXTRA = "";
    public static final int THEME_LIGHT_LIGHTBAR = 0;
    public static final int THEME_LIGHT_DARKBAR = 1;
    public static final int THEME_DARK_LIGHTBAR = 2;
    public static final int THEME_DARK_DARKBAR = 3;

    protected int colorIcon = R.color.mal_color_icon_light_theme;

    @NonNull @Override
    protected MaterialAboutList getMaterialAboutList(@NonNull final Context c) {
        MaterialAboutCard.Builder advancedCardBuilder = new MaterialAboutCard.Builder();
        advancedCardBuilder.title("Advanced");

        advancedCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text("TitleItem OnClickAction")
                .icon(R.mipmap.ic_launcher)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("http://www.daniel-stone.uk")))
                .build());

        advancedCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Snackbar demo")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_code_tags)
                        .color(ContextCompat.getColor(c, colorIcon))
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
                        .color(ContextCompat.getColor(c, colorIcon))
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
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18))
                .build());

        final MaterialAboutActionItem dynamicItem = new MaterialAboutActionItem.Builder()
                .text("Dynamic UI")
                .subText("Tap for a random number.")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_refresh)
                        .color(ContextCompat.getColor(c, colorIcon)
                        ).sizeDp(18))
                .build();
        dynamicItem.setOnClickAction(new MaterialAboutItemOnClickAction() {
            @Override
            public void onClick() {
                dynamicItem.setSubText("Random number: " + ((int) (Math.random() * 10)));
                refreshMaterialAboutList();
            }
        });
        advancedCardBuilder.addItem(dynamicItem);

        return Demo.createMaterialAboutList(c, colorIcon, getIntent().getIntExtra(THEME_EXTRA, THEME_LIGHT_DARKBAR)).addCard(advancedCardBuilder.build());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("test", "onCreate: " + getIntent().getIntExtra(THEME_EXTRA, THEME_LIGHT_DARKBAR));
        switch (getIntent().getIntExtra(THEME_EXTRA, THEME_LIGHT_DARKBAR)) {
            case THEME_LIGHT_LIGHTBAR:
                setTheme(R.style.AppTheme_MaterialAboutActivity_Light_LightActionBar);
                colorIcon = R.color.mal_color_icon_light_theme;
                break;
            case THEME_DARK_LIGHTBAR:
                setTheme(R.style.AppTheme_MaterialAboutActivity_Dark_LightActionBar);
                colorIcon = R.color.mal_color_icon_dark_theme;
                break;
            case THEME_LIGHT_DARKBAR:
                setTheme(R.style.AppTheme_MaterialAboutActivity_Light_DarkActionBar);
                colorIcon = R.color.mal_color_icon_light_theme;
                break;
            case THEME_DARK_DARKBAR:
                setTheme(R.style.AppTheme_MaterialAboutActivity_Dark_DarkActionBar);
                colorIcon = R.color.mal_color_icon_dark_theme;
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

    @NonNull @Override
    protected ViewTypeManager getViewTypeManager() {
        return new MyViewTypeManager();
    }
}
