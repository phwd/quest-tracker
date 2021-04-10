package com.oculus.ocui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.oculus.common.ocui.R;

public final class OCSpinner extends FrameLayout {
    public OCSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.ocspinner_circle, (ViewGroup) this, true);
    }
}
