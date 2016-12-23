package com.danielstone.materialaboutlibrary.model;


import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public class MaterialAboutTitleItem extends MaterialAboutItem {

    private CharSequence text;
    private int textRes;

    private Drawable icon;
    private int iconRes;

    public MaterialAboutTitleItem(MaterialAboutTitleItem.Builder builder) {
        this.text = builder.text;
        this.textRes = builder.textRes;

        this.icon = builder.icon;
        this.iconRes = builder.iconRes;

    }

    public CharSequence getText() {
        return text;
    }

    public int getTextRes() {
        return textRes;
    }

    public Drawable getIcon() {
        return icon;
    }

    public int getIconRes() {
        return iconRes;
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
