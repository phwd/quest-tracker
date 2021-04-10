package org.chromium.content.browser;

import android.view.View;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SyntheticGestureTarget {

    /* renamed from: a  reason: collision with root package name */
    public final C1312Vl0 f10918a;

    public SyntheticGestureTarget(View view) {
        this.f10918a = new C1312Vl0(view);
    }

    public static SyntheticGestureTarget create(View view) {
        return new SyntheticGestureTarget(view);
    }

    public final void inject(int i, int i2, long j) {
        this.f10918a.a(i, i2, j);
    }

    public final void setPointer(int i, float f, float f2, int i2) {
        this.f10918a.b(i, f, f2, i2, 0);
    }

    public final void setScrollDeltas(float f, float f2, float f3, float f4) {
        C1312Vl0 vl0 = this.f10918a;
        vl0.b(0, f, f2, 0, 0);
        vl0.b[0].setAxisValue(10, f3);
        vl0.b[0].setAxisValue(9, f4);
    }
}
