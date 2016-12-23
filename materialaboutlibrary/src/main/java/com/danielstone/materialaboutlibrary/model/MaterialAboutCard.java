package com.danielstone.materialaboutlibrary.model;


import android.support.annotation.StringRes;

import java.util.ArrayList;

public class MaterialAboutCard {

    private CharSequence title;
    private int titleRes;

    private ArrayList<MaterialAboutItem> mItems;

    private MaterialAboutCard(Builder builder) {
        this.title = builder.title;
        this.titleRes = builder.titleRes;
        this.mItems = builder.items;
    }

    public CharSequence getTitle() {
        return title;
    }

    public int getTitleRes() {
        return titleRes;
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
