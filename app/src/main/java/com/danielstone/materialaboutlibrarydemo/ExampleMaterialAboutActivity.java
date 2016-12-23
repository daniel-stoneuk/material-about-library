package com.danielstone.materialaboutlibrarydemo;

import android.util.Log;
import android.widget.Toast;

import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.model.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.model.MaterialAboutTitleItem;

public class ExampleMaterialAboutActivity extends MaterialAboutActivity {

    @Override
    protected MaterialAboutList getMaterialAboutList() {

        MaterialAboutCard.Builder builder = new MaterialAboutCard.Builder();
        builder.title("Author");

        builder.addItem(new MaterialAboutTitleItem.Builder()
                .text("Material About Library")
                .icon(R.mipmap.ic_launcher)
                .build());

        builder.addItem(new MaterialAboutActionItem.Builder()
                .text("Version")
                .subText("1.0.0")
                .icon(android.R.drawable.ic_dialog_email)
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Log.i("test", "onClick: Version Tapped");
                        Toast.makeText(ExampleMaterialAboutActivity.this, "Version Tapped", Toast.LENGTH_SHORT).show();
                    }
                })
                .build());
        builder.addItem(new MaterialAboutActionItem.Builder()
                .text("Changelog")
                .icon(android.R.drawable.ic_dialog_email)
                .build());
        builder.addItem(new MaterialAboutActionItem.Builder()
                .text("Introduction")
                .icon(android.R.drawable.ic_dialog_email)
                .build());
        builder.addItem(new MaterialAboutActionItem.Builder()
                .text("Licenses")
                .icon(android.R.drawable.ic_dialog_email)
                .build());
        return new MaterialAboutList.Builder()
                .addCard(builder.build())
                .addCard(builder.build())
                .addCard(builder.build())
                .addCard(builder.build())
                .addCard(builder.build())
                .addCard(builder.build())
                .build();
    }

}
