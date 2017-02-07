package com.danielstone.materialaboutlibrarydemo;

import android.content.Context;

import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;


public class ExampleMaterialAboutFragment extends MaterialAboutFragment {

    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {
        return Demo.createMaterialAboutList(c, R.color.colorIconLight);
    }

    @Override
    protected int getTheme() {
        return THEME_LIGHT;
    }
}
