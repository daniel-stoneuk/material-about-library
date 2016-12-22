package com.danielstone.materialaboutlibrary.model;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public class MaterialAboutActionItem extends MaterialAboutItem {

    private CharSequence mText;
    private int mTextRes;

    private CharSequence mSubText;
    private int mSubTextRes;

    private Drawable mIcon;
    private int mIconRes;

    private MaterialAboutItem.OnClickListener mClickListener;

    public MaterialAboutActionItem(Builder builder) {
        this.mText = builder.text;
        this.mTextRes = builder.textRes;

        this.mSubText = builder.subText;
        this.mSubTextRes = builder.subTextRes;

        this.mIcon = builder.icon;
        this.mIconRes = builder.iconRes;

        this.mClickListener = builder.onClickListener;
    }

    public CharSequence getText() {
        return mText;
    }

    public int getTextRes() {
        return mTextRes;
    }

    public CharSequence getSubText() {
        return mSubText;
    }

    public int getSubTextRes() {
        return mSubTextRes;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public int getIconRes() {
        return mIconRes;
    }

    public MaterialAboutItem.OnClickListener getOnClickListener() {
        return mClickListener;
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

        OnClickListener onClickListener;

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

        public Builder setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public MaterialAboutActionItem build() {
            return new MaterialAboutActionItem(this);
        }
    }
}
