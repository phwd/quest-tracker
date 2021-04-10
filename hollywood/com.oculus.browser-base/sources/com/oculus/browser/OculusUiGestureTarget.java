package com.oculus.browser;

import J.N;
import android.view.View;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OculusUiGestureTarget {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC1251Ul0 f9703a;
    public long b;

    public OculusUiGestureTarget(View view, float f, float f2, int i) {
        this.f9703a = new C1312Vl0(view);
        this.b = N.Mh57D7wO(this, f, f2, i);
    }

    public final void clearNativePointer() {
        this.b = 0;
    }

    public final long getNativeObject() {
        return this.b;
    }

    public final void inject(int i, long j) {
        ((C1312Vl0) this.f9703a).a(i, 1, j);
    }

    public final void setPointer(int i, int i2) {
        ((C1312Vl0) this.f9703a).b(0, (float) i, (float) i2, 0, 1);
    }
}
