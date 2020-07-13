package com.danielstone.materialaboutlibrary.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MaterialAboutItemAdapter extends RecyclerView.Adapter<MaterialAboutItemViewHolder> {

    private final AsyncListDiffer<MaterialAboutItem> differ = new AsyncListDiffer<MaterialAboutItem>(this, DIFF_CALLBACK);

    private ViewTypeManager viewTypeManager;

    private Context context;

    MaterialAboutItemAdapter() {
        setHasStableIds(true);
        this.viewTypeManager = new DefaultViewTypeManager();
    }

    MaterialAboutItemAdapter(ViewTypeManager customViewTypeManager) {
        setHasStableIds(true);
        this.viewTypeManager = customViewTypeManager;
    }

    @NonNull
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
        viewTypeManager.setupItem(getItemViewType(position), holder, differ.getCurrentList().get(position), context);
    }


    @Override
    public long getItemId(int position) {
        return UUID.fromString(differ.getCurrentList().get(position).getId()).getMostSignificantBits() & Long.MAX_VALUE;
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    @Override
    public int getItemViewType(int position) {
        return differ.getCurrentList().get(position).getType();
    }

    public void setData(ArrayList<MaterialAboutItem> newData) {
        List<MaterialAboutItem> data = new ArrayList<>();
        for (MaterialAboutItem item : newData) {
            data.add(item.clone());
        }
        differ.submitList(data);
    }

    public List<MaterialAboutItem> getData() {
        return differ.getCurrentList();
    }


    public static final DiffUtil.ItemCallback<MaterialAboutItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<MaterialAboutItem>() {
        @Override
        public boolean areItemsTheSame(MaterialAboutItem oldItem, MaterialAboutItem newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(MaterialAboutItem oldItem, MaterialAboutItem newItem) {
            return oldItem.getDetailString().equals(newItem.getDetailString());
        }
    };

}
