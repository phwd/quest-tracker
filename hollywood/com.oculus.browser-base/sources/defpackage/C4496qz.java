package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: qz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4496qz extends AnimatorListenerAdapter {
    public final /* synthetic */ C4666rz F;

    public C4496qz(C4666rz rzVar) {
        this.F = rzVar;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.q = false;
    }
}
