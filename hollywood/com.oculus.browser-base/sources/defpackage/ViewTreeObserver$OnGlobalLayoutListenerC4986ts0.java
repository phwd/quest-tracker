package defpackage;

import J.N;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import java.util.Objects;

/* renamed from: ts0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewTreeObserver$OnGlobalLayoutListenerC4986ts0 implements ViewTreeObserver.OnGlobalLayoutListener {
    public int F;
    public WindowInsets G;
    public final Rect H = new Rect();
    public final /* synthetic */ C5836ys0 I;

    public ViewTreeObserver$OnGlobalLayoutListenerC4986ts0(C5836ys0 ys0) {
        this.I = ys0;
    }

    public void onGlobalLayout() {
        View view = this.I.s1;
        boolean z = false;
        int i = 0;
        while (true) {
            i += view.getTop();
            ViewParent parent = view.getParent();
            if (parent != null && (parent instanceof View)) {
                view = (View) parent;
            }
        }
        boolean z2 = this.F != i;
        this.F = i;
        if (!z2) {
            if (Build.VERSION.SDK_INT >= 30) {
                WindowInsets rootWindowInsets = this.I.s1.getRootWindowInsets();
                z = !rootWindowInsets.equals(this.G);
                this.G = rootWindowInsets;
            } else {
                Objects.requireNonNull(this.I);
                if (AbstractC4226pO.a() && N.M09VlOh_("OmniboxAdaptiveSuggestionsCount")) {
                    C5836ys0 ys0 = this.I;
                    ((C3909na0) ys0.q1).L.a(ys0.n1);
                    z = !this.I.n1.equals(this.H);
                    this.H.set(this.I.n1);
                }
            }
            if (!z) {
                return;
            }
        }
        this.I.requestLayout();
    }
}
