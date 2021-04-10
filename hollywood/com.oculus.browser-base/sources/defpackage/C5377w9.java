package defpackage;

import android.animation.TimeAnimator;

/* renamed from: w9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5377w9 implements TimeAnimator.TimeListener {

    /* renamed from: a  reason: collision with root package name */
    public final C5547x9 f11523a;

    public C5377w9(C5547x9 x9Var) {
        this.f11523a = x9Var;
    }

    public void onTimeUpdate(TimeAnimator timeAnimator, long j, long j2) {
        C5547x9 x9Var = this.f11523a;
        if (x9Var.b.N != null) {
            float f = (((float) j2) * 0.001f * x9Var.g) + x9Var.e;
            x9Var.e = f;
            int round = Math.round(f - ((float) x9Var.f));
            x9Var.f += round;
            x9Var.b.N.smoothScrollBy(round, 0);
            if (!Float.isNaN(x9Var.h) && !Float.isNaN(x9Var.i)) {
                x9Var.c(Math.round(x9Var.h), Math.round(x9Var.i), 0);
            }
        }
    }
}
