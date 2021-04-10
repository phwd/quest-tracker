package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* renamed from: xa  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5611xa extends FrameLayout.LayoutParams {

    /* renamed from: a  reason: collision with root package name */
    public float f11615a;
    public boolean b;
    public boolean c;
    public int d;
    public int e;
    public int f;
    public int g;

    public C5611xa(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.p);
        this.f11615a = obtainStyledAttributes.getFraction(0, 1, 1, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public void setBaseAttributes(TypedArray typedArray, int i, int i2) {
        ((FrameLayout.LayoutParams) this).width = typedArray.getLayoutDimension(i, 0);
        int layoutDimension = typedArray.getLayoutDimension(i2, 0);
        ((FrameLayout.LayoutParams) this).height = layoutDimension;
        this.f = ((FrameLayout.LayoutParams) this).width;
        this.g = layoutDimension;
    }

    public C5611xa(int i, int i2) {
        super(i, i2);
    }
}
