package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import java.util.Iterator;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;

/* renamed from: L91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L91 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabListRecyclerView F;

    public L91(TabListRecyclerView tabListRecyclerView) {
        this.F = tabListRecyclerView;
    }

    public void onAnimationEnd(Animator animator) {
        TabListRecyclerView tabListRecyclerView = this.F;
        tabListRecyclerView.m1 = null;
        Iterator it = ((C3919nd1) tabListRecyclerView.o1).i.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                break;
            }
            ((AbstractC5619xc1) uq0.next()).a();
        }
        TabListRecyclerView tabListRecyclerView2 = this.F;
        tabListRecyclerView2.s0(tabListRecyclerView2.w1);
        TabListRecyclerView tabListRecyclerView3 = this.F;
        tabListRecyclerView3.E0(tabListRecyclerView3.computeVerticalScrollOffset() > 0);
        TabListRecyclerView tabListRecyclerView4 = this.F;
        View$OnLayoutChangeListenerC2948hv1 hv1 = tabListRecyclerView4.q1;
        if (hv1 != null) {
            hv1.H = null;
            tabListRecyclerView4.F0();
        }
        if (AbstractC4772sd1.j()) {
            this.F.requestLayout();
        }
    }
}
