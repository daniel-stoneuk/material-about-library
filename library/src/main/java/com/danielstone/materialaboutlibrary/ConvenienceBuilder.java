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
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.webkit.WebView;

import com.danielstone.materialaboutlibrary.model.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutTitleItem;

@SuppressWarnings("JavaDoc")
public class ConvenienceBuilder {

    public static MaterialAboutTitleItem createAppTitleItem(String appName, Drawable applicationIcon) {
        return new MaterialAboutTitleItem(appName, applicationIcon);
    }

    public static MaterialAboutTitleItem createAppTitleItem(Context c) {
        CharSequence appName = c.getPackageManager().getApplicationLabel(c.getApplicationInfo());
        Drawable applicationIcon = c.getPackageManager().getApplicationIcon(c.getApplicationInfo());
        return createAppTitleItem(appName == null ? "" : appName.toString(), applicationIcon);
    }

    /**
     * This methods reads the version infos from the PackageManager and displays them
     *
     * @param c Context
     * @return A MaterialAboutItem with the version infos
     * @throws PackageManager.NameNotFoundException
     */
    public static MaterialAboutItem createVersionActionItem(Context c, CharSequence text, Drawable icon, boolean includeVersionCode) throws PackageManager.NameNotFoundException {
        PackageInfo pInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
        String versionName = pInfo.versionName;
        int versionCode = pInfo.versionCode;

        return new MaterialAboutActionItem.Builder()
                .text(text)
                .subText(versionName + (includeVersionCode ? " (" + versionCode + ")" : ""))
                .icon(icon)
                .build();
    }

    public static MaterialAboutActionItem.OnClickListener createWebViewDialogOnClickAction(final Context c, final CharSequence dialogTitle, final String htmlString, final boolean isStringUrl) {

        return new MaterialAboutActionItem.OnClickListener() {
            @Override
            public void onClick() {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(c);
                alertBuilder.setTitle(dialogTitle);

                WebView wv = new WebView(c);
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
                dialog.show();
            }
        };
    }


    public static MaterialAboutActionItem createWebViewDialogItem(Context c, CharSequence text, @Nullable CharSequence subText, Drawable icon, CharSequence dialogTitle, String htmlString, boolean isStringUrl) {
        return new MaterialAboutActionItem.Builder()
                .text(text)
                .subText(subText)
                .icon(icon)
                .setOnClickListener(createWebViewDialogOnClickAction(c, dialogTitle, htmlString, isStringUrl))
                .build();
    }


    public static MaterialAboutActionItem.OnClickListener createWebsiteOnClickAction(final Context c, final Uri websiteUrl) {
        return new MaterialAboutActionItem.OnClickListener() {
            @Override
            public void onClick() {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(websiteUrl);
                c.startActivity(i);
            }
        };
    }

    /**
     * Creates an ActionItem which will open a url when tapped
     *
     * @param c          context
     * @param text
     * @param icon
     * @param websiteUrl (subText)
     * @return Item to add to card.
     */
    public static MaterialAboutActionItem createWebsiteActionItem(Context c, CharSequence text, Drawable icon, final Uri websiteUrl) {
        return new MaterialAboutActionItem.Builder()
                .text(text)
                .subText(websiteUrl.toString())
                .icon(icon)
                .setOnClickListener(createWebsiteOnClickAction(c, websiteUrl))
                .build();
    }

    /**
     * Creates a MaterialAboutActionItem.OnClickListener that will open
     * the Google Play store listing for the app.
     *
     * @param c context
     * @return onClickListener
     */
    public static MaterialAboutActionItem.OnClickListener createRateOnClickAction(final Context c) {
        Uri uri = Uri.parse("market://details?id=" + c.getPackageName());
        final Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        } else {
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }

        return new MaterialAboutActionItem.OnClickListener() {
            @Override
            public void onClick() {
                try {
                    c.startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    c.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + c.getPackageName())));
                }
            }
        };
    }

    /**
     * Creates an ActionItem which will open the play store when tapped
     *
     * @param c       context
     * @param text
     * @param subText
     * @param icon
     * @return Item to add to card.
     */
    public static MaterialAboutActionItem createRateActionItem(Context c, CharSequence text, @Nullable CharSequence subText, Drawable icon) {

        return new MaterialAboutActionItem.Builder()
                .text(text)
                .subText(subText)
                .icon(icon)
                .setOnClickListener(createRateOnClickAction(c))
                .build();
    }

    /**
     * Creates a MaterialAboutActionItem.OnClickListener that will open
     * an email intent with specified address.
     *
     * @param c     context
     * @param email email address
     * @return onClickListener
     */
    public static MaterialAboutActionItem.OnClickListener createEmailOnClickAction(final Context c, String email, String emailSubject) {

        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);

        return new MaterialAboutActionItem.OnClickListener() {
            @Override
            public void onClick() {
                c.startActivity(Intent.createChooser(emailIntent, c.getString(R.string.mal_send_email)));
            }
        };
    }

    /**
     * Creates an ActionItem which will open the an email intent when tapped
     *
     * @param c     context
     * @param text
     * @param icon
     * @param email email address (also used as subText)
     * @return Item to add to card.
     */
    public static MaterialAboutActionItem createEmailItem(Context c, CharSequence text, Drawable icon, String email, String emailSubject) {
        return new MaterialAboutActionItem.Builder()
                .text(text)
                .subText(email)
                .icon(icon)
                .setOnClickListener(createEmailOnClickAction(c, email, emailSubject))
                .build();
    }

}