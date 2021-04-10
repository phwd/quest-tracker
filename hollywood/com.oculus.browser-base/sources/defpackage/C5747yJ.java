package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: yJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5747yJ extends AnimatorListenerAdapter {
    public final /* synthetic */ AJ F;

    public C5747yJ(AJ aj) {
        this.F = aj;
    }

    public void onAnimationEnd(Animator animator) {
        AJ aj = this.F;
        aj.c.setChecked(aj.h);
        this.F.n.start();
    }
}
