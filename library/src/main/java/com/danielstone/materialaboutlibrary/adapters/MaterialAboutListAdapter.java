package com.danielstone.materialaboutlibrary.adapters;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danielstone.materialaboutlibrary.R;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MaterialAboutListAdapter extends RecyclerView.Adapter<MaterialAboutListAdapter.MaterialAboutListViewHolder> {


    private final AsyncListDiffer<MaterialAboutCard> differ = new AsyncListDiffer<MaterialAboutCard>(this, DIFF_CALLBACK);

    private Context context;

    private ViewTypeManager viewTypeManager;

    private RecyclerView.RecycledViewPool viewPool;

    public MaterialAboutListAdapter() {
        setHasStableIds(true);
        this.viewTypeManager = new DefaultViewTypeManager();
        viewPool = new RecyclerView.RecycledViewPool();
    }

    public MaterialAboutListAdapter(ViewTypeManager customViewTypeManager) {
        setHasStableIds(true);
        this.viewTypeManager = customViewTypeManager;
        viewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public MaterialAboutListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        if (viewGroup instanceof RecyclerView) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mal_material_about_list_card, viewGroup, false);
            view.setFocusable(true);
            return new MaterialAboutListViewHolder(view, viewPool);
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    @Override
    public void onBindViewHolder(MaterialAboutListViewHolder holder, int position) {
        MaterialAboutCard card = differ.getCurrentList().get(position);

        if (holder.cardView instanceof CardView) {
            CardView cardView = (CardView) holder.cardView;
            int cardColor = card.getCardColor();
            if (cardColor != 0) {
                cardView.setCardBackgroundColor(cardColor);
            } else {
                cardView.setCardBackgroundColor(cardView.getCardBackgroundColor().getDefaultColor());
            }
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


        if (holder.cardView instanceof MaterialCardView) {
            MaterialCardView materialCardView = (MaterialCardView) holder.cardView;
            if (card.isOutline()) {
                materialCardView.setStrokeWidth((int) context.getResources().getDimension(R.dimen.mal_stroke_width));
                materialCardView.setCardElevation(0);
            } else {
                materialCardView.setStrokeWidth(0);
                materialCardView.setCardElevation(context.getResources().getDimension(R.dimen.mal_card_elevation));
            }
        }


        if (card.getCustomAdapter() != null) {
            holder.useCustomAdapter(card.getCustomAdapter());
        } else {
            holder.useMaterialAboutItemAdapter();
            ((MaterialAboutItemAdapter) holder.adapter).setData(card.getItems());
        }
    }

    @Override
    public long getItemId(int position) {
        return UUID.fromString(differ.getCurrentList().get(position).getId()).getMostSignificantBits() & Long.MAX_VALUE;
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public void setData(ArrayList<MaterialAboutCard> newData) {
        List<MaterialAboutCard> data = new ArrayList<>();
        for (MaterialAboutCard card : newData) {
            data.add(card.clone());
        }
        differ.submitList(data);
    }


    List<MaterialAboutCard> getData() {
        return differ.getCurrentList();
    }

    class MaterialAboutListViewHolder extends RecyclerView.ViewHolder {

        final View cardView;
        final TextView title;
        final RecyclerView recyclerView;
        final RecyclerView.RecycledViewPool viewPool;
        RecyclerView.Adapter adapter;

        MaterialAboutListViewHolder(View view, RecyclerView.RecycledViewPool viewPool) {
            super(view);
            this.viewPool = viewPool;
            cardView = view.findViewById(R.id.mal_list_card);
            title = (TextView) view.findViewById(R.id.mal_list_card_title);
            recyclerView = (RecyclerView) view.findViewById(R.id.mal_card_recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setNestedScrollingEnabled(false);
        }

        public void useMaterialAboutItemAdapter() {
            if (!(adapter instanceof MaterialAboutItemAdapter)) {
                adapter = new MaterialAboutItemAdapter(viewTypeManager);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setRecycledViewPool(viewPool);
                recyclerView.setAdapter(adapter);
            }
        }

        public void useCustomAdapter(RecyclerView.Adapter newAdapter) {
            if (adapter == null || !(adapter.getClass().isInstance(newAdapter))) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setRecycledViewPool(null);
                recyclerView.setAdapter(newAdapter);
            }
        }
    }


    public static final DiffUtil.ItemCallback<MaterialAboutCard> DIFF_CALLBACK = new DiffUtil.ItemCallback<MaterialAboutCard>() {
        @Override
        public boolean areItemsTheSame(MaterialAboutCard oldItem, MaterialAboutCard newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(MaterialAboutCard oldItem, MaterialAboutCard newItem) {
            boolean result;
            result = oldItem.toString().equals(newItem.toString());
            if (oldItem.getItems().size() != newItem.getItems().size()) return false;
            for (int i = 0; i < oldItem.getItems().size(); i++) {
                if (!oldItem.getItems().get(i).getDetailString().equals(newItem.getItems().get(i).getDetailString())) return false;
            }
            return result;
        }
    };
}
