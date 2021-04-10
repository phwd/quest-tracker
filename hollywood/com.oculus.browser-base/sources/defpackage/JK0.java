package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* renamed from: JK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JK0 extends ViewGroup.MarginLayoutParams {

    /* renamed from: a  reason: collision with root package name */
    public XK0 f8284a;
    public final Rect b = new Rect();
    public boolean c = true;
    public boolean d = false;

    public JK0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int a() {
        return this.f8284a.g();
    }

    public boolean b() {
        return this.f8284a.q();
    }

    public boolean c() {
        return this.f8284a.n();
    }

    public JK0(int i, int i2) {
        super(i, i2);
    }

    public JK0(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
    }

    public JK0(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }

    public JK0(JK0 jk0) {
        super((ViewGroup.LayoutParams) jk0);
    }
}
