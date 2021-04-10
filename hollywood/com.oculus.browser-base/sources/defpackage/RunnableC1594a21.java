package defpackage;

import android.graphics.drawable.Drawable;

/* renamed from: a21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1594a21 implements Runnable {
    public final View$OnLayoutChangeListenerC4337q21 F;
    public final String G;
    public final Drawable H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f9403J;
    public final int K;

    public RunnableC1594a21(View$OnLayoutChangeListenerC4337q21 q21, String str, Drawable drawable, int i, int i2, int i3) {
        this.F = q21;
        this.G = str;
        this.H = drawable;
        this.I = i;
        this.f9403J = i2;
        this.K = i3;
    }

    public void run() {
        View$OnLayoutChangeListenerC4337q21 q21 = this.F;
        String str = this.G;
        Drawable drawable = this.H;
        int i = this.I;
        int i2 = this.f9403J;
        int i3 = this.K;
        q21.F.m(AbstractC4507r21.f11179a, str);
        q21.F.m(AbstractC4507r21.b, drawable);
        q21.F.k(AbstractC4507r21.f, 0.0f);
        q21.F.l(AbstractC4507r21.e, i);
        q21.F.l(AbstractC4507r21.g, i2);
        q21.F.l(AbstractC4507r21.h, i3);
        q21.F.l(AbstractC4507r21.c, 4);
        q21.f11110J = new RunnableC2970i21(q21);
    }
}
