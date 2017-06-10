package com.danielstone.materialaboutlibrarydemo;

import android.content.Context;

import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

import static com.danielstone.materialaboutlibrarydemo.ExampleMaterialAboutActivity.THEME_LIGHT_DARKBAR;


public class ExampleMaterialAboutFragment extends MaterialAboutFragment {


    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {
        return Demo.createMaterialAboutList(c, R.color.mal_color_icon_dark_theme, THEME_LIGHT_DARKBAR);
    }

    @Override
    protected int getTheme() {
        return R.style.AppTheme_MaterialAboutActivity_Fragment;
    }
}
