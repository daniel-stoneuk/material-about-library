package com.danielstone.materialaboutlibrarydemo;

import android.content.Context;
import android.os.Handler;
import androidx.core.content.ContextCompat;
import android.util.Log;

import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import static com.danielstone.materialaboutlibrarydemo.ExampleMaterialAboutActivity.THEME_LIGHT_DARKBAR;


public class ExampleMaterialAboutFragment extends MaterialAboutFragment {

    private MaterialAboutActionItem createDynamicItem(String subText, final Context c) {
        final MaterialAboutActionItem item = new MaterialAboutActionItem.Builder()
                .text("Dynamic UI")
                .subText(subText)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon2.cmd_refresh)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme)
                        ).sizeDp(18))
                .build();
        item.setOnClickAction(new MaterialAboutItemOnClickAction() {
            @Override
            public void onClick() {
                item.setSubText("Random number: " + ((int) (Math.random() * 10)));
                refreshMaterialAboutList();
            }
        });
        return item;

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
                ((MaterialAboutActionItem) getList().getCards().get(2).getItems().get(7)).setSubText("" + System.currentTimeMillis());
                refreshMaterialAboutList();
            }
            handler.postDelayed(this, 1000);
        }
    };


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
