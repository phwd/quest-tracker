package defpackage;

import android.view.ViewGroup;

/* renamed from: rz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4667rz0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C0913Oz0 oz0 = (C0913Oz0) obj2;
        KH0 kh0 = (KH0) obj3;
        SH0 sh0 = AbstractC0182Cz0.f7851a;
        if (sh0 == kh0) {
            int f = uh0.f(sh0);
            ViewGroup.LayoutParams layoutParams = oz0.c.getLayoutParams();
            layoutParams.height = Math.max(0, f);
            oz0.c.setLayoutParams(layoutParams);
            return;
        }
        TH0 th0 = AbstractC0182Cz0.b;
        if (th0 == kh0) {
            oz0.f = (Runnable) uh0.g(th0);
        }
    }
}
