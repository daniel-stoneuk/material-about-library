package com.danielstone.materialaboutlibrarydemo.custom;

import android.content.Context;
import android.view.View;

import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionCheckBoxItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionSwitchItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutCheckBoxItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutSwitchItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;
import com.danielstone.materialaboutlibrarydemo.R;

import static com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager.ItemType.CHECKBOX_ITEM;
import static com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager.ItemType.SWITCH_ITEM;
import static com.danielstone.materialaboutlibrarydemo.custom.MyViewTypeManager.ItemLayout.*;
import static com.danielstone.materialaboutlibrarydemo.custom.MyViewTypeManager.ItemType.*;

public class MyViewTypeManager extends ViewTypeManager {

    public static final class ItemType {
        public static final int ACTION_ITEM = ViewTypeManager.ItemType.ACTION_ITEM;
        public static final int TITLE_ITEM = ViewTypeManager.ItemType.TITLE_ITEM;
        public static final int CHECKBOX_ITEM = ViewTypeManager.ItemType.CHECKBOX_ITEM;
        public static final int SWITCH_ITEM = ViewTypeManager.ItemType.SWITCH_ITEM;
        public static final int ACTION_CHECKBOX_ITEM = ViewTypeManager.ItemType.ACTION_CHECKBOX_ITEM;
        public static final int ACTION_SWITCH_ITEM = ViewTypeManager.ItemType.ACTION_SWITCH_ITEM;
        public static final int CUSTOM_ITEM = 10;
    }

    public static final class ItemLayout {
        public static final int ACTION_LAYOUT = ViewTypeManager.ItemLayout.ACTION_LAYOUT;
        public static final int TITLE_LAYOUT = ViewTypeManager.ItemLayout.TITLE_LAYOUT;
        public static final int CHECKBOX_LAYOUT = ViewTypeManager.ItemLayout.CHECKBOX_LAYOUT;
        public static final int SWITCH_LAYOUT = ViewTypeManager.ItemLayout.SWITCH_LAYOUT;
        public static final int ACTION_CHECKBOX_LAYOUT = ViewTypeManager.ItemLayout.ACTION_CHECKBOX_LAYOUT;
        public static final int ACTION_SWITCH_LAYOUT = ViewTypeManager.ItemLayout.ACTION_SWITCH_LAYOUT;
        public static final int CUSTOM_LAYOUT = R.layout.custom_item;
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
            case ACTION_CHECKBOX_ITEM:
                return ACTION_CHECKBOX_LAYOUT;
            case ACTION_SWITCH_ITEM:
                return ACTION_SWITCH_LAYOUT;
            case CUSTOM_ITEM:
                return CUSTOM_LAYOUT;
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
            case ACTION_CHECKBOX_ITEM:
                return MaterialAboutActionCheckBoxItem.getViewHolder(view);
            case ACTION_SWITCH_ITEM:
                return MaterialAboutActionSwitchItem.getViewHolder(view);
            case CUSTOM_ITEM:
                return MyCustomItem.getViewHolder(view);
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
            case CUSTOM_ITEM:
                MyCustomItem.setupItem((MyCustomItem.MyCustomItemViewHolder) holder, (MyCustomItem) item, context);
                break;
            case CHECKBOX_ITEM:
                MaterialAboutCheckBoxItem.setupItem((MaterialAboutCheckBoxItem.MaterialAboutCheckBoxItemViewHolder) holder, (MaterialAboutCheckBoxItem) item, context);
                break;
            case SWITCH_ITEM:
                MaterialAboutSwitchItem.setupItem((MaterialAboutSwitchItem.MaterialAboutSwitchItemViewHolder) holder, (MaterialAboutSwitchItem) item, context);
                break;
            case ACTION_CHECKBOX_ITEM:
                MaterialAboutActionCheckBoxItem.setupItem((MaterialAboutActionCheckBoxItem.MaterialAboutActionCheckBoxItemViewHolder) holder, (MaterialAboutActionCheckBoxItem) item, context);
                break;
            case ACTION_SWITCH_ITEM:
                MaterialAboutActionSwitchItem.setupItem((MaterialAboutActionSwitchItem.MaterialAboutActionSwitchItemViewHolder) holder, (MaterialAboutActionSwitchItem) item, context);
                break;
        }
    }
}
