package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

/* renamed from: G7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G7 extends LinearLayout.LayoutParams {

    /* renamed from: a  reason: collision with root package name */
    public int f8065a = 1;
    public Interpolator b;

    public G7(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.j);
        this.f8065a = obtainStyledAttributes.getInt(0, 0);
        if (obtainStyledAttributes.hasValue(1)) {
            this.b = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(1, 0));
        }
        obtainStyledAttributes.recycle();
    }

    public G7(int i, int i2) {
        super(i, i2);
    }

    public G7(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }

    public G7(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
    }

    public G7(LinearLayout.LayoutParams layoutParams) {
        super(layoutParams);
    }
}
