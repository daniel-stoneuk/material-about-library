package com.danielstone.materialaboutlibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.webkit.WebView;

import com.danielstone.materialaboutlibrary.model.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutTitleItem;

public class ConvenienceBuilder {

    public static MaterialAboutTitleItem createAppTitle(String appName, int icLauncher) {
        return new MaterialAboutTitleItem.Builder()
                .text(appName)
                .icon(icLauncher)
                .build();
    }

    /**
     * This method tries to find the app name in R.string.app_name
     * and the launcher icon in R.mipmap.ic_launcher
     * @param ctx Context
     * @return A title with the app name and the app icon
     */
    public static MaterialAboutTitleItem createAppTitle (Context ctx) {
        TypedValue typedValue = new TypedValue();
        ctx.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        int color = typedValue.data;

        String appName = getStringResourceByName(ctx, "app_name");
        int icLauncher = getMipMapByName(ctx, "ic_launcher");
        return createAppTitle(appName, icLauncher);
    }

    /**
     * This methods reads the version infos from the PackageManager and displays them
     * @param ctx Context
     * @return A MaterialAboutItem with the version infos
     * @throws PackageManager.NameNotFoundException
     */
    public static MaterialAboutItem createVersionItem (Context ctx) throws PackageManager.NameNotFoundException{
        PackageInfo pInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
        String versionName = pInfo.versionName;
        int versionCode = pInfo.versionCode;

        return new MaterialAboutActionItem.Builder()
                .text(com.danielstone.materialaboutlibrary.R.string.mal_version_title)
                .subText(versionName + " (" + versionCode + ")")
                .icon(com.danielstone.materialaboutlibrary.R.drawable.ic_about_info)
                .build();
    }

    public static MaterialAboutActionItem createLicenseItem (Context ctx, String htmlString) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ctx);
        alertBuilder.setTitle(R.string.mal_license_title);

        WebView wv = new WebView(ctx);
        wv.loadData(htmlString, "text/html; charset=utf-8", "UTF-8");
        alertBuilder.setView(wv);
        alertBuilder.setNegativeButton(R.string.mal_close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = alertBuilder.create();

        return new MaterialAboutActionItem.Builder()
                .text(R.string.mal_license_title)
                .icon(R.drawable.ic_about_licenses)
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        dialog.show();
                    }
                })
                .build();
    }

    /**
     *
     * @param ctx Context
     * @param websiteUrl Uri, the url needs to be in the form "http://example.com/"
     * @return MaterialAboutActionItem
     */
    public static MaterialAboutActionItem createWebsiteItem (final Context ctx, final Uri websiteUrl) {
        return new MaterialAboutActionItem.Builder()
                .text(R.string.mal_visit_website)
                .subText(websiteUrl.toString())
                .icon(com.danielstone.materialaboutlibrary.R.drawable.ic_about_info)
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(websiteUrl);
                        ctx.startActivity(i);
                    }
                })
                .build();
    }

    public static MaterialAboutActionItem createAuthorItem (String author, String subHeader) {
        return new MaterialAboutActionItem.Builder()
                .text(author)
                .subText(subHeader)
                .icon(R.drawable.ic_about_person)
                .build();
    }

    public static MaterialAboutActionItem createFacebookItem (final Context ctx, final String facebookId) {
        return new MaterialAboutActionItem.Builder()
                .text(R.string.mal_facebook)
                .icon(R.drawable.ic_about_facebook)
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String uri = "https://facebook.com/" + facebookId;
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        ctx.startActivity(intent);
                    }
                })
                .build();
    }

    public static MaterialAboutCard createAuthorCard (final Context ctx, String author, String subHeader, Uri websiteUrl, String facebookId) {
        return new MaterialAboutCard.Builder()
                .title(R.string.mal_author)
                .addItem(createAuthorItem(author, subHeader))
                .addItem(createWebsiteItem(ctx, websiteUrl))
                .addItem(createFacebookItem(ctx, facebookId))
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
