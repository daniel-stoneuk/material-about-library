package com.danielstone.materialaboutlibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.TypedValue;

import com.danielstone.materialaboutlibrary.model.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutTitleItem;

public class ConvenienceBuilder {

    public static MaterialAboutTitleItem createAppTitle(String appName, int icLauncher) {
        return new MaterialAboutTitleItem.Builder()
                .text(appName)
                .icon(icLauncher)
                .build();
    }

    public static MaterialAboutTitleItem createAppTitle (Context ctx) {
        TypedValue typedValue = new TypedValue();
        ctx.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        int color = typedValue.data;

        String appName = getStringResourceByName(ctx, "app_name");
        int icLauncher = getMipMapByName(ctx, "ic_launcher");
        return createAppTitle(appName, icLauncher);
    }

    public static MaterialAboutItem createVersionItem (Context ctx) throws PackageManager.NameNotFoundException{
        PackageInfo pInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
        String versionName = pInfo.versionName;
        int versionCode = pInfo.versionCode;

        return new MaterialAboutActionItem.Builder()
                .text(ctx.getText(com.danielstone.materialaboutlibrary.R.string.mal_version_title))
                .subText(versionName + " (" + versionCode + ")")
                .icon(com.danielstone.materialaboutlibrary.R.drawable.ic_about_info)
                .build();
    }

    private static String getStringResourceByName(Context ctx, String stringName) {
        String packageName = ctx.getPackageName();
        int resId = ctx.getResources().getIdentifier(stringName, "string", packageName);
        return ctx.getString(resId);
    }

    private static int getMipMapByName(Context ctx, String stringName) {
        String packageName = ctx.getPackageName();
        return ctx.getResources().getIdentifier(stringName, "mipmap", packageName);
    }

}
