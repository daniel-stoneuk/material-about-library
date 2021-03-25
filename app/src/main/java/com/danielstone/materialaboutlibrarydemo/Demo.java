package com.danielstone.materialaboutlibrarydemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionCheckBoxItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionSwitchItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutCheckBoxItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutCheckableItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutOnCheckedChangedAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutSwitchItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.OpenSourceLicense;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

public class Demo {

    public static MaterialAboutList createMaterialAboutList(final Context c, final int theme) {
        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();

        // Add items to card

        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text("Material About Library")
                .desc("© 2020 Daniel Stone")
                .icon(R.mipmap.ic_launcher)
                .build());

        appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_information_outline)
                        .sizeDp(18),
                "Version",
                false));

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Changelog")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_history)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(c, "Releases", "https://github.com/daniel-stoneuk/material-about-library/releases", true, false))
                .build());

        appCardBuilder.addItem(new MaterialAboutCheckBoxItem.Builder()
                .text("Checkbox")
                .subText("Sub Text unchecked")
                .subTextChecked("Sub Text checked")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_checkbox_marked_outline)
                        .sizeDp(18))
                .setChecked(true)
                .setOnCheckedChanged(new MaterialAboutOnCheckedChangedAction() {
                    @Override
                    public boolean onCheckedChanged(MaterialAboutCheckableItem item, boolean isChecked) {
                        Toast.makeText(c, "checked: " + isChecked, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                })
                .build()
        );

        appCardBuilder.addItem(new MaterialAboutSwitchItem.Builder()
                .text("Switch")
                .subText("Sub Text unchecked")
                .subTextChecked("Sub Text checked")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_toggle_switch)
                        .sizeDp(18))
                .setChecked(false)
                .setOnCheckedChanged(new MaterialAboutOnCheckedChangedAction() {
                    @Override
                    public boolean onCheckedChanged(MaterialAboutCheckableItem item, boolean isChecked) {
                        Toast.makeText(c, "switch: " + isChecked, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                })
                .build()
        );

        appCardBuilder.addItem(new MaterialAboutActionCheckBoxItem.Builder()
                .text("Action Checkbox")
                .subText("Sub Text unchecked")
                .subTextChecked("Sub Text checked")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_checkbox_marked_outline)
                        .sizeDp(18))
                .setChecked(true)
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        Toast.makeText(c, "action checkbox clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnLongClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        Toast.makeText(c, "action checkbox long clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnCheckedChanged(new MaterialAboutOnCheckedChangedAction() {
                    @Override
                    public boolean onCheckedChanged(MaterialAboutCheckableItem item, boolean isChecked) {
                        Toast.makeText(c, "checked: " + isChecked, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                })
                .build()
        );

        appCardBuilder.addItem(new MaterialAboutActionSwitchItem.Builder()
                .text("Action Switch")
                .subText("Sub Text unchecked")
                .subTextChecked("Sub Text checked")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_toggle_switch)
                        .sizeDp(18))
                .setChecked(false)
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        Toast.makeText(c, "action switch clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnLongClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        Toast.makeText(c, "action switch long clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnCheckedChanged(new MaterialAboutOnCheckedChangedAction() {
                    @Override
                    public boolean onCheckedChanged(MaterialAboutCheckableItem item, boolean isChecked) {
                        Toast.makeText(c, "switch: " + isChecked, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                })
                .build()
        );

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Licenses")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .sizeDp(18))
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        Intent intent = new Intent(c, ExampleMaterialAboutLicenseActivity.class);
                        intent.putExtra(ExampleMaterialAboutActivity.THEME_EXTRA, theme);
                        c.startActivity(intent);
                    }
                })
                .build());

        MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title("Author");
//        authorCardBuilder.titleColor(ContextCompat.getColor(c, R.color.colorAccent));

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Daniel Stone")
                .subText("United Kingdom")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .sizeDp(18))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Fork on GitHub")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://github.com/daniel-stoneuk")))
                .build());

        MaterialAboutCard.Builder convenienceCardBuilder = new MaterialAboutCard.Builder();

        convenienceCardBuilder.title("Convenience Builder");

        convenienceCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_information_outline)
                        .sizeDp(18),
                "Version",
                false));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createWebsiteActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_earth)
                        .sizeDp(18),
                "Visit Website",
                true,
                Uri.parse("http://danstone.uk")));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createRateActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_star)
                        .sizeDp(18),
                "Rate this app",
                null
        ));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createEmailItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_email)
                        .sizeDp(18),
                "Send an email",
                true,
                "apps@danstone.uk",
                "Question concerning MaterialAboutLibrary"));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createPhoneItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_phone)
                        .sizeDp(18),
                "Call me",
                true,
                "+44 12 3456 7890"));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createMapItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_map)
                        .sizeDp(18),
                "Visit London",
                null,
                "London Eye"));

        MaterialAboutCard.Builder otherCardBuilder = new MaterialAboutCard.Builder();
        otherCardBuilder.title("Other");

        otherCardBuilder.cardColor(Color.parseColor("#c0cfff"));

        otherCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_language_html5)
                        .sizeDp(18))
                .text("HTML Formatted Sub Text")
                .subTextHtml("This is <b>HTML</b> formatted <i>text</i> <br /> This is very cool because it allows lines to get very long which can lead to all kinds of possibilities. <br /> And line breaks. <br /> Oh and by the way, this card has a custom defined background.")
                .setIconGravity(MaterialAboutActionItem.GRAVITY_TOP)
                .build()
        );
        return new MaterialAboutList(appCardBuilder.build(), authorCardBuilder.build(), convenienceCardBuilder.build(), otherCardBuilder.build());
    }

    public static MaterialAboutList createMaterialAboutLicenseList(final Context c) {

        MaterialAboutCard materialAboutLibraryLicenseCard = ConvenienceBuilder.createLicenseCard(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .sizeDp(18),
                "material-about-library", "2016", "Daniel Stone",
                OpenSourceLicense.APACHE_2);

        MaterialAboutCard androidIconicsLicenseCard = ConvenienceBuilder.createLicenseCard(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .sizeDp(18),
                "Android Iconics", "2016", "Mike Penz",
                OpenSourceLicense.APACHE_2);

        MaterialAboutCard leakCanaryLicenseCard = ConvenienceBuilder.createLicenseCard(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .sizeDp(18),
                "LeakCanary", "2015", "Square, Inc",
                OpenSourceLicense.APACHE_2);

        MaterialAboutCard mitLicenseCard = ConvenienceBuilder.createLicenseCard(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .sizeDp(18),
                "MIT Example", "2017", "Matthew Ian Thomson",
                OpenSourceLicense.MIT);

        MaterialAboutCard gplLicenseCard = ConvenienceBuilder.createLicenseCard(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .sizeDp(18),
                "GPL Example", "2017", "George Perry Lindsay",
                OpenSourceLicense.GNU_GPL_3);

        return new MaterialAboutList(materialAboutLibraryLicenseCard,
                androidIconicsLicenseCard,
                leakCanaryLicenseCard,
                mitLicenseCard,
                gplLicenseCard);
    }
}
