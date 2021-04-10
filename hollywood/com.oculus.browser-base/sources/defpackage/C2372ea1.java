package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: ea1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2372ea1 extends AnimatorListenerAdapter {
    public final /* synthetic */ AbstractC3056ia1 F;

    public C2372ea1(AbstractC3056ia1 ia1) {
        this.F = ia1;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.l(true);
    }
}
