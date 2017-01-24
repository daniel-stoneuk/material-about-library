package com.danielstone.materialaboutlibrarydemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;

import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;
import com.danielstone.materialaboutlibrarydemo.custom.MyCustomItem;
import com.danielstone.materialaboutlibrarydemo.custom.MyViewTypeManager;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

public class ExampleMaterialAboutActivity extends MaterialAboutActivity {

    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {
        MaterialAboutCard.Builder advancedCardBuilder = new MaterialAboutCard.Builder();
        advancedCardBuilder.title("Advanced");

        advancedCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Snackbar demo")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_code_tags)
                        .color(ContextCompat.getColor(c, R.color.colorIcon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Snackbar.make(((ExampleMaterialAboutActivity) c).findViewById(R.id.mal_material_about_activity_coordinator_layout), "Test", Snackbar.LENGTH_SHORT).show();
                    }
                })
                .build());

        advancedCardBuilder.addItem(new MyCustomItem.Builder()
                .text("Custom Item")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_code_braces)
                        .color(ContextCompat.getColor(c, R.color.colorIcon))
                        .sizeDp(18))
                .build());

        advancedCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Dynamic UI")
                .subText("Tap for a random number.")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_refresh)
                        .color(ContextCompat.getColor(c, R.color.colorIcon)
                        ).sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        MaterialAboutList newList = getMaterialAboutList();
                        ((MaterialAboutActionItem) newList.getCards()
                                .get(4)
                                .getItems()
                                .get(2)).setSubText("Random number: " + ((int) (Math.random() * 10)));
                        setMaterialAboutList(newList);
                    }
                })
                .build());

        return Demo.createMaterialAboutList(c).addCard(advancedCardBuilder.build());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Call this method to let the scrollbar scroll off screen
//        setScrollToolbar(true);
    }

    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.mal_title_about);
    }

    @Override
    protected ViewTypeManager getViewTypeManager() {
        return new MyViewTypeManager();
    }
}
