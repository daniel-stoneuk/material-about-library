package com.danielstone.materialaboutlibrary.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danielstone.materialaboutlibrary.R;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

import java.util.ArrayList;


public class MaterialAboutListAdapter extends RecyclerView.Adapter<MaterialAboutListAdapter.MaterialAboutListViewHolder> {

    private ArrayList<MaterialAboutCard> mData;

        Context context;

    public MaterialAboutListAdapter(MaterialAboutList list) {
            this.mData = list.getCards();
        }

        public class MaterialAboutListViewHolder extends RecyclerView.ViewHolder {

            public final TextView mTitle;
            public final RecyclerView mRecyclerView;
            public MaterialAboutItemAdapter mAdapter;

            public MaterialAboutListViewHolder(View view) {
                super(view);
                mTitle = (TextView) view.findViewById(R.id.mal_list_card_title);
                mRecyclerView = (RecyclerView) view.findViewById(R.id.mal_card_recyclerview);
                mAdapter = new MaterialAboutItemAdapter(new ArrayList<MaterialAboutItem>());
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setNestedScrollingEnabled(false);

            }


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

            CharSequence title = mData.get(position).getTitle();
            int titleRes = mData.get(position).getTitleRes();

            holder.mTitle.setVisibility(View.VISIBLE);
            if (title != null) {
                holder.mTitle.setText(title);
            } else if (titleRes != 0) {
                holder.mTitle.setText(titleRes);
            } else {
                holder.mTitle.setVisibility(View.GONE);
            }

            holder.mAdapter.swapData(mData.get(position).getItems());

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

    public void swapData(MaterialAboutList list) {
        mData = list.getCards();
        notifyDataSetChanged();
    }

    public ArrayList<MaterialAboutCard> getData() {
        return mData;
    }
}
