package com.customfabs;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Zeki Guler on 27,May,2016
 * ©2015 Appscore. All Rights Reserved
 */
public class FloatingActionBackPanel extends FrameLayout {

    public static final int THEME_LIGHT = 0;
    public static final int THEME_DARK = 1;
    public static final int THEME_LIGHTER = 2;
    public static final int THEME_DARKER = 3;

    public FloatingActionBackPanel(Context context, ViewGroup.LayoutParams layoutParams, int theme,
                                   Drawable backgroundDrawable, View contentView,
                                   LayoutParams contentParams) {
        super(context);
        setLayoutParams(layoutParams);

        // If no custom backgroundDrawable is specified, use the background drawable of the theme.
        if(backgroundDrawable == null) {
            if(theme == THEME_LIGHT) {
                backgroundDrawable = context.getResources().getDrawable(R.drawable.button_sub_action_selector);
            }
            else if(theme == THEME_DARK) {
                backgroundDrawable = context.getResources().getDrawable(R.drawable.button_sub_action_dark_selector);
            }
            else if(theme == THEME_LIGHTER) {
                backgroundDrawable = context.getResources().getDrawable(R.drawable.button_action_selector);
            }
            else if(theme == THEME_DARKER) {
                backgroundDrawable = context.getResources().getDrawable(R.drawable.button_action_dark_selector);
            }
            else {
                throw new RuntimeException("Unknown SubActionButton theme: " + theme);
            }
        }
        else {
            backgroundDrawable = backgroundDrawable.mutate().getConstantState().newDrawable();
        }

        setBackgroundResource(backgroundDrawable);
        if(contentView != null) {
            setContentView(contentView, contentParams);
        }

    }

    private void setBackgroundResource(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        }
        else {
            setBackgroundDrawable(drawable);
        }
    }

    /**
     * Sets a content view with custom LayoutParams that will be displayed inside this SubActionButton.
     * @param contentView
     * @param params
     */
    public void setContentView(View contentView, FrameLayout.LayoutParams params) {
        if(params == null) {
            params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER);
            final int margin = getResources().getDimensionPixelSize(R.dimen.sub_action_button_content_margin);
            params.setMargins(margin, margin, margin, margin);
        }

        contentView.setClickable(false);
        this.addView(contentView, params);
    }

    /**
     * A builder for {@link FloatingActionBackPanel} in conventional Java Builder format
     */
    public static class Builder {

        private Context context;
        private ViewGroup.LayoutParams layoutParams;
        private int theme;
        private int position;
        private boolean systemOverlay;
        private Drawable backgroundDrawable;
        private View contentView;
        private LayoutParams contentParams;

        public Builder(Context context) {
            this.context = context;

            // Default floationActionBackPanel settings
            int size = context.getResources().getDimensionPixelSize(R.dimen.action_button_panel_size);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size, size, Gravity.BOTTOM | Gravity.LEFT);
            setLayoutParams(params);
            setTheme(SubActionButton.THEME_LIGHT);
        }

        public Builder setLayoutParams(ViewGroup.LayoutParams layoutParams){
            this.layoutParams = layoutParams;
            return this;
        }

        public Builder setTheme(int theme) {
            this.theme = theme;
            return this;
        }

        public Builder setBackgroundDrawable(Drawable backgroundDrawable) {
            this.backgroundDrawable = backgroundDrawable;
            return this;
        }

        public Builder setContentView(View contentView) {
            return setContentView(contentView, null);
        }

        public Builder setContentView(View contentView, LayoutParams contentParams) {
            this.contentView = contentView;
            this.contentParams = contentParams;
            return this;
        }

        public Builder setSystemOverlay(boolean systemOverlay) {
            this.systemOverlay = systemOverlay;
            return this;
        }

        public FloatingActionBackPanel build() {
            return new FloatingActionBackPanel(context,
                    layoutParams,
                    theme,
                    backgroundDrawable,
                    contentView,
                    contentParams);
        }



    }
}
