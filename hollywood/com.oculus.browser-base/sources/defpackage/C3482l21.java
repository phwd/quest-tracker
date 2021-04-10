package defpackage;

import android.animation.Animator;
import android.graphics.drawable.Drawable;

/* renamed from: l21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3482l21 extends AbstractC2406em {
    public final /* synthetic */ String G;
    public final /* synthetic */ Drawable H;
    public final /* synthetic */ int I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ int f10322J;
    public final /* synthetic */ View$OnLayoutChangeListenerC4337q21 K;

    public C3482l21(View$OnLayoutChangeListenerC4337q21 q21, String str, Drawable drawable, int i, int i2) {
        this.K = q21;
        this.G = str;
        this.H = drawable;
        this.I = i;
        this.f10322J = i2;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        this.K.F.m(AbstractC4507r21.f11179a, this.G);
        this.K.F.m(AbstractC4507r21.b, this.H);
        this.K.F.l(AbstractC4507r21.g, this.I);
        this.K.F.l(AbstractC4507r21.h, this.f10322J);
    }
}
