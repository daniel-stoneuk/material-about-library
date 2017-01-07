package com.danielstone.materialaboutlibrary.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
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

    private static final String TAG = MaterialAboutItemAdapter.class.getSimpleName();

    private ArrayList<MaterialAboutItem> data;

    private Context context;

    MaterialAboutItemAdapter(ArrayList<MaterialAboutItem> data) {
        this.data = data;
    }

    public class MaterialAboutItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final View view;
        private final ImageView icon;
        private final TextView text;
        private final TextView subText;
        private int viewType;
        MaterialAboutActionItem.OnClickListener onClickListener;

        MaterialAboutItemViewHolder(View view, int viewType) {
            super(view);
            this.view = view;
            icon = (ImageView) view.findViewById(R.id.mal_item_image);
            text = (TextView) view.findViewById(R.id.mal_item_text);
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
        if (!(viewGroup instanceof RecyclerView)) {
            throw new RuntimeException("Not bound to RecyclerView");
        }

        int layoutId = -1;
        switch (viewType) {
            case MaterialAboutItem.ItemType.ACTION_ITEM: {
                layoutId = R.layout.mal_material_about_action_item;
                break;
            }
            case MaterialAboutItem.ItemType.TITLE_ITEM: {
                layoutId = R.layout.mal_material_about_title_item;
                break;
            }
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
        view.setFocusable(true);
        return new MaterialAboutItemViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(MaterialAboutItemViewHolder holder, int position) {
        switch (holder.viewType) {
            case MaterialAboutItem.ItemType.ACTION_ITEM: {
                setupActionItem(holder, position);
                break;
            }
            case MaterialAboutItem.ItemType.TITLE_ITEM: {
                setupTitleItem(holder, position);
                break;
            }
        }
    }

    private void setupActionItem(MaterialAboutItemViewHolder holder, int position) {
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

        int pL = 0, pT = 0, pR = 0, pB = 0;
        if (Build.VERSION.SDK_INT < 21) {
            pL = holder.view.getPaddingLeft();
            pT = holder.view.getPaddingTop();
            pR = holder.view.getPaddingRight();
            pB = holder.view.getPaddingBottom();
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

        if (Build.VERSION.SDK_INT < 21) {
            holder.view.setPadding(pL, pT, pR, pB);
        }
    }

    private void setupTitleItem(MaterialAboutItemViewHolder holder, int position) {
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
