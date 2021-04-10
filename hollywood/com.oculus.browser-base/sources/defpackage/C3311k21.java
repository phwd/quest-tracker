package defpackage;

import android.animation.Animator;
import java.util.Iterator;

/* renamed from: k21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3311k21 extends AbstractC2406em {
    public final /* synthetic */ View$OnLayoutChangeListenerC4337q21 G;

    public C3311k21(View$OnLayoutChangeListenerC4337q21 q21) {
        this.G = q21;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        View$OnLayoutChangeListenerC4337q21 q21 = this.G;
        q21.Q = null;
        Iterator it = q21.H.iterator();
        while (it.hasNext()) {
            ((Y11) it.next()).h();
        }
    }

    @Override // defpackage.AbstractC2406em
    public void c(Animator animator) {
        this.G.O.run();
    }
}
