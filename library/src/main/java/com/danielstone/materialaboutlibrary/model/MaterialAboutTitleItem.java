package com.danielstone.materialaboutlibrary.model;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielstone.materialaboutlibrary.R;
import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

import static android.view.View.GONE;

public class MaterialAboutTitleItem extends MaterialAboutItem {


    private CharSequence text = null;
    private int textRes = 0;

    private Drawable icon = null;
    private int iconRes = 0;

    @Override
    public int getType() {
        return ViewTypeManager.ItemType.TITLE_ITEM;
    }

    public static MaterialAboutItemViewHolder getViewHolder(View view) {
        return new MaterialAboutTitleItem.MaterialAboutTitleItemViewHolder(view);
    }

    public static class MaterialAboutTitleItemViewHolder extends MaterialAboutItemViewHolder {
        public final View view;
        public final ImageView icon;
        public final TextView text;

        MaterialAboutTitleItemViewHolder(View view) {
            super(view);
            this.view = view;
            icon = (ImageView) view.findViewById(R.id.mal_item_image);
            text = (TextView) view.findViewById(R.id.mal_item_text);
        }
    }


    private MaterialAboutTitleItem(MaterialAboutTitleItem.Builder builder) {
        this.text = builder.text;
        this.textRes = builder.textRes;

        this.icon = builder.icon;
        this.iconRes = builder.iconRes;

    }

    public MaterialAboutTitleItem(CharSequence text, Drawable icon) {
        this.text = text;
        this.icon = icon;
    }

    public MaterialAboutTitleItem(int textRes, int iconRes) {
        this.textRes = textRes;
        this.iconRes = iconRes;
    }

    public CharSequence getText() {
        return text;
    }

    public MaterialAboutTitleItem setText(CharSequence text) {
        this.textRes = 0;
        this.text = text;
        return this;
    }

    public int getTextRes() {
        return textRes;
    }

    public MaterialAboutTitleItem setTextRes(int textRes) {
        this.text = null;
        this.textRes = textRes;
        return this;
    }
    public Drawable getIcon() {
        return icon;
    }

    public MaterialAboutTitleItem setIcon(Drawable icon) {
        this.iconRes = 0;
        this.icon = icon;
        return this;
    }

    public int getIconRes() {
        return iconRes;
    }

    public MaterialAboutTitleItem setIconRes(int iconRes) {
        this.icon = null;
        this.iconRes = iconRes;
        return this;
    }
    public static void setupItem(MaterialAboutTitleItemViewHolder holder, MaterialAboutTitleItem item, Context c) {

        CharSequence text = item.getText();
        int textRes = item.getTextRes();

        holder.text.setVisibility(View.VISIBLE);
        if (text != null) {
            holder.text.setText(text);
        } else if (textRes != 0) {
            holder.text.setText(textRes);
        } else {
            holder.text.setVisibility(GONE);
        }

        Drawable drawable = item.getIcon();
        int drawableRes = item.getIconRes();
        if (drawable != null) {
            holder.icon.setImageDrawable(drawable);
        } else if (drawableRes != 0) {
            holder.icon.setImageResource(drawableRes);
        }

        holder.view.setSoundEffectsEnabled(false);
    }

    public static class Builder {

        private CharSequence text = null;
        @StringRes
        private int textRes = 0;

        private Drawable icon = null;
        @DrawableRes
        private int iconRes = 0;

        @LayoutRes
        private int layoutRes;

        public MaterialAboutTitleItem.Builder text(CharSequence text) {
            this.text = text;
            this.textRes = 0;
            return this;
        }


        public MaterialAboutTitleItem.Builder text(@StringRes int text) {
            this.textRes = text;
            this.text = null;
            return this;
        }

        public MaterialAboutTitleItem.Builder icon(Drawable icon) {
            this.icon = icon;
            this.iconRes = 0;
            return this;
        }


        public MaterialAboutTitleItem.Builder icon(@DrawableRes int iconRes) {
            this.icon = null;
            this.iconRes = iconRes;
            return this;
        }

        public MaterialAboutTitleItem build() {
            return new MaterialAboutTitleItem(this);
        }
    }
}
