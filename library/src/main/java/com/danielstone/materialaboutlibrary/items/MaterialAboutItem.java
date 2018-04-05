package com.danielstone.materialaboutlibrary.items;

import java.util.UUID;

public abstract class MaterialAboutItem {

    public String id = "NO-UUID";

    public MaterialAboutItem() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public abstract int getType();

    public abstract String getDetailString();

    public abstract MaterialAboutItem clone();

}
