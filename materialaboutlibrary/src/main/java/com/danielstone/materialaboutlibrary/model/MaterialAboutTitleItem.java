package com.danielstone.materialaboutlibrary.model;


import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public class MaterialAboutTitleItem extends MaterialAboutItem {

    private CharSequence mText;
    private int mTextRes;

    private Drawable mIcon;
    private int mIconRes;

    public MaterialAboutTitleItem(MaterialAboutTitleItem.Builder builder) {
        this.mText = builder.text;
        this.mTextRes = builder.textRes;

        this.mIcon = builder.icon;
        this.mIconRes = builder.iconRes;

    }

    public CharSequence getText() {
        return mText;
    }

    public int getTextRes() {
        return mTextRes;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public int getIconRes() {
        return mIconRes;
    }


    public static class Builder {

        private CharSequence text = null;
        @StringRes
        private int textRes = 0;

        private Drawable icon = null;
        @DrawableRes
        private int iconRes = 0;

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
