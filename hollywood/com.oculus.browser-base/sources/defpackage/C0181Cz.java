package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: Cz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0181Cz extends AnimatorListenerAdapter {
    public final /* synthetic */ C0242Dz F;

    public C0181Cz(C0242Dz dz) {
        this.F = dz;
    }

    public void onAnimationEnd(Animator animator) {
        C0242Dz dz = this.F;
        if (dz.h == 0.0f) {
            dz.c = 0;
            dz.d = false;
            dz.e = "";
            dz.f = false;
            dz.h = 0.0f;
        }
        dz.i.removeAllListeners();
        this.F.i = null;
    }
}
