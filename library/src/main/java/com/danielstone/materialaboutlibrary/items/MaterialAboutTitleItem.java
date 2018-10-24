package com.danielstone.materialaboutlibrary.items;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielstone.materialaboutlibrary.R;
import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

import static android.view.View.GONE;

public class MaterialAboutTitleItem extends MaterialAboutItem {

    private CharSequence text = null;
    private int textRes = 0;
    private CharSequence desc = null;
    private int descRes = 0;
    private Drawable icon = null;
    private int iconRes = 0;
    private MaterialAboutItemOnClickAction onClickAction = null;
    private MaterialAboutItemOnClickAction onLongClickAction = null;

    private MaterialAboutTitleItem(MaterialAboutTitleItem.Builder builder) {
        super();
        this.text = builder.text;
        this.textRes = builder.textRes;

        this.desc = builder.desc;
        this.descRes = builder.descRes;

        this.icon = builder.icon;
        this.iconRes = builder.iconRes;

        this.onClickAction = builder.onClickAction;
        this.onLongClickAction = builder.onLongClickAction;
    }

    public MaterialAboutTitleItem(CharSequence text, CharSequence desc, Drawable icon) {
        this.text = text;
        this.desc = desc;
        this.icon = icon;
    }

    public MaterialAboutTitleItem(int textRes, int descRes, int iconRes) {
        this.textRes = textRes;
        this.descRes = descRes;
        this.iconRes = iconRes;
    }

    public static MaterialAboutItemViewHolder getViewHolder(View view) {
        return new MaterialAboutTitleItem.MaterialAboutTitleItemViewHolder(view);
    }

