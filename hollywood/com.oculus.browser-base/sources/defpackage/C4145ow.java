package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: ow  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4145ow extends AnimatorListenerAdapter {
    public final /* synthetic */ C5677xw F;
    public final /* synthetic */ C4316pw G;

    public C4145ow(C4316pw pwVar, C5677xw xwVar) {
        this.G = pwVar;
        this.F = xwVar;
    }

    public void onAnimationEnd(Animator animator) {
        this.G.f11101a.remove(this.F);
        this.F.H.c(this);
    }
}
