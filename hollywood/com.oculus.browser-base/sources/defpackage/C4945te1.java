package defpackage;

import android.animation.ValueAnimator;
import java.util.Iterator;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: te1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4945te1 extends Y11 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC1888bk f11356a;
    public final /* synthetic */ C5285ve1 b;

    public C4945te1(C5285ve1 ve1, AbstractC1888bk bkVar) {
        this.b = ve1;
        this.f11356a = bkVar;
    }

    @Override // defpackage.Y11
    public void g(int i) {
        int dimensionPixelSize = this.b.F.getResources().getDimensionPixelSize(this.b.F.N0()) + i;
        C1551Zj zj = (C1551Zj) this.f11356a;
        zj.Q = true;
        int i2 = zj.M;
        if (i2 != dimensionPixelSize || zj.N != i) {
            int i3 = zj.N;
            zj.M = dimensionPixelSize;
            zj.N = i;
            Tab tab = zj.a0;
            if (!(tab != null && tab.isUserInteractable() && !tab.isNativePage())) {
                if (!zj.Q) {
                    zj.j(false);
                } else if (zj.b0 == null) {
                    zj.c0 = true;
                    int i4 = zj.M;
                    int i5 = zj.N;
                    ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                    zj.b0 = ofFloat;
                    ofFloat.addUpdateListener(new C1185Tj(zj, i3, i5, i2, i4));
                    zj.b0.setDuration(200L);
                    zj.b0.addListener(new C1490Yj(zj));
                    zj.b0.start();
                }
            }
            Iterator it = zj.Y.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    break;
                }
                ((AbstractC2230dk) uq0.next()).k(zj.M, zj.N);
            }
        }
        ((C1551Zj) this.f11356a).Q = false;
    }
}
