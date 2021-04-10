package defpackage;

import android.animation.Animator;

/* renamed from: p21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4166p21 extends AbstractC2406em {
    public final /* synthetic */ View$OnLayoutChangeListenerC4337q21 G;

    public C4166p21(View$OnLayoutChangeListenerC4337q21 q21) {
        this.G = q21;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        if (((Boolean) this.G.M.get()).booleanValue()) {
            this.G.N.onResult(new RunnableC3995o21(this));
        } else {
            this.G.b(true);
        }
        this.G.S = null;
    }
}
