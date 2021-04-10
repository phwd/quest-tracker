package defpackage;

import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* renamed from: OA  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OA implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ CoordinatorLayout F;

    public OA(CoordinatorLayout coordinatorLayout) {
        this.F = coordinatorLayout;
    }

    public boolean onPreDraw() {
        this.F.p(0);
        return true;
    }
}
