package com.danielstone.materialaboutlibrary.items;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.StringRes;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


import com.danielstone.materialaboutlibrary.R;
import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.view.View.GONE;

/**
 * Created by François Dexemple on 04/05/2018
 */
public class MaterialAboutSwitchItem extends MaterialAboutItem {
    public static final int GRAVITY_TOP = 0;
    public static final int GRAVITY_MIDDLE = 1;
    public static final int GRAVITY_BOTTOM = 2;
    private CharSequence text = null;
    private int textRes = 0;
    private CharSequence subText = null;
    private int subTextRes = 0;
    private Drawable icon = null;
    private int iconRes = 0;
    private boolean showIcon = true;
    private int iconGravity = GRAVITY_MIDDLE;
    private MaterialAboutOnCheckedChangedAction onCheckedChanged = null;
    private boolean checked = false;

    private MaterialAboutSwitchItem(MaterialAboutSwitchItem.Builder builder) {
        super();
        this.text = builder.text;
        this.textRes = builder.textRes;

        this.subText = builder.subText;
        this.subTextRes = builder.subTextRes;

        this.icon = builder.icon;
        this.iconRes = builder.iconRes;

        this.showIcon = builder.showIcon;

        this.iconGravity = builder.iconGravity;

        this.onCheckedChanged = builder.onCheckedChanged;
        this.checked=builder.checked;
    }

    public MaterialAboutSwitchItem(CharSequence text, CharSequence subText, Drawable icon, boolean checked, MaterialAboutOnCheckedChangedAction onCheckedChanged) {
        this.text = text;
        this.subText = subText;
        this.icon = icon;
        this.onCheckedChanged = onCheckedChanged;
        this.checked=checked;
    }

    public MaterialAboutSwitchItem(int textRes, int subTextRes, int iconRes, boolean checked, MaterialAboutOnCheckedChangedAction onCheckedChanged) {
        this.textRes = textRes;
        this.subTextRes = subTextRes;
        this.iconRes = iconRes;
        this.onCheckedChanged = onCheckedChanged;
        this.checked=checked;
    }

    public static MaterialAboutItemViewHolder getViewHolder(View view) {
        return new MaterialAboutSwitchItemViewHolder(view);
    }

    public static void setupItem(MaterialAboutSwitchItemViewHolder holder, MaterialAboutSwitchItem item, Context context) {
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

        CharSequence subText = item.getSubText();
        int subTextRes = item.getSubTextRes();

        holder.subText.setVisibility(View.VISIBLE);
        if (subText != null) {
            holder.subText.setText(subText);
        } else if (subTextRes != 0) {
            holder.subText.setText(subTextRes);
        } else {
            holder.subText.setVisibility(GONE);
        }

        if (item.shouldShowIcon()) {
            holder.icon.setVisibility(View.VISIBLE);
            Drawable drawable = item.getIcon();
            int drawableRes = item.getIconRes();
            if (drawable != null) {
                holder.icon.setImageDrawable(drawable);
            } else if (drawableRes != 0) {
                holder.icon.setImageResource(drawableRes);
            }
        } else {
            holder.icon.setVisibility(GONE);
        }

        int pL = 0, pT = 0, pR = 0, pB = 0;
        if (Build.VERSION.SDK_INT < 21) {
            pL = holder.view.getPaddingLeft();
            pT = holder.view.getPaddingTop();
            pR = holder.view.getPaddingRight();
            pB = holder.view.getPaddingBottom();
        }

        if (item.getOnCheckedChanged() != null) {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
            holder.view.setBackgroundResource(outValue.resourceId);
        } else {
            holder.view.setBackgroundResource(0);
        }
        holder.setOnCheckedChanged(item.getOnCheckedChanged());

        if (Build.VERSION.SDK_INT < 21) {
            holder.view.setPadding(pL, pT, pR, pB);
        }

        holder.aswitch.setChecked(item.isChecked());
    }

    @Override
    public int getType() {
        return ViewTypeManager.ItemType.SWITCH_ITEM;
    }

    @Override
    public String getDetailString() {
        return "MaterialAboutSwitchItem{" +
                "text=" + text +
                ", textRes=" + textRes +
                ", subText=" + subText +
                ", subTextRes=" + subTextRes +
                ", icon=" + icon +
                ", iconRes=" + iconRes +
                ", showIcon=" + showIcon +
                ", iconGravity=" + iconGravity +
                ", onCheckedChanged=" + onCheckedChanged +
                '}';
    }

    public MaterialAboutSwitchItem(MaterialAboutSwitchItem item) {
        this.id = item.getId();
        this.text = item.getText();
        this.textRes = item.getTextRes();
        this.subText = item.getSubText();
        this.subTextRes = item.getSubTextRes();
        this.icon = item.getIcon();
        this.iconRes = item.getIconRes();
        this.showIcon = item.showIcon;
        this.iconGravity = item.iconGravity;
        this.onCheckedChanged = item.onCheckedChanged;
        this.checked = item.checked;
    }

    @Override
    public MaterialAboutItem clone() {
        return new MaterialAboutSwitchItem(this);
    }

    public CharSequence getText() {
        return text;
    }
    public MaterialAboutSwitchItem setText(CharSequence text) {
        this.textRes = 0;
        this.text = text;
        return this;
    }

