package com.oculus.panelapp.anytimeui.v2.tablet.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.vrshell.panels.views.ShellButton;
import javax.annotation.Nullable;

public class ToggleButton extends ShellButton {
    /* JADX INFO: finally extract failed */
    public ToggleButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ToggleButton);
        try {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) obtainStyledAttributes.getDrawable(R.styleable.ToggleButton_icon);
            obtainStyledAttributes.recycle();
            layerIconOntoBackground(bitmapDrawable);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void layerIconOntoBackground(@Nullable BitmapDrawable bitmapDrawable) {
        if (bitmapDrawable != null) {
            bitmapDrawable.setGravity(17);
        }
        setBackground(new LayerDrawable(new Drawable[]{getContext().getResources().getDrawable(R.drawable.osig_button_primary_background, getContext().getTheme()), bitmapDrawable}));
    }

    public void setIcon(Drawable drawable) {
        layerIconOntoBackground((BitmapDrawable) drawable);
    }
}
