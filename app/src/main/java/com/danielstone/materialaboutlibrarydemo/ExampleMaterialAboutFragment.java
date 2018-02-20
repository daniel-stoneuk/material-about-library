package com.danielstone.materialaboutlibrarydemo;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.Arrays;

import static com.danielstone.materialaboutlibrarydemo.ExampleMaterialAboutActivity.THEME_LIGHT_DARKBAR;


public class ExampleMaterialAboutFragment extends MaterialAboutFragment {

    private MaterialAboutActionItem createDynamicItem(String subText, final Context c) {
        return new MaterialAboutActionItem.Builder()
                .text("Dynamic UI")
                .subText(subText)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_refresh)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme)
                        ).sizeDp(18))
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        MaterialAboutList list = getList();
                        ArrayList<MaterialAboutCard> cards = list.getCards();
                        cards.get(2).getItems().remove(6);
                        cards.get(2).getItems().add(6, createDynamicItem("Random number: " + ((int) (Math.random() * 10)), c));
                        refreshMaterialAboutList();
                    }
                })
                .build();
    }

    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {
        MaterialAboutList list = Demo.createMaterialAboutList(c, R.color.mal_color_icon_dark_theme, THEME_LIGHT_DARKBAR);

        list.getCards().get(2).getItems().add(createDynamicItem("Tap for a random number", c));

        final MaterialAboutActionItem time = new MaterialAboutActionItem.Builder()
                .text("Unix Time In Millis")
                .subText("Time")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_clock)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme)
                        ).sizeDp(18))
                .build();
        list.getCards().get(2).getItems().add(time);

        return list;
    }

    final Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.i("MaterialAboutFragment", "Updating with time");
            if (getList().getCards().size() > 0) {
                getList().getCards().get(2).getItems().remove(7);
                getList().getCards().get(2).getItems().add(createTimeItem(getContext()));
                refreshMaterialAboutList();
            }
            handler.postDelayed(this, 1000);
        }
    };

    private MaterialAboutActionItem createTimeItem(Context c) {
        return new MaterialAboutActionItem.Builder()
                .text("Unix Time")
                .subText("" + System.currentTimeMillis())
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_clock)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme)
                        ).sizeDp(18))
                .build();
    }


    @Override
    public void onResume() {
        super.onResume();
        runnable.run();
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected int getTheme() {
        return R.style.AppTheme_MaterialAboutActivity_Fragment;
    }
}
