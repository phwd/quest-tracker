package defpackage;

import android.view.ViewGroup;

/* renamed from: l01  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3476l01 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C5968zf1 zf1 = (C5968zf1) obj2;
        KH0 kh0 = (KH0) obj3;
        if (N01.g == kh0) {
            AbstractC0017Af1.a(zf1, uh0);
        } else if (N01.h == kh0) {
            AbstractC0017Af1.a(zf1, uh0);
        } else {
            SH0 sh0 = N01.b;
            if (sh0 == kh0) {
                int f = uh0.f(sh0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) zf1.b.getLayoutParams();
                if (marginLayoutParams != null) {
                    marginLayoutParams.bottomMargin = f;
                    return;
                }
                return;
            }
            SH0 sh02 = N01.j;
            if (sh02 == kh0) {
                int f2 = uh0.f(sh02);
                ViewGroup.LayoutParams layoutParams = zf1.c.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.height = f2;
                    zf1.c.setLayoutParams(layoutParams);
                }
            }
        }
    }
}
