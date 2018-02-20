package com.danielstone.materialaboutlibrary.adapters;

import android.support.v7.util.DiffUtil;
import android.util.Log;

import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;

import java.util.List;

public class MaterialAboutListDiffUtilCallback extends DiffUtil.Callback {

    private static final String TAG = MaterialAboutListDiffUtilCallback.class.getSimpleName();
    List<MaterialAboutCard> oldList;
    List<MaterialAboutCard> newList;

    public MaterialAboutListDiffUtilCallback(List<MaterialAboutCard> oldList, List<MaterialAboutCard> newList) {
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
        MaterialAboutCard oldData = oldList.get(oldItemPosition);
        MaterialAboutCard newData = newList.get(newItemPosition);
        return oldData.getId().equals(newData.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        boolean result;
        MaterialAboutCard oldData = oldList.get(oldItemPosition);
        MaterialAboutCard newData = newList.get(newItemPosition);
        result = oldData.toString().equals(newData.toString());
        if (oldData.getItems().size() != newData.getItems().size()) return false;
        for (int i = 0; i < oldData.getItems().size(); i++) {
            if (!oldData.getItems().get(i).getDetailString().equals(newData.getItems().get(i).getDetailString())) return false;
        }
        return result;
    }
}
