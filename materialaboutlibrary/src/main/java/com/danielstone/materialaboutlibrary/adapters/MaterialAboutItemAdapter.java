package com.danielstone.materialaboutlibrary.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielstone.materialaboutlibrary.R;
import com.danielstone.materialaboutlibrary.model.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutTitleItem;

import java.util.ArrayList;


public class MaterialAboutItemAdapter extends RecyclerView.Adapter<MaterialAboutItemAdapter.MaterialAboutItemViewHolder> {

    public static final String TAG = MaterialAboutItemAdapter.class.getSimpleName();

    public static final int VIEW_TYPE_ACTION_ITEM = 0;
    public static final int VIEW_TYPE_TITLE_ITEM = 1;
    public static final int VIEW_TYPE_PERSON_ITEM = 2;

    private ArrayList<MaterialAboutItem> data;

    Context context;

    public MaterialAboutItemAdapter(ArrayList<MaterialAboutItem> data) {
        this.data = data;
    }

    public class MaterialAboutItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final View view;
        private final ImageView icon;
        private final TextView text;
        private final TextView subText;
        private int viewType;
        public MaterialAboutActionItem.OnClickListener onClickListener;

        public MaterialAboutItemViewHolder(View view, int viewType) {
            super(view);
            this.view = view;
            icon = (ImageView) view.findViewById(R.id.mal_action_item_image);
            text = (TextView) view.findViewById(R.id.mal_action_item_text);
            subText = (TextView) view.findViewById(R.id.mal_action_item_subtext);
            this.viewType = viewType;

            view.setOnClickListener(this);
            onClickListener = null;
        }

        @Override
        public void onClick(View v) {
            if (onClickListener != null) {
                onClickListener.onClick();
            }
        }
    }

    @Override
    public MaterialAboutItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        if (viewGroup instanceof RecyclerView) {
            int layoutId = -1;
            switch (viewType) {
                case VIEW_TYPE_ACTION_ITEM: {
                    layoutId = R.layout.mal_material_about_action_item;
                    break;
                }
                case VIEW_TYPE_TITLE_ITEM: {
                    layoutId = R.layout.mal_material_about_title_item;
                    break;
                }
            }
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
            view.setFocusable(true);
            return new MaterialAboutItemViewHolder(view, viewType);
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    @Override
    public void onBindViewHolder(MaterialAboutItemViewHolder holder, int position) {
        switch (holder.viewType) {
            case VIEW_TYPE_ACTION_ITEM: {
                if (data.get(position) instanceof MaterialAboutActionItem) {

                    MaterialAboutActionItem item = (MaterialAboutActionItem) data.get(position);

                    CharSequence text = item.getText();
                    int textRes = item.getTextRes();

                    holder.text.setVisibility(View.VISIBLE);
                    if (text != null) {
                        holder.text.setText(text);
                    } else if (textRes != 0) {
                        holder.text.setText(textRes);
                    } else {
                        holder.text.setVisibility(View.GONE);
                    }

                    CharSequence subText = item.getSubText();
                    int subTextRes = item.getSubTextRes();

                    holder.subText.setVisibility(View.VISIBLE);
                    if (subText != null) {
                        holder.subText.setText(subText);
                    } else if (subTextRes != 0) {
                        holder.subText.setText(subTextRes);
                    } else {
                        holder.subText.setVisibility(View.GONE);
                    }

                    Drawable drawable = item.getIcon();
                    int drawableRes = item.getIconRes();
                    if (drawable != null) {
                        holder.icon.setImageDrawable(drawable);
                    } else if (drawableRes != 0) {
                        holder.icon.setImageResource(drawableRes);
                    }

                    if (item.getOnClickListener() != null) {
                            TypedValue outValue = new TypedValue();
                            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
                            holder.view.setBackgroundResource(outValue.resourceId);
                            holder.onClickListener = item.getOnClickListener();
                    } else {
                        TypedValue outValue = new TypedValue();
                        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, false);
                        holder.view.setBackgroundResource(outValue.resourceId);
                        holder.onClickListener = item.getOnClickListener();
                        holder.onClickListener = null;
                    }

                }
                break;
            }
            case VIEW_TYPE_TITLE_ITEM: {
                MaterialAboutTitleItem item = (MaterialAboutTitleItem) data.get(position);

                CharSequence text = item.getText();
                int textRes = item.getTextRes();

                holder.text.setVisibility(View.VISIBLE);
                if (text != null) {
                    holder.text.setText(text);
                } else if (textRes != 0) {
                    holder.text.setText(textRes);
                } else {
                    holder.text.setVisibility(View.GONE);
                }

                Drawable drawable = item.getIcon();
                int drawableRes = item.getIconRes();
                if (drawable != null) {
                    holder.icon.setImageDrawable(drawable);
                } else if (drawableRes != 0) {
                    holder.icon.setImageResource(drawableRes);
                }

                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (data.get(position) instanceof MaterialAboutActionItem) {
            return VIEW_TYPE_ACTION_ITEM;
        } else if (data.get(position) instanceof MaterialAboutTitleItem) {
            return VIEW_TYPE_TITLE_ITEM;
        }
        Log.i(TAG, "getItemViewType: That didn't work oops");
        return VIEW_TYPE_ACTION_ITEM;
    }

    public void swapData(ArrayList<MaterialAboutItem> newData) {
        data = newData;
        notifyDataSetChanged();
    }

    public ArrayList<MaterialAboutItem> getData() {
        return data;
    }
}
