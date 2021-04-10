package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* renamed from: x80  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5545x80 extends ViewGroup.MarginLayoutParams {

    /* renamed from: a  reason: collision with root package name */
    public float f11593a;
    public int b;

    public C5545x80(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.Y);
        this.f11593a = obtainStyledAttributes.getFloat(3, 0.0f);
        this.b = obtainStyledAttributes.getInt(0, -1);
        obtainStyledAttributes.recycle();
    }

    public C5545x80(int i, int i2) {
        super(i, i2);
        this.b = -1;
        this.f11593a = 0.0f;
    }

    public C5545x80(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.b = -1;
    }
}
