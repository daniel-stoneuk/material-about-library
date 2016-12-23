package com.danielstone.materialaboutlibrary.model;


import java.util.ArrayList;

public class MaterialAboutList {

    private ArrayList<MaterialAboutCard> cards;

    private MaterialAboutList(Builder builder) {
        this.cards = builder.cards;
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
        return cards;
    }
}
