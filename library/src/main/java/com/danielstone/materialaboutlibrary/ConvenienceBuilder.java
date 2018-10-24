package com.danielstone.materialaboutlibrary;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.webkit.WebView;
import android.widget.Toast;

import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.util.OpenSourceLicense;

@SuppressWarnings("JavaDoc")
public class ConvenienceBuilder {

    public static MaterialAboutTitleItem createAppTitleItem(String appName, Drawable applicationIcon) {
        return new MaterialAboutTitleItem(appName, null, applicationIcon);
    }

    public static MaterialAboutTitleItem createAppTitleItem(Context c) {
        Context applicationContext = c.getApplicationContext();
        PackageManager packageManager = applicationContext.getPackageManager();
        ApplicationInfo applicationInfo = applicationContext.getApplicationInfo();
        CharSequence appName = packageManager.getApplicationLabel(applicationInfo);
        Drawable applicationIcon = packageManager.getApplicationIcon(applicationInfo);
        return createAppTitleItem(appName == null ? "" : appName.toString(), applicationIcon);
    }

    /**
     * Creates an item with version info read from the PackageManager for current application.
     *
     * @param c      context
     * @param icon
     * @param text
     * @param includeVersionCode
     * @return Item to add to card.
     */
    public static MaterialAboutActionItem createVersionActionItem(Context c, Drawable icon, CharSequence text, boolean includeVersionCode) {
	    String versionName = "";
	    int versionCode = 0;
	    try {
		    PackageInfo pInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
		    versionName = pInfo.versionName;
		    versionCode = pInfo.versionCode;
	    } catch (PackageManager.NameNotFoundException ignored) {
		    // This shouldn't happen.
	    }
        return new MaterialAboutActionItem.Builder()
                .text(text)
                .subText(versionName + (includeVersionCode ? " (" + versionCode + ")" : ""))
                .icon(icon)
                .build();
    }

    public static MaterialAboutItemOnClickAction createWebViewDialogOnClickAction(final Context c, final CharSequence dialogTitle, final String htmlString, final boolean isStringUrl, final boolean supportZoom) {
        return createWebViewDialogOnClickAction(c, dialogTitle, c.getString(R.string.mal_close), htmlString, isStringUrl, supportZoom);
    }

