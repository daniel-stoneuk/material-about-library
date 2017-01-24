package com.danielstone.materialaboutlibrary.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

import java.util.ArrayList;


public class MaterialAboutItemAdapter extends RecyclerView.Adapter<MaterialAboutItemViewHolder> {

    private static final String TAG = MaterialAboutItemAdapter.class.getSimpleName();

    private ArrayList<MaterialAboutItem> data;

    private ViewTypeManager viewTypeManager;

    private Context context;

    MaterialAboutItemAdapter(ArrayList<MaterialAboutItem> data) {
        this.data = data;
        this.viewTypeManager = new DefaultViewTypeManager();
    }

    MaterialAboutItemAdapter(ArrayList<MaterialAboutItem> data, ViewTypeManager customViewTypeManager) {
        this.data = data;
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
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    public void swapData(ArrayList<MaterialAboutItem> newData) {
        data = newData;
        notifyDataSetChanged();
    }

    public ArrayList<MaterialAboutItem> getData() {
        return data;
    }
}
