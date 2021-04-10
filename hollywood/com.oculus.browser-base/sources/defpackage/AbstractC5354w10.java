package defpackage;

import android.animation.Animator;
import android.animation.ValueAnimator;

/* renamed from: w10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5354w10 {

    /* renamed from: a  reason: collision with root package name */
    public Animator f11513a;
    public final /* synthetic */ C5694y10 b;

    public AbstractC5354w10(C5694y10 y10, AbstractC3820n10 n10) {
        this.b = y10;
    }

    public abstract Animator a();

    public ValueAnimator b(D10 d10, float f) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(d10.getTranslationY(), f);
        ofFloat.addUpdateListener(new C5184v10(this, d10));
        return ofFloat;
    }

    public abstract int c();

    public abstract void d();

    public abstract void e();
}
