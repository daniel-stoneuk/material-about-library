package com.danielstone.materialaboutlibrary.adapters;

import android.support.v7.util.DiffUtil;
import android.util.Log;

import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;

import java.util.List;

public class MaterialAboutItemDiffUtilCallback extends DiffUtil.Callback {

    private static final String TAG = MaterialAboutItemDiffUtilCallback.class.getSimpleName();
    List<MaterialAboutItem> oldList;
    List<MaterialAboutItem> newList;

    public MaterialAboutItemDiffUtilCallback(List<MaterialAboutItem> oldList, List<MaterialAboutItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }


    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        MaterialAboutItem oldData = oldList.get(oldItemPosition);
        MaterialAboutItem newData = newList.get(newItemPosition);
        return oldData.getId().equals(newData.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        MaterialAboutItem oldData = oldList.get(oldItemPosition);
        MaterialAboutItem newData = newList.get(newItemPosition);
        return oldData.getDetailString().equals(newData.getDetailString());
    }
}
