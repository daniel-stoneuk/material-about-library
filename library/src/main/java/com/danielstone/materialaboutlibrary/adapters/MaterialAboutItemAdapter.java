package com.danielstone.materialaboutlibrary.adapters;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;


public class MaterialAboutItemAdapter extends RecyclerView.Adapter<MaterialAboutItemViewHolder> {

    private static final String TAG = MaterialAboutItemAdapter.class.getSimpleName();

    private ArrayList<MaterialAboutItem> data = new ArrayList<>();

    private ViewTypeManager viewTypeManager;

    private Context context;

    MaterialAboutItemAdapter(ArrayList<MaterialAboutItem> data) {
        setHasStableIds(true);
        this.data.clear();
        this.data.addAll(data);
        this.viewTypeManager = new DefaultViewTypeManager();
    }

    MaterialAboutItemAdapter(ArrayList<MaterialAboutItem> data, ViewTypeManager customViewTypeManager) {
        setHasStableIds(true);
        this.data.clear();
        this.data.addAll(data);
        this.viewTypeManager = customViewTypeManager;
    }

    @Override
    public MaterialAboutItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        if (!(viewGroup instanceof RecyclerView)) {
            throw new RuntimeException("Not bound to RecyclerView");
        }

        int layoutId = viewTypeManager.getLayout(viewType);

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
        view.setFocusable(true);

        return viewTypeManager.getViewHolder(viewType, view);
    }

    @Override
    public void onBindViewHolder(MaterialAboutItemViewHolder holder, int position) {
        viewTypeManager.setupItem(getItemViewType(position), holder, data.get(position), context);
    }


    @Override
    public long getItemId(int position) {
        return UUID.fromString(data.get(position).getId()).getMostSignificantBits() & Long.MAX_VALUE;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    public void setData(ArrayList<MaterialAboutItem> newData) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MaterialAboutItemDiffUtilCallback(this.data, newData));
        data.clear();
        data.addAll(newData);
        diffResult.dispatchUpdatesTo(this);
    }

    public ArrayList<MaterialAboutItem> getData() {
        return data;
    }
}
