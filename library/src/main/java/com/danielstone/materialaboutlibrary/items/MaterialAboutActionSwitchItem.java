package com.danielstone.materialaboutlibrary.items;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;

import com.danielstone.materialaboutlibrary.R;
import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

public class MaterialAboutActionSwitchItem extends MaterialAboutSwitchItem {

    private MaterialAboutItemOnClickAction onClickAction = null;
    private MaterialAboutItemOnClickAction onLongClickAction = null;

    private MaterialAboutActionSwitchItem(Builder builder) {
        super(builder);

        this.onClickAction = builder.onClickAction;
        this.onLongClickAction = builder.onLongClickAction;
    }

    public MaterialAboutActionSwitchItem(CharSequence text, CharSequence subText, Drawable icon, boolean checked, MaterialAboutOnCheckedChangedAction onCheckedChanged, MaterialAboutItemOnClickAction onClickAction) {
        super(text, subText, icon, checked, onCheckedChanged);
        this.onClickAction = onClickAction;
    }

    public MaterialAboutActionSwitchItem(int textRes, int subTextRes, int iconRes, boolean checked, MaterialAboutOnCheckedChangedAction onCheckedChanged, MaterialAboutItemOnClickAction onClickAction) {
        super(textRes, subTextRes, iconRes, checked, onCheckedChanged);
        this.onClickAction = onClickAction;
    }

    public static MaterialAboutItemViewHolder getViewHolder(View view) {
        return new MaterialAboutActionSwitchItemViewHolder(view);
    }

    public static void setupItem(MaterialAboutActionSwitchItemViewHolder holder, MaterialAboutActionSwitchItem item, Context context) {
        MaterialAboutSwitchItem.setupItem(holder, item, context);

        int pL = 0, pT = 0, pR = 0, pB = 0;
        if (Build.VERSION.SDK_INT < 21) {
            pL = holder.actionView.getPaddingLeft();
            pT = holder.actionView.getPaddingTop();
            pR = holder.actionView.getPaddingRight();
            pB = holder.actionView.getPaddingBottom();
        }

        if (item.getOnClickAction() != null || item.getOnLongClickAction() != null) {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
            holder.actionView.setBackgroundResource(outValue.resourceId);
        } else {
            holder.actionView.setBackgroundResource(0);
        }
        holder.setOnClickAction(item.getOnClickAction());
        holder.setOnLongClickAction(item.getOnLongClickAction());

        if (Build.VERSION.SDK_INT < 21) {
            holder.actionView.setPadding(pL, pT, pR, pB);
        }
    }

    @Override
    public int getType() {
        return ViewTypeManager.ItemType.ACTION_SWITCH_ITEM;
    }

    @Override
    public String getDetailString() {
        return "MaterialAboutActionSwitchItem{" +
                "onClickAction=" + onClickAction +
                ", onLongClickAction=" + onLongClickAction +
                "} extends " + super.getDetailString();
    }

    public MaterialAboutActionSwitchItem(MaterialAboutActionSwitchItem item) {
        super(item);
        this.onClickAction = item.onClickAction;
        this.onLongClickAction = item.onLongClickAction;
    }

    @Override
    public MaterialAboutItem clone() {
        return new MaterialAboutActionSwitchItem(this);
    }

    public MaterialAboutItemOnClickAction getOnClickAction() {
        return onClickAction;
    }

    public MaterialAboutActionSwitchItem setOnClickAction(MaterialAboutItemOnClickAction onClickAction) {
        this.onClickAction = onClickAction;
        return this;
    }

    public MaterialAboutItemOnClickAction getOnLongClickAction() {
        return onLongClickAction;
    }

    public MaterialAboutActionSwitchItem setOnLongClickAction(MaterialAboutItemOnClickAction onLongClickAction) {
        this.onLongClickAction = onLongClickAction;
        return this;
    }

    public static class MaterialAboutActionSwitchItemViewHolder extends MaterialAboutSwitchItemViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final View actionView;
        private MaterialAboutItemOnClickAction onClickAction;
        private MaterialAboutItemOnClickAction onLongClickAction;

        MaterialAboutActionSwitchItemViewHolder(View view) {
            super(view);
            actionView = view.findViewById(R.id.mal_switch_action);
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

    public static class Builder extends MaterialAboutSwitchItem.Builder {

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

        public MaterialAboutActionSwitchItem build() {
            return new MaterialAboutActionSwitchItem(this);
        }
    }
}
