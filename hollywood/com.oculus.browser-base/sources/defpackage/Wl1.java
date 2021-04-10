package defpackage;

import J.N;
import android.graphics.Rect;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: Wl1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Wl1 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C2408em1 em1 = (C2408em1) obj2;
        KH0 kh0 = (KH0) obj3;
        if (em1.H.get() != null) {
            N.MHqlwRYg(em1.G, em1, (ResourceManager) em1.H.get(), uh0.f(AbstractC2238dm1.f9805a), uh0.f(AbstractC2238dm1.c), uh0.f(AbstractC2238dm1.b), uh0.f(AbstractC2238dm1.d), uh0.e(AbstractC2238dm1.e), uh0.h(AbstractC2238dm1.f), uh0.h(AbstractC2238dm1.g));
            C3971nv nvVar = (C3971nv) uh0.g(AbstractC2238dm1.h);
            if (nvVar != null) {
                long j = em1.G;
                Rect rect = nvVar.f10520a;
                int i = rect.left;
                int i2 = rect.top;
                int width = rect.width();
                int height = nvVar.f10520a.height();
                int i3 = nvVar.c;
                Rect rect2 = nvVar.b;
                N.MKAQCQU8(j, em1, i, i2, width, height, i3, rect2.left, rect2.top, rect2.width(), nvVar.b.height(), nvVar.d);
            }
        }
    }
}
