package com.danielstone.materialaboutlibrary.model;

public abstract class MaterialAboutItem {

    public abstract int getType();

    public static final class ItemType {
        public static final int ACTION_ITEM = 0;
        public static final int TITLE_ITEM = 1;
        public static final int PERSON_ITEM = 2;
        public static final int DEFAULT_ITEM = 3;
    }

}

