package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Zd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1535Zd1 extends AnimatorListenerAdapter {
    public final /* synthetic */ C1872be1 F;

    public C1535Zd1(C1872be1 be1) {
        this.F = be1;
    }

    public void onAnimationEnd(Animator animator) {
        Tab tab = this.F.F;
        if (tab != null) {
            ((C0011Ad1) tab.C()).c(this.F.H);
        }
        C1872be1 be1 = this.F;
        if (be1.f9550J != null) {
            be1.f9550J = null;
        }
        be1.N = false;
        be1.O = false;
    }
}
