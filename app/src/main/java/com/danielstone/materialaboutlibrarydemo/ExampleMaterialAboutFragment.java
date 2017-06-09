package com.danielstone.materialaboutlibrarydemo;

import android.content.Context;

import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

import static com.danielstone.materialaboutlibrarydemo.ExampleMaterialAboutActivity.THEME_LIGHT_DARKBAR;


public class ExampleMaterialAboutFragment extends MaterialAboutFragment {


    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {
        return Demo.createMaterialAboutList(c, R.color.colorIconDark, THEME_LIGHT_DARKBAR);
    }

    @Override
    protected int getTheme() {
        return THEME_DARK;
    }
}
