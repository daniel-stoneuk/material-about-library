package com.danielstone.materialaboutlibrary.items;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;

import com.danielstone.materialaboutlibrary.R;
import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

public class MaterialAboutActionCheckBoxItem extends MaterialAboutCheckBoxItem {

    private MaterialAboutItemOnClickAction onClickAction = null;
    private MaterialAboutItemOnClickAction onLongClickAction = null;

    private MaterialAboutActionCheckBoxItem(Builder builder) {
        super(builder);

        this.onClickAction = builder.onClickAction;
        this.onLongClickAction = builder.onLongClickAction;
    }

    public MaterialAboutActionCheckBoxItem(CharSequence text, CharSequence subText, Drawable icon, boolean checked, MaterialAboutOnCheckedChangedAction onCheckedChanged, MaterialAboutItemOnClickAction onClickAction) {
        super(text, subText, icon, checked, onCheckedChanged);
        this.onClickAction = onClickAction;
    }

    public MaterialAboutActionCheckBoxItem(int textRes, int subTextRes, int iconRes, boolean checked, MaterialAboutOnCheckedChangedAction onCheckedChanged, MaterialAboutItemOnClickAction onClickAction) {
        super(textRes, subTextRes, iconRes, checked, onCheckedChanged);
        this.onClickAction = onClickAction;
    }

    public static MaterialAboutItemViewHolder getViewHolder(View view) {
        return new MaterialAboutActionCheckBoxItemViewHolder(view);
    }

    public static void setupItem(MaterialAboutActionCheckBoxItemViewHolder holder, MaterialAboutActionCheckBoxItem item, Context context) {
        MaterialAboutCheckBoxItem.setupItem(holder, item, context);

        if (item.getOnClickAction() != null || item.getOnLongClickAction() != null) {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
            holder.actionView.setBackgroundResource(outValue.resourceId);
        } else {
            holder.actionView.setBackgroundResource(0);
        }
        holder.setOnClickAction(item.getOnClickAction());
        holder.setOnLongClickAction(item.getOnLongClickAction());
    }

    @Override
    public int getType() {
        return ViewTypeManager.ItemType.ACTION_CHECKBOX_ITEM;
    }

    @Override
    public String getDetailString() {
        return "MaterialAboutActionCheckBoxItem{" +
                "onClickAction=" + onClickAction +
                ", onLongClickAction=" + onLongClickAction +
                "} extends " + super.getDetailString();
    }

    public MaterialAboutActionCheckBoxItem(MaterialAboutActionCheckBoxItem item) {
        super(item);
        this.onClickAction = item.onClickAction;
        this.onLongClickAction = item.onLongClickAction;
    }

    @Override
    public MaterialAboutItem clone() {
        return new MaterialAboutActionCheckBoxItem(this);
    }

    public MaterialAboutItemOnClickAction getOnClickAction() {
        return onClickAction;
    }

    public MaterialAboutActionCheckBoxItem setOnClickAction(MaterialAboutItemOnClickAction onClickAction) {
        this.onClickAction = onClickAction;
        return this;
    }

    public MaterialAboutItemOnClickAction getOnLongClickAction() {
        return onLongClickAction;
    }

    public MaterialAboutActionCheckBoxItem setOnLongClickAction(MaterialAboutItemOnClickAction onLongClickAction) {
        this.onLongClickAction = onLongClickAction;
        return this;
    }

    public static class MaterialAboutActionCheckBoxItemViewHolder extends MaterialAboutCheckBoxItemViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final View actionView;
        private MaterialAboutItemOnClickAction onClickAction;
        private MaterialAboutItemOnClickAction onLongClickAction;

        MaterialAboutActionCheckBoxItemViewHolder(View view) {
            super(view);
            actionView = view.findViewById(R.id.mal_checkbox_action);
        }

        public void setOnClickAction(MaterialAboutItemOnClickAction onClickAction) {
            this.onClickAction = onClickAction;
            actionView.setOnClickListener(onClickAction != null ? this : null);
        }

        public void setOnLongClickAction(MaterialAboutItemOnClickAction onLongClickAction) {
            this.onLongClickAction = onLongClickAction;
            actionView.setOnLongClickListener(onLongClickAction != null ? this : null);
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

    public static class Builder extends MaterialAboutCheckBoxItem.Builder {

        MaterialAboutItemOnClickAction onClickAction = null;
        MaterialAboutItemOnClickAction onLongClickAction = null;

        @Override
        public Builder text(CharSequence text) {
            return (Builder) super.text(text);
        }

        @Override
        public Builder text(int text) {
            return (Builder) super.text(text);
        }

        @Override
        public Builder subText(CharSequence subText) {
            return (Builder) super.subText(subText);
        }

        @Override
        public Builder subText(int subTextRes) {
            return (Builder) super.subText(subTextRes);
        }

        @Override
        public Builder subTextHtml(String subTextHtml) {
            return (Builder) super.subTextHtml(subTextHtml);
        }

        @Override
        public Builder subTextChecked(CharSequence subTextChecked) {
            return (Builder) super.subTextChecked(subTextChecked);
        }

        @Override
        public Builder subTextChecked(int subTextCheckedRes) {
            return (Builder) super.subTextChecked(subTextCheckedRes);
        }

        @Override
        public Builder subTextCheckedHtml(String subTextCheckedHtml) {
            return (Builder) super.subTextCheckedHtml(subTextCheckedHtml);
        }

        @Override
        public Builder icon(Drawable icon) {
            return (Builder) super.icon(icon);
        }

        @Override
        public Builder icon(int iconRes) {
            return (Builder) super.icon(iconRes);
        }

        @Override
        public Builder showIcon(boolean showIcon) {
            return (Builder) super.showIcon(showIcon);
        }

        @Override
        public Builder setIconGravity(int iconGravity) {
            return (Builder) super.setIconGravity(iconGravity);
        }

        @Override
        public Builder setOnCheckedChanged(MaterialAboutOnCheckedChangedAction onCheckedChanged) {
            return (Builder) super.setOnCheckedChanged(onCheckedChanged);
        }

        @Override
        public Builder setChecked(boolean checked) {
            return (Builder) super.setChecked(checked);
        }

        public Builder setOnClickAction(MaterialAboutItemOnClickAction onClickAction) {
            this.onClickAction = onClickAction;
            return this;
        }

        public Builder setOnLongClickAction(MaterialAboutItemOnClickAction onLongClickAction) {
            this.onLongClickAction = onLongClickAction;
            return this;
        }

        public MaterialAboutActionCheckBoxItem build() {
            return new MaterialAboutActionCheckBoxItem(this);
        }
    }
}
