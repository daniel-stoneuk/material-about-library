package com.danielstone.materialaboutlibrary.util;

import android.content.Context;
import android.view.View;

import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutCheckBoxItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutSwitchItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;

import static com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager.ItemLayout.ACTION_LAYOUT;
import static com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager.ItemLayout.CHECKBOX_LAYOUT;
import static com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager.ItemLayout.SWITCH_LAYOUT;
import static com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager.ItemLayout.TITLE_LAYOUT;
import static com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager.ItemType.ACTION_ITEM;
import static com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager.ItemType.CHECKBOX_ITEM;
import static com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager.ItemType.SWITCH_ITEM;
import static com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager.ItemType.TITLE_ITEM;

public class DefaultViewTypeManager extends ViewTypeManager {

    public static final class ItemType {
        public static final int ACTION_ITEM = ViewTypeManager.ItemType.ACTION_ITEM;
        public static final int TITLE_ITEM = ViewTypeManager.ItemType.TITLE_ITEM;
        public static final int CHECKBOX_ITEM = ViewTypeManager.ItemType.CHECKBOX_ITEM;
        public static final int SWITCH_ITEM = ViewTypeManager.ItemType.SWITCH_ITEM;
    }

    public static final class ItemLayout {
        public static final int ACTION_LAYOUT = ViewTypeManager.ItemLayout.ACTION_LAYOUT;
        public static final int TITLE_LAYOUT = ViewTypeManager.ItemLayout.TITLE_LAYOUT;
        public static final int CHECKBOX_LAYOUT = ViewTypeManager.ItemLayout.CHECKBOX_LAYOUT;
        public static final int SWITCH_LAYOUT = ViewTypeManager.ItemLayout.SWITCH_LAYOUT;
    }

    public int getLayout(int itemType) {
        switch (itemType) {
            case ACTION_ITEM:
                return ACTION_LAYOUT;
            case TITLE_ITEM:
                return TITLE_LAYOUT;
            case CHECKBOX_ITEM:
                return CHECKBOX_LAYOUT;
            case SWITCH_ITEM:
                return SWITCH_LAYOUT;
            default:
                return -1;
        }
    }

    public MaterialAboutItemViewHolder getViewHolder(int itemType, View view) {
        switch (itemType) {
            case ACTION_ITEM:
                return MaterialAboutActionItem.getViewHolder(view);
            case TITLE_ITEM:
                return MaterialAboutTitleItem.getViewHolder(view);
            case CHECKBOX_ITEM:
                return MaterialAboutCheckBoxItem.getViewHolder(view);
            case SWITCH_ITEM:
                return MaterialAboutSwitchItem.getViewHolder(view);
            default:
                return null;
        }
    }

    public void setupItem(int itemType, MaterialAboutItemViewHolder holder, MaterialAboutItem item, Context context) {
        switch (itemType) {
            case ACTION_ITEM:
                MaterialAboutActionItem.setupItem((MaterialAboutActionItem.MaterialAboutActionItemViewHolder) holder, (MaterialAboutActionItem) item, context);
                break;
            case TITLE_ITEM:
                MaterialAboutTitleItem.setupItem((MaterialAboutTitleItem.MaterialAboutTitleItemViewHolder) holder, (MaterialAboutTitleItem) item, context);
                break;
            case CHECKBOX_ITEM:
                MaterialAboutCheckBoxItem.setupItem((MaterialAboutCheckBoxItem.MaterialAboutCheckBoxItemViewHolder) holder, (MaterialAboutCheckBoxItem) item, context);
                break;
            case SWITCH_ITEM:
                MaterialAboutSwitchItem.setupItem((MaterialAboutSwitchItem.MaterialAboutSwitchItemViewHolder) holder, (MaterialAboutSwitchItem) item, context);
                break;
        }
    }
}
