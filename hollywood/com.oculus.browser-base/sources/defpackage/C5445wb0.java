package defpackage;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.Magnifier;

/* renamed from: wb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5445wb0 {

    /* renamed from: a  reason: collision with root package name */
    public C5615xb0 f11552a;
    public ValueAnimator b;
    public boolean c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h = -1.0f;
    public float i = -1.0f;

    public C5445wb0(C5615xb0 xb0) {
        this.f11552a = xb0;
        a();
    }

    public final void a() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.b = ofFloat;
        ofFloat.setDuration(100L);
        this.b.setInterpolator(new LinearInterpolator());
        this.b.addUpdateListener(new C5275vb0(this));
    }

    public void b() {
        C5615xb0 xb0 = this.f11552a;
        Magnifier magnifier = xb0.f11618a;
        if (magnifier != null) {
            magnifier.dismiss();
            xb0.f11618a = null;
        }
        this.b.cancel();
        this.c = false;
    }
}
