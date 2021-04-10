package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: yY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5778yY0 extends AnimatorListenerAdapter {
    public final /* synthetic */ C5948zY0 F;

    public C5778yY0(C5948zY0 zy0) {
        this.F = zy0;
    }

    public void onAnimationEnd(Animator animator) {
        C5948zY0 zy0 = this.F;
        zy0.l.removeOnLayoutChangeListener(zy0.p);
        C5948zY0 zy02 = this.F;
        zy02.j.removeView(zy02.b);
    }
}
