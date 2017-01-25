package com.danielstone.materialaboutlibrary.items;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.StringRes;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danielstone.materialaboutlibrary.R;
import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.view.View.GONE;

public class MaterialAboutActionItem extends MaterialAboutItem {

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
    private MaterialAboutActionItem.OnClickListener onClickListener = null;
    private MaterialAboutActionItem(Builder builder) {
        this.text = builder.text;
        this.textRes = builder.textRes;

        this.subText = builder.subText;
        this.subTextRes = builder.subTextRes;

        this.icon = builder.icon;
        this.iconRes = builder.iconRes;

        this.showIcon = builder.showIcon;

        this.iconGravity = builder.iconGravity;

        this.onClickListener = builder.onClickListener;
    }

    public MaterialAboutActionItem(CharSequence text, CharSequence subText, Drawable icon, OnClickListener onClickListener) {
        this.text = text;
        this.subText = subText;
        this.icon = icon;
        this.onClickListener = onClickListener;
    }

    public MaterialAboutActionItem(CharSequence text, CharSequence subText, Drawable icon) {
        this.text = text;
        this.subText = subText;
        this.icon = icon;
    }

    public MaterialAboutActionItem(int textRes, int subTextRes, int iconRes, OnClickListener onClickListener) {
        this.textRes = textRes;
        this.subTextRes = subTextRes;
        this.iconRes = iconRes;
        this.onClickListener = onClickListener;
    }

    public MaterialAboutActionItem(int textRes, int subTextRes, int iconRes) {
        this.textRes = textRes;
        this.subTextRes = subTextRes;
        this.iconRes = iconRes;
    }

    public static MaterialAboutItemViewHolder getViewHolder(View view) {
        return new MaterialAboutActionItemViewHolder(view);
    }

    public static void setupItem(MaterialAboutActionItemViewHolder holder, MaterialAboutActionItem item, Context context) {
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

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.icon.getLayoutParams();
        switch (item.getIconGravity()) {
            case MaterialAboutActionItem.GRAVITY_TOP:
                params.gravity = Gravity.TOP;
                break;
            case MaterialAboutActionItem.GRAVITY_MIDDLE:
                params.gravity = Gravity.CENTER_VERTICAL;
                break;
            case MaterialAboutActionItem.GRAVITY_BOTTOM:
                params.gravity = Gravity.BOTTOM;
                break;
        }
        holder.icon.setLayoutParams(params);

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
            holder.view.setSoundEffectsEnabled(true);
        } else {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, false);
            holder.view.setBackgroundResource(outValue.resourceId);
            holder.onClickListener = null;
            holder.view.setSoundEffectsEnabled(false);
        }

        if (Build.VERSION.SDK_INT < 21) {
            holder.view.setPadding(pL, pT, pR, pB);
        }
    }

    @Override
    public int getType() {
        return ViewTypeManager.ItemType.ACTION_ITEM;
    }

    public CharSequence getText() {
        return text;
    }

    public MaterialAboutActionItem setText(CharSequence text) {
        this.textRes = 0;
        this.text = text;
        return this;
    }

    public int getTextRes() {
        return textRes;
    }

    public MaterialAboutActionItem setTextRes(int textRes) {
        this.text = null;
        this.textRes = textRes;
        return this;
    }

    public CharSequence getSubText() {
        return subText;
    }

    public MaterialAboutActionItem setSubText(CharSequence subText) {
        this.subTextRes = 0;
        this.subText = subText;
        return this;
    }

    public int getSubTextRes() {
        return subTextRes;
    }

    public MaterialAboutActionItem setSubTextRes(int subTextRes) {
        this.subText = null;
        this.subTextRes = subTextRes;
        return this;
    }

    public Drawable getIcon() {
        return icon;
    }

    public MaterialAboutActionItem setIcon(Drawable icon) {
        this.iconRes = 0;
        this.icon = icon;
        return this;
    }

    public int getIconRes() {
        return iconRes;
    }

    public MaterialAboutActionItem setIconRes(int iconRes) {
        this.icon = null;
        this.iconRes = iconRes;
        return this;
    }

    public boolean shouldShowIcon() {
        return showIcon;
    }

    public MaterialAboutActionItem setShouldShowIcon(boolean showIcon) {
        this.showIcon = showIcon;
        return this;
    }

    @IconGravity
    public int getIconGravity() {
        return iconGravity;
    }

    public MaterialAboutActionItem setIconGravity(int iconGravity) {
        this.iconGravity = iconGravity;
        return this;
    }

    public MaterialAboutActionItem.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public MaterialAboutActionItem setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public interface OnClickListener {
        void onClick();
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({GRAVITY_TOP, GRAVITY_MIDDLE, GRAVITY_BOTTOM})
    public @interface IconGravity {
    }

    public static class MaterialAboutActionItemViewHolder extends MaterialAboutItemViewHolder implements View.OnClickListener {
        public final View view;
        public final ImageView icon;
        public final TextView text;
        public final TextView subText;
        public MaterialAboutActionItem.OnClickListener onClickListener;

        MaterialAboutActionItemViewHolder(View view) {
            super(view);
            this.view = view;
            icon = (ImageView) view.findViewById(R.id.mal_item_image);
            text = (TextView) view.findViewById(R.id.mal_item_text);
            subText = (TextView) view.findViewById(R.id.mal_action_item_subtext);

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

    public static class Builder {

        MaterialAboutActionItem.OnClickListener onClickListener = null;
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

        public Builder setIconGravity(@IconGravity int iconGravity) {
            this.iconGravity = iconGravity;
            return this;
        }

        public Builder setOnClickListener(MaterialAboutActionItem.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public MaterialAboutActionItem build() {
            return new MaterialAboutActionItem(this);
        }
    }
}
