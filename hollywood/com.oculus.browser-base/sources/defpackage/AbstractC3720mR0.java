package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: mR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3720mR0 {
    public static void a(C5968zf1 zf1, UH0 uh0) {
        int i = 0;
        boolean z = uh0.h(N01.g) && uh0.h(N01.f) && !uh0.h(N01.h);
        if (z && zf1.b.getParent() == null) {
            zf1.f11758a.addView(zf1.b);
            int f = uh0.f(N01.j);
            ViewGroup.LayoutParams layoutParams = zf1.c.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = f;
                zf1.c.setLayoutParams(layoutParams);
            }
        }
        View view = zf1.b;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
    }
}
