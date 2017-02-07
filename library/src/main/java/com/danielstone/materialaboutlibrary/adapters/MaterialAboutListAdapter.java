package com.danielstone.materialaboutlibrary.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danielstone.materialaboutlibrary.R;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

import java.util.ArrayList;


public class MaterialAboutListAdapter extends RecyclerView.Adapter<MaterialAboutListAdapter.MaterialAboutListViewHolder> {

    private ArrayList<MaterialAboutCard> data;

    private Context context;

    private ViewTypeManager viewTypeManager;

    public MaterialAboutListAdapter(MaterialAboutList list) {
        this.data = list.getCards();
        this.viewTypeManager = new DefaultViewTypeManager();
    }

    public MaterialAboutListAdapter(MaterialAboutList list, ViewTypeManager customViewTypeManager) {
        this.data = list.getCards();
        this.viewTypeManager = customViewTypeManager;
    }

    @Override
    public MaterialAboutListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        if (viewGroup instanceof RecyclerView) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mal_material_about_list_card, viewGroup, false);
            view.setFocusable(true);
            return new MaterialAboutListViewHolder(view);
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    @Override
    public void onBindViewHolder(MaterialAboutListViewHolder holder, int position) {

        CharSequence title = data.get(position).getTitle();
        int titleRes = data.get(position).getTitleRes();

        holder.title.setVisibility(View.VISIBLE);
        if (title != null) {
            holder.title.setText(title);
        } else if (titleRes != 0) {
            holder.title.setText(titleRes);
        } else {
            holder.title.setVisibility(View.GONE);
        }

        int color = data.get(position).getTitleColor();

        if (holder.title.getVisibility() == View.VISIBLE) {
            if (color != 0) {
                holder.title.setTextColor(color);
            } else {
                holder.title.setTextColor(holder.title.getTextColors().getDefaultColor());
            }
        }

        holder.adapter.swapData(data.get(position).getItems());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void swapData(MaterialAboutList list) {
        data = list.getCards();
        notifyDataSetChanged();
    }

    ArrayList<MaterialAboutCard> getData() {
        return data;
    }

    class MaterialAboutListViewHolder extends RecyclerView.ViewHolder {

        final TextView title;
        final RecyclerView recyclerView;
        MaterialAboutItemAdapter adapter;

        MaterialAboutListViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.mal_list_card_title);
            recyclerView = (RecyclerView) view.findViewById(R.id.mal_card_recyclerview);
            adapter = new MaterialAboutItemAdapter(new ArrayList<MaterialAboutItem>(), viewTypeManager);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);

        }
    }
}
