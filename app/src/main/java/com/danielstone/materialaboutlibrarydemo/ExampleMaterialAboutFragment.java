package com.danielstone.materialaboutlibrarydemo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.model.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.model.MaterialAboutTitleItem;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

/**
 * A placeholder fragment containing a simple view.
 */
public class ExampleMaterialAboutFragment extends MaterialAboutFragment {

    @Override
    protected MaterialAboutList getMaterialAboutList(final Context activityContext) {

        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();

        // Add items to card

        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text("Material About Activity")
                .icon(R.mipmap.ic_launcher)
                .build());

        try {

            appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(activityContext,
                    "Version",
                    new IconicsDrawable(activityContext)
                            .icon(GoogleMaterial.Icon.gmd_info_outline)
                            .color(ContextCompat.getColor(activityContext, R.color.colorIcon))
                            .sizeDp(18)));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Changelog")
                .icon(new IconicsDrawable(activityContext)
                        .icon(CommunityMaterial.Icon.cmd_history)
                        .color(ContextCompat.getColor(activityContext, R.color.colorIcon))
                        .sizeDp(18))
                .setOnClickListener(ConvenienceBuilder.createWebViewDialogOnClickAction(activityContext, "Releases", "https://github.com/daniel-stoneuk/material-about-library/releases", true))
                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Licenses")
                .icon(new IconicsDrawable(activityContext)
                        .icon(GoogleMaterial.Icon.gmd_book)
                        .color(ContextCompat.getColor(activityContext, R.color.colorIcon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(activityContext, "Material Design About Library with Mike Penz Android Iconics in demo", Toast.LENGTH_SHORT).show();
                    }
                })
                .build());

        MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title("Author");

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Daniel Stone")
                .subText("United Kingdom")
                .icon(new IconicsDrawable(activityContext)
                        .icon(GoogleMaterial.Icon.gmd_person)
                        .color(ContextCompat.getColor(activityContext, R.color.colorIcon))
                        .sizeDp(18))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Fork on GitHub")
                .icon(new IconicsDrawable(activityContext)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .color(ContextCompat.getColor(activityContext, R.color.colorIcon))
                        .sizeDp(18))
                .setOnClickListener(ConvenienceBuilder.createWebsiteOnClickAction(activityContext, Uri.parse("https://github.com/daniel-stoneuk")))
                .build());

        MaterialAboutCard.Builder supportCardBuilder = new MaterialAboutCard.Builder();

        supportCardBuilder.title("Support Development");
        supportCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Report Bugs")
                .subText("Report bugs or request new features.")
                .icon(new IconicsDrawable(activityContext)
                        .icon(GoogleMaterial.Icon.gmd_bug_report)
                        .color(ContextCompat.getColor(activityContext, R.color.colorIcon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(activityContext, "Bug report tapped", Toast.LENGTH_SHORT).show();
                    }
                })
                .build());

        return new MaterialAboutList(appCardBuilder.build(), authorCardBuilder.build(), supportCardBuilder.build());
    }

}
