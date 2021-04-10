package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import java.util.Iterator;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;

/* renamed from: N91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class N91 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabListRecyclerView F;

    public N91(TabListRecyclerView tabListRecyclerView) {
        this.F = tabListRecyclerView;
    }

    public void onAnimationEnd(Animator animator) {
        TabListRecyclerView tabListRecyclerView = this.F;
        tabListRecyclerView.n1 = null;
        tabListRecyclerView.setVisibility(4);
        Iterator it = ((C3919nd1) this.F.o1).i.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5619xc1) uq0.next()).d();
            } else {
                return;
            }
        }
    }
}
