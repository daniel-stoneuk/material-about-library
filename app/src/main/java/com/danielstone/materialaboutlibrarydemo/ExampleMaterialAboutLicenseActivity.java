package com.danielstone.materialaboutlibrarydemo;

import android.content.Context;
import android.view.MenuItem;

import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

public class ExampleMaterialAboutLicenseActivity extends ExampleMaterialAboutActivity {

    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {
        return Demo.createMaterialAboutLicenseList(c, colorIcon);
    }

    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.mal_title_licenses);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return false;
        }
    }
}
