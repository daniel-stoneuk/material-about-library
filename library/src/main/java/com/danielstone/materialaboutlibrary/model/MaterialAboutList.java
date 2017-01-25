package com.danielstone.materialaboutlibrary.model;


import java.util.ArrayList;
import java.util.Collections;

public class MaterialAboutList {

    private ArrayList<MaterialAboutCard> cards = new ArrayList<>();

    private MaterialAboutList(Builder builder) {
        this.cards = builder.cards;
    }

    public MaterialAboutList(MaterialAboutCard... materialAboutCards) {
        Collections.addAll(cards, materialAboutCards);
    }

    public MaterialAboutList addCard(MaterialAboutCard card) {
        cards.add(card);
        return this;
    }

    public MaterialAboutList clearCards(MaterialAboutCard card) {
        cards.clear();
        return this;
    }

    public ArrayList<MaterialAboutCard> getCards() {
        return cards;
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
}
