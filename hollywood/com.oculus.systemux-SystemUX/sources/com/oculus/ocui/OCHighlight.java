package com.oculus.ocui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.oculus.common.ocui.R;

public class OCHighlight extends View {
    public OCHighlight(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundResource(R.drawable.ochighlight_circle);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        startAnimation();
    }

    public void setVisibility(int i) {
        if (i != 0) {
            clearAnimation();
        }
        super.setVisibility(i);
    }

    public void startAnimation() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.ochightlight_pulse));
    }
}