    public int getTextRes() {
        return textRes;
    }

    public MaterialAboutSwitchItem setTextRes(int textRes) {
        this.text = null;
        this.textRes = textRes;
        return this;
    }

    public CharSequence getSubText() {
        return subText;
    }

    public MaterialAboutSwitchItem setSubText(CharSequence subText) {
        this.subTextRes = 0;
        this.subText = subText;
        return this;
    }

    public int getSubTextRes() {
        return subTextRes;
    }

    public MaterialAboutSwitchItem setSubTextRes(int subTextRes) {
        this.subText = null;
        this.subTextRes = subTextRes;
        return this;
    }

    public Drawable getIcon() {
        return icon;
    }

    public MaterialAboutSwitchItem setIcon(Drawable icon) {
        this.iconRes = 0;
        this.icon = icon;
        return this;
    }

    public int getIconRes() {
        return iconRes;
    }

    public MaterialAboutSwitchItem setIconRes(int iconRes) {
        this.icon = null;
        this.iconRes = iconRes;
        return this;
    }

    public boolean shouldShowIcon() {
        return showIcon;
    }

    public MaterialAboutSwitchItem setShouldShowIcon(boolean showIcon) {
        this.showIcon = showIcon;
        return this;
    }

    @MaterialAboutSwitchItem.IconGravity
    public int getIconGravity() {
        return iconGravity;
    }

    public MaterialAboutSwitchItem setIconGravity(int iconGravity) {
        this.iconGravity = iconGravity;
        return this;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public MaterialAboutOnCheckedChangedAction getOnCheckedChanged() {
        return onCheckedChanged;
    }

    public MaterialAboutSwitchItem setOnCheckedChanged(MaterialAboutOnCheckedChangedAction onCheckedChanged) {
        this.onCheckedChanged = onCheckedChanged;
        return this;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({GRAVITY_TOP, GRAVITY_MIDDLE, GRAVITY_BOTTOM})
    public @interface IconGravity {
    }

    public static class MaterialAboutSwitchItemViewHolder extends MaterialAboutItemViewHolder implements CompoundButton.OnCheckedChangeListener{
        public final View view;
        public final ImageView icon;
        public final TextView text;
        public final TextView subText;
        public final Switch aswitch;
        private MaterialAboutOnCheckedChangedAction onCheckedChanged;

        MaterialAboutSwitchItemViewHolder(View view) {
            super(view);
            this.view = view;
            icon = (ImageView) view.findViewById(R.id.mal_switch_image);
            text = (TextView) view.findViewById(R.id.mal_switch_text);
            subText = (TextView) view.findViewById(R.id.mal_switch_subtext);
            aswitch = (Switch) view.findViewById(R.id.mal_switch);
        }

        public void setOnCheckedChanged(MaterialAboutOnCheckedChangedAction onCheckedChanged) {
            this.onCheckedChanged = onCheckedChanged;
            this.aswitch.setOnCheckedChangeListener(onCheckedChanged != null ? this : null);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (onCheckedChanged != null) {
                onCheckedChanged.onCheckedChanged(buttonView,isChecked);
            }
        }
    }

    public static class Builder {

        MaterialAboutOnCheckedChangedAction onCheckedChanged = null;
        private CharSequence text = null;
        @StringRes
        private int textRes = 0;
        private CharSequence subText = null;
        @StringRes
        private int subTextRes = 0;
        private Drawable icon = null;
        @DrawableRes
        private int iconRes = 0;
        private boolean showIcon = true;
        @IconGravity
        private int iconGravity = GRAVITY_MIDDLE;
        private boolean checked = false;

        public Builder text(CharSequence text) {
            this.text = text;
            this.textRes = 0;
            return this;
        }

        public Builder text(@StringRes int text) {
            this.textRes = text;
            this.text = null;
            return this;
        }

        public Builder subText(CharSequence subText) {
            this.subText = subText;
            this.subTextRes = 0;
            return this;
        }

        public Builder subText(@StringRes int subTextRes) {
            this.subText = null;
            this.subTextRes = subTextRes;
            return this;
        }

        public Builder subTextHtml(String subTextHtml) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                this.subText = Html.fromHtml(subTextHtml, Html.FROM_HTML_MODE_LEGACY);
            } else {
                //noinspection deprecation
                this.subText = Html.fromHtml(subTextHtml);
            }
            this.subTextRes = 0;
            return this;
        }

        public Builder icon(Drawable icon) {
            this.icon = icon;
            this.iconRes = 0;
            return this;
        }

        public Builder icon(@DrawableRes int iconRes) {
            this.icon = null;
            this.iconRes = iconRes;
            return this;
        }

        public Builder showIcon(boolean showIcon) {
            this.showIcon = showIcon;
            return this;
        }

        public Builder setIconGravity(@MaterialAboutSwitchItem.IconGravity int iconGravity) {
            this.iconGravity = iconGravity;
            return this;
        }

        public Builder setOnCheckedChanged(MaterialAboutOnCheckedChangedAction onCheckedChanged) {
            this.onCheckedChanged = onCheckedChanged;
            return this;
        }

        public Builder setChecked(boolean checked) {
            this.checked = checked;
            return this;
        }

        public MaterialAboutSwitchItem build() {
            return new MaterialAboutSwitchItem(this);
        }
    }
}
