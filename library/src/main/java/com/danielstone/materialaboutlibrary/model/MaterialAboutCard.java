package com.danielstone.materialaboutlibrary.model;


import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;

import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class MaterialAboutCard {

    private String id = "NO-UUID";

    private CharSequence title = null;
    private int titleRes = 0;

    private int titleColor = 0;
    private int cardColor = 0;

    private ArrayList<MaterialAboutItem> items = new ArrayList<>();
    ;

    private MaterialAboutCard(Builder builder) {
        this.id = UUID.randomUUID().toString();
        this.title = builder.title;
        this.titleRes = builder.titleRes;
        this.titleColor = builder.titleColor;
        this.cardColor = builder.cardColor;
        this.items = builder.items;
    }

    public MaterialAboutCard(CharSequence title, MaterialAboutItem... materialAboutItems) {
        this.title = title;
        Collections.addAll(items, materialAboutItems);
    }

    public MaterialAboutCard(int titleRes, MaterialAboutItem... materialAboutItems) {
        this.titleRes = titleRes;
        Collections.addAll(items, materialAboutItems);
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

    public int getCardColor() {
        return cardColor;
    }

    public ArrayList<MaterialAboutItem> getItems() {
        return items;
    }

    public static class Builder {
        private CharSequence title = null;
        @StringRes
        private int titleRes = 0;

        @ColorInt
        private int titleColor = 0;

        @ColorInt
        private int cardColor = 0;

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

        public Builder cardColor(@ColorInt int cardColor) {
            this.cardColor = cardColor;
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

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        String result = "MaterialAboutCard{" +
                "id='" + id + '\'' +
                ", title=" + title +
                ", titleRes=" + titleRes +
                ", titleColor=" + titleColor +
                ", cardColor=" + cardColor + '}';
        return result;
    }

    public MaterialAboutCard(MaterialAboutCard card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.titleRes = card.getTitleRes();
        this.titleColor = card.getTitleColor();
        this.cardColor = card.getCardColor();
        this.items = new ArrayList<>();
        for (MaterialAboutItem item : card.items) {
            this.items.add(item.clone());
        }
    }

    public MaterialAboutCard clone() {
        return new MaterialAboutCard(this);
    }

}
