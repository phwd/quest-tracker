package defpackage;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: Af1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0017Af1 {
    public static void a(C5968zf1 zf1, UH0 uh0) {
        boolean z = uh0.h(N01.g) && !uh0.h(N01.h);
        if (z && zf1.b.getParent() == null) {
            zf1.f11758a.addView(zf1.b);
            ((ViewGroup.MarginLayoutParams) zf1.b.getLayoutParams()).bottomMargin = uh0.f(N01.b);
            int f = uh0.f(N01.j);
            ViewGroup.LayoutParams layoutParams = zf1.c.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = f;
                zf1.c.setLayoutParams(layoutParams);
            }
        }
        View view = zf1.b;
        if (!z) {
            view.setVisibility(8);
            return;
        }
        view.setAlpha(0.0f);
        view.setVisibility(0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f, 1.0f);
        ofFloat.setDuration(50L);
        ofFloat.start();
    }
}
