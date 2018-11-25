package com.danielstone.materialaboutlibrary.items;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielstone.materialaboutlibrary.R;
import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.StringRes;

import static android.view.View.GONE;

/**
 * Created by François Dexemple on 04/05/2018
 */
public class MaterialAboutCheckBoxItem extends MaterialAboutCheckableItem {
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

    private MaterialAboutCheckBoxItem(MaterialAboutCheckBoxItem.Builder builder) {
        super(builder);
        this.text = builder.text;
        this.textRes = builder.textRes;

        this.subText = builder.subText;
        this.subTextRes = builder.subTextRes;

        this.icon = builder.icon;
        this.iconRes = builder.iconRes;

        this.showIcon = builder.showIcon;

        this.iconGravity = builder.iconGravity;
    }

    public MaterialAboutCheckBoxItem(CharSequence text, CharSequence subText, Drawable icon, boolean checked, MaterialAboutOnCheckedChangedAction onCheckedChanged) {
        super(checked, onCheckedChanged);
        this.text = text;
        this.subText = subText;
        this.icon = icon;
    }

    public MaterialAboutCheckBoxItem(int textRes, int subTextRes, int iconRes, boolean checked, MaterialAboutOnCheckedChangedAction onCheckedChanged) {
        super(checked, onCheckedChanged);
        this.textRes = textRes;
        this.subTextRes = subTextRes;
        this.iconRes = iconRes;
    }


    public static MaterialAboutItemViewHolder getViewHolder(View view) {
        return new MaterialAboutCheckBoxItemViewHolder(view);
    }

    public static void setupItem(MaterialAboutCheckBoxItemViewHolder holder, MaterialAboutCheckBoxItem item, Context context) {
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

        if (item.getOnCheckedChangedAction() != null) {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
            holder.view.setBackgroundResource(outValue.resourceId);
        } else {
            holder.view.setBackgroundResource(0);
        }
        holder.aCheckBox.setChecked(item.isChecked());
        // NOTE: We must call this after the setChanged call to not trigger the listener on initialization here
        MaterialAboutCheckableItem.setupItem(holder, item);

        if (Build.VERSION.SDK_INT < 21) {
            holder.view.setPadding(pL, pT, pR, pB);
        }
    }

    @Override
    public int getType() {
        return ViewTypeManager.ItemType.CHECKBOX_ITEM;
    }

    @Override
    public String getDetailString() {
        return "MaterialAboutCheckBoxItem{" +
                "text=" + text +
                ", textRes=" + textRes +
                ", subText=" + subText +
                ", subTextRes=" + subTextRes +
                ", icon=" + icon +
                ", iconRes=" + iconRes +
                ", showIcon=" + showIcon +
                ", iconGravity=" + iconGravity +
                "} extends " + super.getDetailString();
    }

    public MaterialAboutCheckBoxItem(MaterialAboutCheckBoxItem item) {
    	super(item);
        this.id = item.getId();
        this.text = item.getText();
        this.textRes = item.getTextRes();
        this.subText = item.getSubText();
        this.subTextRes = item.getSubTextRes();
        this.icon = item.getIcon();
        this.iconRes = item.getIconRes();
        this.showIcon = item.showIcon;
        this.iconGravity = item.iconGravity;
    }

    @Override
    public MaterialAboutItem clone() {
        return new MaterialAboutCheckBoxItem(this);
    }

    public CharSequence getText() {
        return text;
    }

    public MaterialAboutCheckBoxItem setText(CharSequence text) {
        this.textRes = 0;
        this.text = text;
        return this;
    }

    public int getTextRes() {
        return textRes;
    }

    public MaterialAboutCheckBoxItem setTextRes(int textRes) {
        this.text = null;
        this.textRes = textRes;
        return this;
    }

    public CharSequence getSubText() {
        return subText;
    }

    public MaterialAboutCheckBoxItem setSubText(CharSequence subText) {
        this.subTextRes = 0;
        this.subText = subText;
        return this;
    }

    public int getSubTextRes() {
        return subTextRes;
    }

    public MaterialAboutCheckBoxItem setSubTextRes(int subTextRes) {
        this.subText = null;
        this.subTextRes = subTextRes;
        return this;
    }

    public Drawable getIcon() {
        return icon;
    }

    public MaterialAboutCheckBoxItem setIcon(Drawable icon) {
        this.iconRes = 0;
        this.icon = icon;
        return this;
    }

    public int getIconRes() {
        return iconRes;
    }

    public MaterialAboutCheckBoxItem setIconRes(int iconRes) {
        this.icon = null;
        this.iconRes = iconRes;
        return this;
    }

    public boolean shouldShowIcon() {
        return showIcon;
    }

    public MaterialAboutCheckBoxItem setShouldShowIcon(boolean showIcon) {
        this.showIcon = showIcon;
        return this;
    }

    @MaterialAboutCheckBoxItem.IconGravity
    public int getIconGravity() {
        return iconGravity;
    }

    public MaterialAboutCheckBoxItem setIconGravity(int iconGravity) {
        this.iconGravity = iconGravity;
        return this;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({GRAVITY_TOP, GRAVITY_MIDDLE, GRAVITY_BOTTOM})
    public @interface IconGravity {
    }

    public static class MaterialAboutCheckBoxItemViewHolder extends MaterialAboutCheckableItemViewHolder implements CompoundButton.OnCheckedChangeListener {
        public final View view;
        public final ImageView icon;
        public final TextView text;
        public final TextView subText;
        public final CheckBox aCheckBox;

        MaterialAboutCheckBoxItemViewHolder(View view) {
            super(view);
            this.view = view;
            icon = (ImageView) view.findViewById(R.id.mal_checkbox_image);
            text = (TextView) view.findViewById(R.id.mal_checkbox_text);
            subText = (TextView) view.findViewById(R.id.mal_checkbox_subtext);
            aCheckBox = (CheckBox) view.findViewById(R.id.mal_checkbox);
        }

		@Override
		protected void initActionViewListener(boolean hasListener) {
			this.aCheckBox.setOnCheckedChangeListener(hasListener ? this : null);
		}

		@Override
		protected void setActionViewChecked(boolean isChecked) {
			this.aCheckBox.setChecked(isChecked);
		}

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        	super.onCheckedChanged(isChecked);
        }
    }

    public static class Builder extends CheckableBuilder<Builder> {

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

        public Builder setIconGravity(@MaterialAboutCheckBoxItem.IconGravity int iconGravity) {
            this.iconGravity = iconGravity;
            return this;
        }

        public MaterialAboutCheckBoxItem build() {
            return new MaterialAboutCheckBoxItem(this);
        }
    }
}