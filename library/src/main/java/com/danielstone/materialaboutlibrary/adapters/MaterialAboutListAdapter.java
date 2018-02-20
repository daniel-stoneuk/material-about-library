package com.danielstone.materialaboutlibrary.adapters;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
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
import java.util.Collections;
import java.util.UUID;


public class MaterialAboutListAdapter extends RecyclerView.Adapter<MaterialAboutListAdapter.MaterialAboutListViewHolder> {

    private ArrayList<MaterialAboutCard> data = new ArrayList<>();

    private Context context;

    private ViewTypeManager viewTypeManager;

    public MaterialAboutListAdapter(MaterialAboutList list) {
        setHasStableIds(true);
        data.clear();
        data.addAll(list.getCards());
        this.viewTypeManager = new DefaultViewTypeManager();
    }

    public MaterialAboutListAdapter(MaterialAboutList list, ViewTypeManager customViewTypeManager) {
        setHasStableIds(true);
        data.clear();
        data.addAll(list.getCards());
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
        MaterialAboutCard card = data.get(position);

        int cardColor = card.getCardColor();
        if (cardColor != 0) {
            holder.cardView.setBackgroundColor(cardColor);
        } else {
            holder.cardView.setBackgroundColor(holder.cardView.getCardBackgroundColor().getDefaultColor());
        }

        CharSequence title = card.getTitle();
        int titleRes = card.getTitleRes();

        holder.title.setVisibility(View.VISIBLE);
        if (title != null) {
            holder.title.setText(title);
        } else if (titleRes != 0) {
            holder.title.setText(titleRes);
        } else {
            holder.title.setVisibility(View.GONE);
        }

        int titleColor = card.getTitleColor();

        if (holder.title.getVisibility() == View.VISIBLE) {
            if (titleColor != 0) {
                holder.title.setTextColor(titleColor);
            } else {
                holder.title.setTextColor(holder.title.getTextColors().getDefaultColor());
            }
        }

        holder.adapter.setData(card.getItems());
    }

    @Override
    public long getItemId(int position) {
        return UUID.fromString(data.get(position).getId()).getMostSignificantBits() & Long.MAX_VALUE;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<MaterialAboutCard> newData) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MaterialAboutListDiffUtilCallback(this.data, newData));
        data.clear();
        for (MaterialAboutCard card : newData) {
            data.add(card.clone());
        }
        diffResult.dispatchUpdatesTo(this);
    }


    ArrayList<MaterialAboutCard> getData() {
        return data;
    }

    class MaterialAboutListViewHolder extends RecyclerView.ViewHolder {

        final CardView cardView;
        final TextView title;
        final RecyclerView recyclerView;
        MaterialAboutItemAdapter adapter;

        MaterialAboutListViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.mal_list_card);
            title = (TextView) view.findViewById(R.id.mal_list_card_title);
            recyclerView = (RecyclerView) view.findViewById(R.id.mal_card_recyclerview);
            adapter = new MaterialAboutItemAdapter(new ArrayList<MaterialAboutItem>(), viewTypeManager);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);
        }
    }
}
