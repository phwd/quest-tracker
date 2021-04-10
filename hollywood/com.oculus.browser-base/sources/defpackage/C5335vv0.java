package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: vv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5335vv0 extends AnimatorListenerAdapter {
    public final /* synthetic */ Runnable F;
    public final /* synthetic */ C5505wv0 G;

    public C5335vv0(C5505wv0 wv0, Runnable runnable) {
        this.G = wv0;
        this.F = runnable;
    }

    public void onAnimationEnd(Animator animator) {
        this.G.i = null;
        Runnable runnable = this.F;
        if (runnable != null) {
            runnable.run();
        }
    }
}
