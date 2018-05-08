package com.danielstone.materialaboutlibrarydemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutOnCheckedChangedAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutSwitchItem;
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
    public static final int THEME_CUSTOM_CARDVIEW = 4;

    protected int colorIcon = R.color.mal_color_icon_light_theme;

    @NonNull
    @Override
    protected MaterialAboutList getMaterialAboutList(@NonNull final Context c) {
        final MaterialAboutCard.Builder advancedCardBuilder = new MaterialAboutCard.Builder();
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
                        ((MaterialAboutSwitchItem) getList().getCards().get(4).getItems().get(1)).setChecked(!((MaterialAboutSwitchItem) getList().getCards().get(4).getItems().get(1)).isChecked());
                        refreshMaterialAboutList();
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

        final MaterialAboutSwitchItem switchItem = new MaterialAboutSwitchItem.Builder()
                .text("This a switch")
                .subText("This switch cannot be switched off")
                .setChecked(true)
                .build();
        switchItem.setOnCheckedChanged(new MaterialAboutOnCheckedChangedAction() {
            @Override
            public boolean onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchItem.setChecked(true);
                Toast.makeText(c, "This cannot be switched off", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        advancedCardBuilder.addItem(switchItem);

        advancedCardBuilder.addItem(createDynamicItem("Tap for a random number & swap position", c));

        return Demo.createMaterialAboutList(c, colorIcon, getIntent().getIntExtra(THEME_EXTRA, THEME_LIGHT_DARKBAR)).addCard(advancedCardBuilder.build());
    }

    private MaterialAboutActionItem createDynamicItem(String subText, final Context c) {
        final MaterialAboutActionItem item = new MaterialAboutActionItem.Builder()
                .text("Dynamic UI")
                .subText(subText)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_refresh)
                        .color(ContextCompat.getColor(c, colorIcon)
                        ).sizeDp(18))
                .build();
        item.setOnClickAction(new MaterialAboutItemOnClickAction() {
            @Override
            public void onClick() {
                getList().getCards().get(5).getItems().remove(getList().getCards().get(5).getItems().indexOf(item));
                int newIndex = ((int) (Math.random() * 5));
                getList().getCards().get(5).getItems().add(newIndex, item);
                item.setSubText("Random number: " + ((int) (Math.random() * 10)));

                setMaterialAboutList(getList());
            }
        });

        return item;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("test", "onCreate: " + getIntent().getIntExtra(THEME_EXTRA, THEME_LIGHT_DARKBAR));
        switch (getIntent().getIntExtra(THEME_EXTRA, THEME_LIGHT_DARKBAR)) {
            case THEME_LIGHT_LIGHTBAR:
                setTheme(R.style.AppTheme_MaterialAboutActivity_Light);
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
                setTheme(R.style.AppTheme_MaterialAboutActivity_Dark);
                colorIcon = R.color.mal_color_icon_dark_theme;
                break;
            case THEME_CUSTOM_CARDVIEW:
                setTheme(R.style.AppTheme_MaterialAboutActivity_Light_DarkActionBar_CustomCardView);
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
