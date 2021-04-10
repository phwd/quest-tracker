package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;

/* renamed from: Bu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Bu1 extends ViewGroup.LayoutParams {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7770a;
    public int b;
    public float c = 0.0f;
    public boolean d;
    public int e;
    public int f;

    public Bu1() {
        super(-1, -1);
    }

    public Bu1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.F);
        this.b = obtainStyledAttributes.getInteger(0, 48);
        obtainStyledAttributes.recycle();
    }
}
