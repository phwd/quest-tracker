package org.chromium.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TextViewWithLeading extends TextView {
    public TextViewWithLeading(Context context) {
        super(context);
    }

    public TextViewWithLeading(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.M0, 0, 0);
        if (obtainStyledAttributes.hasValue(0)) {
            setLineSpacing(obtainStyledAttributes.getDimension(0, 0.0f) - getPaint().getFontMetrics(null), 1.0f);
        }
        obtainStyledAttributes.recycle();
    }
}
