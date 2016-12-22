package com.danielstone.materialaboutlibrary.model;


import android.support.annotation.StringRes;

import java.util.ArrayList;

public class MaterialAboutCard {

    private CharSequence mTitle;
    private int mTitleRes;

    private ArrayList<MaterialAboutItem> mItems;

    public MaterialAboutCard(Builder builder) {
        this.mTitle = builder.title;
        this.mTitleRes = builder.titleRes;
        this.mItems = builder.items;
    }

    public CharSequence getTitle() {
        return mTitle;
    }

    public int getTitleRes() {
        return mTitleRes;
    }

    public ArrayList<MaterialAboutItem> getItems() {
        return mItems;
    }

    public static class Builder {
        private CharSequence title = null;
        @StringRes
        private int titleRes = 0;
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

        public Builder addItem(MaterialAboutItem item) {
            this.items.add(item);
            return this;
        }

        public MaterialAboutCard build() {
            return new MaterialAboutCard(this);
        }
    }

}
