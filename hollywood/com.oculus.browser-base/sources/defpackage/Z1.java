package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* renamed from: Z1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Z1 extends ViewGroup.MarginLayoutParams {

    /* renamed from: a  reason: collision with root package name */
    public int f9313a;

    public Z1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f9313a = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.b);
        this.f9313a = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
    }

    public Z1(int i, int i2) {
        super(i, i2);
        this.f9313a = 0;
        this.f9313a = 8388627;
    }

    public Z1(Z1 z1) {
        super((ViewGroup.MarginLayoutParams) z1);
        this.f9313a = 0;
        this.f9313a = z1.f9313a;
    }

    public Z1(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f9313a = 0;
    }
}
