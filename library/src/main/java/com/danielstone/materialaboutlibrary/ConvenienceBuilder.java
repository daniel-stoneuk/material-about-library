package com.danielstone.materialaboutlibrary;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.webkit.WebView;
import android.widget.Toast;

import com.danielstone.materialaboutlibrary.model.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutTitleItem;

public class ConvenienceBuilder {

    public static MaterialAboutTitleItem createAppTitle(String appName, Drawable applicationIcon) {
        return new MaterialAboutTitleItem.Builder()
                .text(appName)
                .icon(applicationIcon)
                .build();
    }

    /**
     * This method tries to find the app name in R.string.app_name
     * and the launcher icon in R.mipmap.ic_launcher
     *
     * @param ctx Context
     * @return A title with the app name and the app icon
     */
    public static MaterialAboutTitleItem createAppTitle(Context ctx) {
        String appName = getStringResourceByName(ctx, "app_name");
        Drawable applicationIcon = ctx.getPackageManager().getApplicationIcon(ctx.getApplicationInfo());
        return createAppTitle(appName, applicationIcon);
    }

    /**
     * This methods reads the version infos from the PackageManager and displays them
     *
     * @param ctx Context
     * @return A MaterialAboutItem with the version infos
     * @throws PackageManager.NameNotFoundException
     */
    public static MaterialAboutItem createVersionItem(Context ctx) throws PackageManager.NameNotFoundException {
        PackageInfo pInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
        String versionName = pInfo.versionName;
        int versionCode = pInfo.versionCode;

        return new MaterialAboutActionItem.Builder()
                .text(com.danielstone.materialaboutlibrary.R.string.mal_version_title)
                .subText(versionName + " (" + versionCode + ")")
                .icon(com.danielstone.materialaboutlibrary.R.drawable.ic_about_info)
                .build();
    }

    /**
     * @param ctx         Context
     * @param htmlString  contains HTML or an internet address
     * @param isStringUrl if true htmlString contains an internet address, if false htmlString contains HTML
     * @return MaterialAboutActionItem
     */
    public static MaterialAboutActionItem createLicenseItem(Context ctx, String htmlString, boolean isStringUrl) {
        return createWebviewDialogItem(ctx, R.string.mal_license_title, R.drawable.ic_about_licenses, htmlString, isStringUrl);
    }

    public static MaterialAboutActionItem createDataprivacyItem(Context ctx, String htmlString, boolean isStringUrl) {
        return createWebviewDialogItem(ctx, R.string.mal_dataprivacy, R.drawable.ic_about_dataprivacy, htmlString, isStringUrl);
    }

    public static MaterialAboutActionItem createWebviewDialogItem(Context ctx, int stringResIDTitle, int iconResID, String htmlString, boolean isStringUrl) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ctx);
        alertBuilder.setTitle(stringResIDTitle);

        WebView wv = new WebView(ctx);
        if (isStringUrl) {
            wv.loadUrl(htmlString);
        } else {
            wv.loadData(htmlString, "text/html; charset=utf-8", "UTF-8");
        }
        alertBuilder.setView(wv);
        alertBuilder.setNegativeButton(R.string.mal_close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = alertBuilder.create();

        return new MaterialAboutActionItem.Builder()
                .text(stringResIDTitle)
                .icon(iconResID)
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        dialog.show();
                    }
                })
                .build();
    }

    /**
     * @param ctx        Context
     * @param websiteUrl Uri, the url needs to be in the form "http://example.com/"
     * @return MaterialAboutActionItem
     */
    public static MaterialAboutActionItem createWebsiteItem(final Context ctx, final Uri websiteUrl) {
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

    public static MaterialAboutActionItem createAuthorItem(String author, String subHeader) {
        return new MaterialAboutActionItem.Builder()
                .text(author)
                .subText(subHeader)
                .icon(R.drawable.ic_about_person)
                .build();
    }

    public static MaterialAboutActionItem createFacebookItem(final Context ctx, final String facebookId) {
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

    public static MaterialAboutActionItem createRateButtonItem(final Context ctx) {
        Uri uri = Uri.parse("market://details?id=" + ctx.getPackageName());
        final Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        } else {
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }

        return new MaterialAboutActionItem.Builder()
                .text(R.string.mal_rate_this_app)
                .subText(R.string.mal_rate_subtext)
                .icon(R.drawable.ic_about_star)
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        try {
                            ctx.startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            ctx.startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + ctx.getPackageName())));
                        }
                    }
                })
                .build();
    }

    public static MaterialAboutActionItem createPhoneItem(final Context ctx, String phoneNumber){
        final Intent phoneIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        return new MaterialAboutActionItem.Builder()
                .text(R.string.mal_contact_phone)
                .subText(phoneNumber)
                .icon(R.drawable.ic_about_phone)
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        try {
                            ctx.startActivity(phoneIntent);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(ctx, R.string.mal_contact_phone_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .build();
    }

    public static MaterialAboutActionItem createEmailItem(final Context ctx, String email){
        final Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType("text/plain");
        mailIntent.putExtra(Intent.EXTRA_EMAIL, email);
        return new MaterialAboutActionItem.Builder()
                .text(R.string.mal_contact_send_email)
                .subText(email)
                .icon(R.drawable.ic_about_email)
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        ctx.startActivity(mailIntent);
                    }
                })
                .build();
    }

    public static MaterialAboutCard createAuthorCard(final Context ctx, String author, String subHeader, Uri websiteUrl, String facebookId) {
        MaterialAboutCard.Builder authorBuilder = new MaterialAboutCard.Builder().title(R.string.mal_author);

        if (author != null) {
            authorBuilder.addItem(createAuthorItem(author, subHeader));
        }
        if (websiteUrl != null) {
            authorBuilder.addItem(createWebsiteItem(ctx, websiteUrl));
        }
        if (facebookId != null) {
            authorBuilder.addItem(createFacebookItem(ctx, facebookId));
        }
        return authorBuilder.build();
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
