package defpackage;

import android.animation.Animator;

/* renamed from: j21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3140j21 extends AbstractC2406em {
    public final /* synthetic */ Runnable G;
    public final /* synthetic */ View$OnLayoutChangeListenerC4337q21 H;

    public C3140j21(View$OnLayoutChangeListenerC4337q21 q21, Runnable runnable) {
        this.H = q21;
        this.G = runnable;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        this.G.run();
        this.H.P = null;
    }
}
