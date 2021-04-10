package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: em  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2406em extends AnimatorListenerAdapter {
    public boolean F;

    public void a(Animator animator) {
    }

    public void b(Animator animator) {
    }

    public void c(Animator animator) {
    }

    public final void onAnimationCancel(Animator animator) {
        if (!this.F) {
            this.F = true;
            a(animator);
        }
    }

    public final void onAnimationEnd(Animator animator) {
        if (!this.F) {
            this.F = true;
            b(animator);
        }
    }

    public final void onAnimationStart(Animator animator) {
        this.F = false;
        c(animator);
    }
}
