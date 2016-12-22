package com.danielstone.materialaboutlibrary.model;


import java.util.ArrayList;

public class MaterialAboutList {

    private ArrayList<MaterialAboutCard> mCards;

    public MaterialAboutList(Builder builder) {
        this.mCards = builder.cards;
    }

    public static class Builder {
        private ArrayList<MaterialAboutCard> cards = new ArrayList<>();

        public Builder addCard(MaterialAboutCard card) {
            this.cards.add(card);
            return this;
        }

        public MaterialAboutList build() {
            return new MaterialAboutList(this);
        }
    }

    public ArrayList<MaterialAboutCard> getCards() {
        return mCards;
    }
}
