package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Iy1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Iy1 extends AnimatorListenerAdapter {
    public final /* synthetic */ WindowAndroid F;

    public Iy1(WindowAndroid windowAndroid) {
        this.F = windowAndroid;
    }

    public void onAnimationEnd(Animator animator) {
        animator.removeListener(this);
        this.F.L.remove(animator);
        this.F.A0();
    }
}
