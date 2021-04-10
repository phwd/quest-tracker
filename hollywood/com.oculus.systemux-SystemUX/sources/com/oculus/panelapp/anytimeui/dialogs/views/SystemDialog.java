package com.oculus.panelapp.anytimeui.dialogs.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.oculus.panelapp.anytimeui.R;

public class SystemDialog extends FrameLayout {
    public SystemDialog(Context context) {
        this(context, null);
    }

    public SystemDialog(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SystemDialog(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    public void setIcon(Drawable drawable) {
        LayerDrawable layerDrawable = (LayerDrawable) getBackground();
        if (drawable != null) {
            layerDrawable.setDrawableByLayerId(R.id.system_dialog_header_icon, drawable);
            return;
        }
        layerDrawable.findDrawableByLayerId(R.id.system_dialog_header_icon).setAlpha(0);
        layerDrawable.findDrawableByLayerId(R.id.system_dialog_header_icon_ring).setAlpha(0);
        layerDrawable.findDrawableByLayerId(R.id.system_dialog_header_icon_background).setAlpha(0);
    }

    private void init(@Nullable AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SystemDialog, i, 0);
        setIcon(obtainStyledAttributes.getDrawable(R.styleable.SystemDialog_icon));
        obtainStyledAttributes.recycle();
    }
}