    public static void setupItem(MaterialAboutTitleItemViewHolder holder, MaterialAboutTitleItem item, Context context) {

        CharSequence text = item.getText();
        int textRes = item.getTextRes();

        holder.text.setVisibility(View.VISIBLE);
        if (text != null) {
            holder.text.setText(text);
        } else if (textRes != 0) {
            holder.text.setText(textRes);
        } else {
            holder.text.setVisibility(GONE);
        }

        CharSequence desc = item.getDesc();
        int descRes = item.getDescRes();

        holder.desc.setVisibility(View.VISIBLE);
        if (desc != null) {
            holder.desc.setText(desc);
        } else if (descRes != 0) {
            holder.desc.setText(descRes);
        } else {
            holder.desc.setVisibility(GONE);
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

        if (item.getOnClickAction() != null || item.getOnLongClickAction() != null) {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
            holder.view.setBackgroundResource(outValue.resourceId);
        } else {
            holder.view.setBackgroundResource(0);
        }
        holder.setOnClickAction(item.getOnClickAction());
        holder.setOnLongClickAction(item.getOnLongClickAction());

        if (Build.VERSION.SDK_INT < 21) {
            holder.view.setPadding(pL, pT, pR, pB);
        }
    }

    @Override
    public int getType() {
        return ViewTypeManager.ItemType.TITLE_ITEM;
    }

    @Override
    public String getDetailString() {
        return "MaterialAboutTitleItem{" +
                "text=" + text +
                ", textRes=" + textRes +
                ", desc=" + desc +
                ", descRes=" + descRes +
                ", icon=" + icon +
                ", iconRes=" + iconRes +
                ", onClickAction=" + onClickAction +
                ", onLongClickAction=" + onLongClickAction +
                '}';
    }

    public MaterialAboutTitleItem(MaterialAboutTitleItem item) {
        this.id = item.getId();
        this.text = item.getText();
        this.textRes = item.getTextRes();
        this.desc = item.getDesc();
        this.descRes = item.getDescRes();
        this.icon = item.getIcon();
        this.iconRes = item.getIconRes();
        this.onClickAction = item.getOnClickAction();
        this.onLongClickAction = item.getOnLongClickAction();
    }

    @Override
    public MaterialAboutTitleItem clone() {
        return new MaterialAboutTitleItem(this);
    }

    public CharSequence getText() {
        return text;
    }

    public MaterialAboutTitleItem setText(CharSequence text) {
        this.textRes = 0;
        this.text = text;
        return this;
    }

    public int getTextRes() {
        return textRes;
    }

    public MaterialAboutTitleItem setTextRes(int textRes) {
        this.text = null;
        this.textRes = textRes;
        return this;
    }

    public CharSequence getDesc() {
        return desc;
    }

    public MaterialAboutTitleItem setDesc(CharSequence desc) {
        this.descRes = 0;
        this.desc = desc;
        return this;
    }

    public int getDescRes() {
        return descRes;
    }

    public MaterialAboutTitleItem setDescRes(int descRes) {
        this.desc = null;
        this.descRes = textRes;
        return this;
    }

    public Drawable getIcon() {
        return icon;
    }

    public MaterialAboutTitleItem setIcon(Drawable icon) {
        this.iconRes = 0;
        this.icon = icon;
        return this;
    }

    public int getIconRes() {
        return iconRes;
    }

    public MaterialAboutTitleItem setIconRes(int iconRes) {
        this.icon = null;
        this.iconRes = iconRes;
        return this;
    }

    public MaterialAboutItemOnClickAction getOnClickAction() {
        return onClickAction;
    }

    public MaterialAboutTitleItem setOnClickAction(MaterialAboutItemOnClickAction onClickAction) {
        this.onClickAction = onClickAction;
        return this;
    }

    public MaterialAboutItemOnClickAction getOnLongClickAction() {
        return onLongClickAction;
    }

    public MaterialAboutTitleItem setOnLongClickAction(MaterialAboutItemOnClickAction onLongClickAction) {
        this.onLongClickAction = onLongClickAction;
        return this;
    }
    public static class MaterialAboutTitleItemViewHolder extends MaterialAboutItemViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final View view;
        public final ImageView icon;
        public final TextView text;
        public final TextView desc;
        private MaterialAboutItemOnClickAction onClickAction;
        private MaterialAboutItemOnClickAction onLongClickAction;

        MaterialAboutTitleItemViewHolder(View view) {
            super(view);
            this.view = view;
            icon = (ImageView) view.findViewById(R.id.mal_item_image);
            text = (TextView) view.findViewById(R.id.mal_item_text);
            desc = (TextView) view.findViewById(R.id.mal_item_desc);
        }

        public void setOnClickAction(MaterialAboutItemOnClickAction onClickAction) {
            this.onClickAction = onClickAction;
            if (onClickAction != null) {
                view.setOnClickListener(this);
            } else {
                view.setClickable(false);
            }
        }

        public void setOnLongClickAction(MaterialAboutItemOnClickAction onLongClickAction) {
            this.onLongClickAction = onLongClickAction;
            if (onLongClickAction != null) {
                view.setOnLongClickListener(this);
            } else {
                view.setLongClickable(false);
            }
        }

        @Override
        public void onClick(View v) {
            if (onClickAction != null) {
                onClickAction.onClick();
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (onLongClickAction != null) {
                onLongClickAction.onClick();
                return true;
            }
            return false;
        }
    }

    public static class Builder {

        MaterialAboutItemOnClickAction onClickAction = null;
        MaterialAboutItemOnClickAction onLongClickAction = null;
        private CharSequence text = null;
        @StringRes
        private int textRes = 0;
        private CharSequence desc = null;
        @StringRes
        private int descRes = 0;
        private Drawable icon = null;
        @DrawableRes
        private int iconRes = 0;

        public MaterialAboutTitleItem.Builder text(CharSequence text) {
            this.text = text;
            this.textRes = 0;
            return this;
        }


        public MaterialAboutTitleItem.Builder text(@StringRes int text) {
            this.textRes = text;
            this.text = null;
            return this;
        }

        public MaterialAboutTitleItem.Builder desc(CharSequence desc) {
            this.desc = desc;
            this.descRes = 0;
            return this;
        }


        public MaterialAboutTitleItem.Builder desc(@StringRes int desc) {
            this.descRes = desc;
            this.desc = null;
            return this;
        }

        public MaterialAboutTitleItem.Builder icon(Drawable icon) {
            this.icon = icon;
            this.iconRes = 0;
            return this;
        }


        public MaterialAboutTitleItem.Builder icon(@DrawableRes int iconRes) {
            this.icon = null;
            this.iconRes = iconRes;
            return this;
        }

        public MaterialAboutTitleItem.Builder setOnClickAction(MaterialAboutItemOnClickAction onClickAction) {
            this.onClickAction = onClickAction;
            return this;
        }

        public MaterialAboutTitleItem.Builder setOnLongClickAction(MaterialAboutItemOnClickAction onLongClickAction) {
            this.onLongClickAction = onLongClickAction;
            return this;
        }

        public MaterialAboutTitleItem build() {
            return new MaterialAboutTitleItem(this);
        }
    }
}
