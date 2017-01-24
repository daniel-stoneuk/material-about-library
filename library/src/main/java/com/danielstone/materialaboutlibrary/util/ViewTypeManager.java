package com.danielstone.materialaboutlibrary.util;

import android.content.Context;
import android.view.View;

import com.danielstone.materialaboutlibrary.R;
import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;

public abstract class ViewTypeManager {

    public static final class ItemType {
        public static final int ACTION_ITEM = 0;
        public static final int TITLE_ITEM = 1;
    }

    public static final class ItemLayout {
        public static final int ACTION_LAYOUT = R.layout.mal_material_about_action_item;
        public static final int TITLE_LAYOUT = R.layout.mal_material_about_title_item;
    }

    public abstract int getLayout(int itemType);

    public abstract MaterialAboutItemViewHolder getViewHolder(int itemType, View view);

    public abstract void setupItem(int itemType, MaterialAboutItemViewHolder holder, MaterialAboutItem item, Context context);
}
