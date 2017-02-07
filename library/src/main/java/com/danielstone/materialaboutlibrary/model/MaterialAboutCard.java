package com.danielstone.materialaboutlibrary.model;


import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;

import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;

import java.util.ArrayList;
import java.util.Collections;

public class MaterialAboutCard {

    private CharSequence title = null;
    private int titleRes = 0;

    private int titleColor = 0;

    private ArrayList<MaterialAboutItem> mItems = new ArrayList<>();;

    private MaterialAboutCard(Builder builder) {
        this.title = builder.title;
        this.titleRes = builder.titleRes;
        this.titleColor = builder.titleColor;
        this.mItems = builder.items;
    }

    public MaterialAboutCard(CharSequence title, MaterialAboutItem... materialAboutItems) {
        this.title = title;
        Collections.addAll(mItems, materialAboutItems);
    }

    public MaterialAboutCard(int titleRes, MaterialAboutItem... materialAboutItems) {
        this.titleRes = titleRes;
        Collections.addAll(mItems, materialAboutItems);
    }

    public CharSequence getTitle() {
        return title;
    }

    public int getTitleRes() {
        return titleRes;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public ArrayList<MaterialAboutItem> getItems() {
        return mItems;
    }

    public static class Builder {
        private CharSequence title = null;
        @StringRes
        private int titleRes = 0;

        @ColorInt
        private int titleColor = 0;

        private ArrayList<MaterialAboutItem> items = new ArrayList<>();

        public Builder title(CharSequence title) {
            this.title = title;
            this.titleRes = 0;
            return this;
        }

        public Builder title(@StringRes int titleRes) {
            this.titleRes = titleRes;
            this.title = null;
            return this;
        }

        public Builder titleColor(@ColorInt int color) {
            this.titleColor = color;
            return this;
        }

        public Builder addItem(MaterialAboutItem item) {
            this.items.add(item);
            return this;
        }

        public MaterialAboutCard build() {
            return new MaterialAboutCard(this);
        }
    }

}
