package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import java.util.ArrayList;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RadioButtonWithDescriptionLayout extends RadioGroup implements LJ0 {
    public final List F = new ArrayList();
    public RadioGroup.OnCheckedChangeListener G;

    public RadioButtonWithDescriptionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.LJ0
    public void a(RadioButtonWithDescription radioButtonWithDescription) {
        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = this.G;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(this, radioButtonWithDescription.getId());
        }
    }

    public void b(View view, RadioButtonWithDescription radioButtonWithDescription) {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        addView(view, indexOfChild(radioButtonWithDescription) + 1);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            RadioButtonWithDescription radioButtonWithDescription = (RadioButtonWithDescription) getChildAt(i);
            radioButtonWithDescription.I = this;
            if (radioButtonWithDescription.getId() == -1) {
                radioButtonWithDescription.setId(RadioGroup.generateViewId());
            }
            List list = this.F;
            radioButtonWithDescription.f10823J = list;
            list.add(radioButtonWithDescription);
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for (int i = 0; i < getChildCount(); i++) {
            AbstractC4656rv1.g(getChildAt(i), z);
        }
    }

    public void setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener onCheckedChangeListener) {
        this.G = onCheckedChangeListener;
    }
}
