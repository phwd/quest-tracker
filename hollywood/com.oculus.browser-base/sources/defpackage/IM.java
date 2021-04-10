package defpackage;

import android.view.ViewGroup;
import android.widget.FrameLayout;

/* renamed from: IM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class IM {
    public static void a(ViewGroup viewGroup, UH0 uh0, boolean z) {
        TH0 th0 = N01.i;
        if (uh0.g(th0) != null) {
            FrameLayout frameLayout = ((XO) uh0.g(th0)).o;
            if (z) {
                viewGroup.addView(frameLayout);
                if (uh0.h(N01.d)) {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) frameLayout.getLayoutParams();
                    layoutParams.bottomMargin = uh0.f(N01.b);
                    layoutParams.topMargin = uh0.f(N01.j);
                    return;
                }
                return;
            }
            AbstractC2417ep1.k(frameLayout);
        }
    }
}
