package com.danielstone.materialaboutlibrary.model;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public class MaterialAboutActionItem extends MaterialAboutItem {

    public interface OnClickListener {
        void onClick();
    }

    private CharSequence text = null;
    private int textRes = 0;

    private CharSequence subText = null;
    private int subTextRes = 0;

    private Drawable icon = null;
    private int iconRes = 0;
    private boolean showIcon = true;

    private MaterialAboutActionItem.OnClickListener onClickListener = null;

    @Override
    public int getType() {
        return ItemType.ACTION_ITEM;
    }

    private MaterialAboutActionItem(Builder builder) {
        this.text = builder.text;
        this.textRes = builder.textRes;

        this.subText = builder.subText;
        this.subTextRes = builder.subTextRes;

        this.icon = builder.icon;
        this.iconRes = builder.iconRes;

        this.showIcon = builder.showIcon;

        this.onClickListener = builder.onClickListener;
    }

    public MaterialAboutActionItem(CharSequence text, CharSequence subText, Drawable icon, OnClickListener onClickListener) {
        this.text = text;
        this.subText = subText;
        this.icon = icon;
        this.onClickListener = onClickListener;
    }

    public MaterialAboutActionItem(CharSequence text, CharSequence subText, Drawable icon) {
        this.text = text;
        this.subText = subText;
        this.icon = icon;
    }

    public MaterialAboutActionItem(int textRes, int subTextRes, int iconRes, OnClickListener onClickListener) {
        this.textRes = textRes;
        this.subTextRes = subTextRes;
        this.iconRes = iconRes;
        this.onClickListener = onClickListener;
    }

    public MaterialAboutActionItem(int textRes, int subTextRes, int iconRes) {
        this.textRes = textRes;
        this.subTextRes = subTextRes;
        this.iconRes = iconRes;
    }

    public CharSequence getText() {
        return text;
    }

    public int getTextRes() {
        return textRes;
    }

    public CharSequence getSubText() {
        return subText;
    }

    public int getSubTextRes() {
        return subTextRes;
    }

    public Drawable getIcon() {
        return icon;
    }

    public int getIconRes() {
        return iconRes;
    }

    public boolean shouldShowIcon() {
        return showIcon;
    }

    public MaterialAboutActionItem.OnClickListener getOnClickListener() {
        return onClickListener;
    }


    public static class Builder {

        private CharSequence text = null;
        @StringRes
        private int textRes = 0;

        private CharSequence subText = null;
        @StringRes
        private int subTextRes = 0;

        private Drawable icon = null;
        @DrawableRes
        private int iconRes = 0;

        private boolean showIcon = true;

        MaterialAboutActionItem.OnClickListener onClickListener;

        public Builder text(CharSequence text) {
            this.text = text;
            this.textRes = 0;
            return this;
        }

        public Builder text(@StringRes int text) {
            this.textRes = text;
            this.text = null;
            return this;
        }

        public Builder subText(CharSequence subText) {
            this.subText = subText;
            this.subTextRes = 0;
            return this;
        }

        public Builder subText(@StringRes int subTextRes) {
            this.subText = null;
            this.subTextRes = subTextRes;
            return this;
        }

        public Builder icon(Drawable icon) {
            this.icon = icon;
            this.iconRes = 0;
            return this;
        }

        public Builder icon(@DrawableRes int iconRes) {
            this.icon = null;
            this.iconRes = iconRes;
            return this;
        }

        public Builder showIcon(boolean showIcon) {
            this.showIcon = showIcon;
            return this;
        }

        public Builder setOnClickListener(MaterialAboutActionItem.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public MaterialAboutActionItem build() {
            return new MaterialAboutActionItem(this);
        }
    }
}
