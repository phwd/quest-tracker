package defpackage;

import android.view.View;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import java.util.Objects;

/* renamed from: UW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UW implements Runnable {
    public final CoordinatorLayout F;
    public final View G;
    public final /* synthetic */ VW H;

    public UW(VW vw, CoordinatorLayout coordinatorLayout, View view) {
        this.H = vw;
        this.F = coordinatorLayout;
        this.G = view;
    }

    public void run() {
        OverScroller overScroller;
        if (this.G != null && (overScroller = this.H.d) != null) {
            if (overScroller.computeScrollOffset()) {
                VW vw = this.H;
                vw.t(this.F, this.G, vw.d.getCurrY());
                this.G.postOnAnimation(this);
                return;
            }
            VW vw2 = this.H;
            CoordinatorLayout coordinatorLayout = this.F;
            View view = this.G;
            AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) vw2;
            Objects.requireNonNull(behavior);
            AppBarLayout appBarLayout = (AppBarLayout) view;
            behavior.G(coordinatorLayout, appBarLayout);
            if (appBarLayout.P) {
                appBarLayout.i(appBarLayout.j(behavior.B(coordinatorLayout)));
            }
        }
    }
}
