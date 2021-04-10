package defpackage;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;

/* renamed from: gR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2695gR0 extends TouchDelegate {

    /* renamed from: a  reason: collision with root package name */
    public final View f9998a;
    public final Rect b = new Rect();
    public final Rect c = new Rect();
    public final Rect d = new Rect();
    public final int e;
    public boolean f;

    public C2695gR0(Rect rect, Rect rect2, View view) {
        super(rect, view);
        this.e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        a(rect, rect2);
        this.f9998a = view;
    }

    public void a(Rect rect, Rect rect2) {
        this.b.set(rect);
        this.d.set(rect);
        Rect rect3 = this.d;
        int i = this.e;
        rect3.inset(-i, -i);
        this.c.set(rect2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2695gR0.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