    public static MaterialAboutItemOnClickAction createWebViewDialogOnClickAction(final Context c, final CharSequence dialogTitle, final CharSequence dialogNegativeButton, final String htmlString, final boolean isStringUrl, final boolean supportZoom) {

        return new MaterialAboutItemOnClickAction() {
            @Override
            public void onClick() {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(c);
                alertBuilder.setTitle(dialogTitle);

                final WebView wv = new WebView(c);
                wv.getSettings().setSupportZoom(supportZoom);
                if (!supportZoom) {
                    wv.getSettings().setLoadWithOverviewMode(true);
                    wv.getSettings().setUseWideViewPort(true);
                }
                if (isStringUrl) {
                    wv.loadUrl(htmlString);
                } else {
                    wv.loadData(htmlString, "text/html; charset=utf-8", "UTF-8");
                }

                alertBuilder.setView(wv);
                alertBuilder.setNegativeButton(dialogNegativeButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        wv.destroy();
                        dialog.dismiss();
                    }
                });

                final AlertDialog dialog = alertBuilder.create();
                dialog.show();
            }
        };
    }


    public static MaterialAboutActionItem createWebViewDialogItem(Context c, Drawable icon, CharSequence text, @Nullable CharSequence subText, CharSequence dialogTitle, String htmlString, boolean isStringUrl, boolean supportZoom) {
        return createWebViewDialogItem(c, icon, text, subText, dialogTitle, c.getString(R.string.mal_close), htmlString, isStringUrl, supportZoom);
    }

    public static MaterialAboutActionItem createWebViewDialogItem(Context c, Drawable icon, CharSequence text, @Nullable CharSequence subText, CharSequence dialogTitle, CharSequence dialogNegativeButton, String htmlString, boolean isStringUrl, boolean supportZoom) {
        return new MaterialAboutActionItem.Builder()
                .text(text)
                .subText(subText)
                .icon(icon)
                .setOnClickAction(createWebViewDialogOnClickAction(c, dialogTitle, dialogNegativeButton, htmlString, isStringUrl, supportZoom))
                .build();
    }


    public static MaterialAboutItemOnClickAction createWebsiteOnClickAction(final Context c, final Uri websiteUrl) {
        return new MaterialAboutItemOnClickAction() {
            @Override
            public void onClick() {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(websiteUrl);
                try {
                    c.startActivity(i);
                } catch (Exception e) {
                    // No activity to handle intent
                    Toast.makeText(c, R.string.mal_activity_exception, Toast.LENGTH_SHORT).show();
                }
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
    public static MaterialAboutActionItem createWebsiteActionItem(Context c, Drawable icon, CharSequence text, boolean showAddress, final Uri websiteUrl) {
        return new MaterialAboutActionItem.Builder()
                .text(text)
                .subText((showAddress ? websiteUrl.toString() : null))
                .icon(icon)
                .setOnClickAction(createWebsiteOnClickAction(c, websiteUrl))
                .build();
    }

    /**
     * Creates a MaterialAboutItemOnClickAction that will open
     * the Google Play store listing for the app.
     *
     * @param c context
     * @return onClickAction
     */
    public static MaterialAboutItemOnClickAction createRateOnClickAction(final Context c) {
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

        return new MaterialAboutItemOnClickAction() {
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
    public static MaterialAboutActionItem createRateActionItem(Context c, Drawable icon, CharSequence text, @Nullable CharSequence subText) {

        return new MaterialAboutActionItem.Builder()
                .text(text)
                .subText(subText)
                .icon(icon)
                .setOnClickAction(createRateOnClickAction(c))
                .build();
    }

    public static MaterialAboutItemOnClickAction createEmailOnClickAction(final Context c, String email, String emailSubject) {
        return createEmailOnClickAction(c, email, emailSubject, c.getString(R.string.mal_send_email));
    }

    /**
     * Creates a MaterialAboutItemOnClickAction that will open
     * an email intent with specified address.
     *
     * @param c     context
     * @param email email address
     * @return onClickAction
     */
    public static MaterialAboutItemOnClickAction createEmailOnClickAction(final Context c, String email, String emailSubject, final CharSequence chooserTitle) {

        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);

        return new MaterialAboutItemOnClickAction() {
            @Override
            public void onClick() {
                try {
                    c.startActivity(Intent.createChooser(emailIntent, chooserTitle));
                } catch (Exception e) {
                    // No activity to handle intent
                    Toast.makeText(c, R.string.mal_activity_exception, Toast.LENGTH_SHORT).show();
                }
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
    public static MaterialAboutActionItem createEmailItem(Context c, Drawable icon, CharSequence text, boolean showEmail, String email, String emailSubject, CharSequence chooserTitle) {
        return new MaterialAboutActionItem.Builder()
                .text(text)
                .subText((showEmail ? email : null))
                .icon(icon)
                .setOnClickAction(createEmailOnClickAction(c, email, emailSubject, chooserTitle))
                .build();
    }

    public static MaterialAboutActionItem createEmailItem(Context c, Drawable icon, CharSequence text, boolean showEmail, String email, String emailSubject) {
        return createEmailItem(c, icon, text, showEmail, email, emailSubject, c.getString(R.string.mal_send_email));
    }

    /**
     * Creates a MaterialAboutItemOnClickAction that will open
     * the dialer with specified number.
     *
     * @param c      context
     * @param number phone number
     * @return onClickAction
     */
    public static MaterialAboutItemOnClickAction createPhoneOnClickAction(final Context c, String number) {
        final Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse("tel:" + number));

        return new MaterialAboutItemOnClickAction() {
            @Override
            public void onClick() {
                try {
                    c.startActivity(phoneIntent);
                } catch (Exception e) {
                    // No activity to handle intent
                    Toast.makeText(c, R.string.mal_activity_exception, Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    /**
     * Creates an ActionItem which will open the dialer when tapped
     *
     * @param c      context
     * @param text
     * @param icon
     * @param number phone number (also used as subText)
     * @return Item to add to card.
     */
    public static MaterialAboutActionItem createPhoneItem(Context c, Drawable icon, CharSequence text, boolean showNumber, String number) {
        return new MaterialAboutActionItem.Builder()
                .text(text)
                .subText((showNumber ? number : null))
                .icon(icon)
                .setOnClickAction(createPhoneOnClickAction(c, number))
                .build();
    }

    /**
     * Creates a MaterialAboutItemOnClickAction that will open
     * maps with a query.
     * Query can be either lat,lng(label) or written address
     *
     * @param c      context
     * @param addressQuery address query
     * @return onClickAction
     */
    public static MaterialAboutItemOnClickAction createMapOnClickAction(final Context c, String addressQuery) {
        final Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(Uri.parse("geo:0,0").buildUpon().appendQueryParameter("q", addressQuery).build());
        return new MaterialAboutItemOnClickAction() {
            @Override
            public void onClick() {
                try {
                    c.startActivity(mapIntent);
                } catch (Exception e) {
                    // No activity to handle intent
                    Toast.makeText(c, R.string.mal_activity_exception, Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    /**
     * Creates an ActionItem which will open maps when tapped
     * Query can be either lat,lng(label) or written address
     *
     * @param c      context
     * @param text
     * @param subText can be set to null
     * @param icon
     * @param addressQuery addressQuery
     * @return Item to add to card.
     */
    public static MaterialAboutActionItem createMapItem(Context c, Drawable icon, CharSequence text, CharSequence subText, String addressQuery) {
        return new MaterialAboutActionItem.Builder()
                .text(text)
                .subText(subText)
                .icon(icon)
                .setOnClickAction(createMapOnClickAction(c, addressQuery))
                .build();
    }

    public static MaterialAboutCard createLicenseCard(Context c, Drawable icon, CharSequence libraryTitle, CharSequence year, CharSequence name, OpenSourceLicense license) {

        MaterialAboutActionItem licenseItem = new MaterialAboutActionItem.Builder()
                .icon(icon)
                .setIconGravity(MaterialAboutActionItem.GRAVITY_TOP)
                .text(libraryTitle)
                .subText(String.format(c.getString(license.getResourceId()), year, name))
                .build();

        return new MaterialAboutCard.Builder().addItem(licenseItem).build();
    }

}
