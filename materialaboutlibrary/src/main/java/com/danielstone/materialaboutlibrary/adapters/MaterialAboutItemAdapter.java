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

    private ArrayList<MaterialAboutItem> mData;

    Context context;

    public MaterialAboutItemAdapter(ArrayList<MaterialAboutItem> data) {
        this.mData = data;
    }

    public class MaterialAboutItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final View mView;
        private final ImageView mIcon;
        private final TextView mText;
        private final TextView mSubText;
        private int viewType;
        public MaterialAboutActionItem.OnClickListener mOnClickListener;

        public MaterialAboutItemViewHolder(View view, int viewType) {
            super(view);
            mView = view;
            mIcon = (ImageView) view.findViewById(R.id.mal_action_item_image);
            mText = (TextView) view.findViewById(R.id.mal_action_item_text);
            mSubText = (TextView) view.findViewById(R.id.mal_action_item_subtext);
            this.viewType = viewType;

            view.setOnClickListener(this);
            mOnClickListener = null;
        }

        @Override
        public void onClick(View v) {
            if (mOnClickListener != null) {
                mOnClickListener.onClick();
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
                if (mData.get(position) instanceof MaterialAboutActionItem) {

                    MaterialAboutActionItem item = (MaterialAboutActionItem) mData.get(position);

                    CharSequence text = item.getText();
                    int textRes = item.getTextRes();

                    holder.mText.setVisibility(View.VISIBLE);
                    if (text != null) {
                        holder.mText.setText(text);
                    } else if (textRes != 0) {
                        holder.mText.setText(textRes);
                    } else {
                        holder.mText.setVisibility(View.GONE);
                    }

                    CharSequence subText = item.getSubText();
                    int subTextRes = item.getSubTextRes();

                    holder.mSubText.setVisibility(View.VISIBLE);
                    if (subText != null) {
                        holder.mSubText.setText(subText);
                    } else if (subTextRes != 0) {
                        holder.mSubText.setText(subTextRes);
                    } else {
                        holder.mSubText.setVisibility(View.GONE);
                    }

                    Drawable drawable = item.getIcon();
                    int drawableRes = item.getIconRes();
                    if (drawable != null) {
                        holder.mIcon.setImageDrawable(drawable);
                    } else if (drawableRes != 0) {
                        holder.mIcon.setImageResource(drawableRes);
                    }

                    if (item.getOnClickListener() != null) {
                            TypedValue outValue = new TypedValue();
                            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
                            holder.mView.setBackgroundResource(outValue.resourceId);
                            holder.mOnClickListener = item.getOnClickListener();
                    } else {
                        TypedValue outValue = new TypedValue();
                        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, false);
                        holder.mView.setBackgroundResource(outValue.resourceId);
                        holder.mOnClickListener = item.getOnClickListener();
                        holder.mOnClickListener = null;
                    }

                }
                break;
            }
            case VIEW_TYPE_TITLE_ITEM: {
                MaterialAboutTitleItem item = (MaterialAboutTitleItem) mData.get(position);

                CharSequence text = item.getText();
                int textRes = item.getTextRes();

                holder.mText.setVisibility(View.VISIBLE);
                if (text != null) {
                    holder.mText.setText(text);
                } else if (textRes != 0) {
                    holder.mText.setText(textRes);
                } else {
                    holder.mText.setVisibility(View.GONE);
                }

                Drawable drawable = item.getIcon();
                int drawableRes = item.getIconRes();
                if (drawable != null) {
                    holder.mIcon.setImageDrawable(drawable);
                } else if (drawableRes != 0) {
                    holder.mIcon.setImageResource(drawableRes);
                }

                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (mData.get(position) instanceof MaterialAboutActionItem) {
            return VIEW_TYPE_ACTION_ITEM;
        } else if (mData.get(position) instanceof MaterialAboutTitleItem) {
            return VIEW_TYPE_TITLE_ITEM;
        }
        Log.i(TAG, "getItemViewType: That didn't work oops");
        return VIEW_TYPE_ACTION_ITEM;
    }

    public void swapData(ArrayList<MaterialAboutItem> newData) {
        mData = newData;
        notifyDataSetChanged();
    }

    public ArrayList<MaterialAboutItem> getData() {
        return mData;
    }
}
