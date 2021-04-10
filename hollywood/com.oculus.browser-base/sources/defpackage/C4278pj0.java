package defpackage;

import android.animation.Animator;

/* renamed from: pj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4278pj0 extends AbstractC2406em {
    public final /* synthetic */ boolean G;
    public final /* synthetic */ Runnable H;
    public final /* synthetic */ C4449qj0 I;

    public C4278pj0(C4449qj0 qj0, boolean z, Runnable runnable) {
        this.I = qj0;
        this.G = z;
        this.H = runnable;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        this.I.Q = this.G ? 2 : 0;
        this.H.run();
        this.I.P = null;
    }

    @Override // defpackage.AbstractC2406em
    public void c(Animator animator) {
        this.I.Q = 1;
    }
}
