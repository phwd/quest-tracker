package defpackage;

import J.N;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.suggestions.mostvisited.MostVisitedSitesBridge;

/* renamed from: Xh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1425Xh1 implements AbstractC0763Ml0 {

    /* renamed from: a  reason: collision with root package name */
    public final O31 f9228a;
    public final AbstractC1181Th1 b;
    public final AbstractC1303Vh1 c;
    public final C4105oi1 d;
    public final Collection e = new ArrayList();
    public final C1364Wh1 f;
    public SparseArray g;
    public List h;
    public boolean i;
    public boolean j;

    public C1425Xh1(C4105oi1 oi1, O31 o31, AbstractC1181Th1 th1, AbstractC1303Vh1 vh1, OfflinePageBridge offlinePageBridge) {
        SparseArray sparseArray = new SparseArray();
        sparseArray.put(1, new ArrayList());
        this.g = sparseArray;
        this.f9228a = o31;
        this.b = th1;
        this.c = vh1;
        this.d = oi1;
        C1364Wh1 wh1 = new C1364Wh1(this, offlinePageBridge);
        this.f = wh1;
        ((C0520Il0) o31).f8248a.add(wh1);
    }

    public final void a(int i2) {
        this.e.add(Integer.valueOf(i2));
    }

    public final C0815Nh1 b(SX0 sx0) {
        if (this.g.get(sx0.f) == null) {
            return null;
        }
        for (C0815Nh1 nh1 : (List) this.g.get(sx0.f)) {
            if (nh1.f8567a.equals(sx0)) {
                return nh1;
            }
        }
        return null;
    }

    public final boolean c() {
        if (this.e.contains(1) || this.e.contains(2)) {
            return true;
        }
        return false;
    }

    public final void d(int i2) {
        this.e.remove(Integer.valueOf(i2));
        if (this.e.isEmpty()) {
            List<C0815Nh1> list = (List) this.g.get(1);
            C1486Yh1 yh1 = (C1486Yh1) this.b;
            Objects.requireNonNull(yh1);
            for (C0815Nh1 nh1 : list) {
                MostVisitedSitesBridge mostVisitedSitesBridge = (MostVisitedSitesBridge) yh1.f9290a;
                long j2 = mostVisitedSitesBridge.f10768a;
                if (j2 != 0) {
                    int i3 = nh1.b;
                    int i4 = nh1.c;
                    int i5 = nh1.d;
                    SX0 sx0 = nh1.f8567a;
                    N.MwKG6a15(j2, mostVisitedSitesBridge, i3, i4, i5, sx0.d, sx0.e, sx0.g.getTime(), nh1.f8567a.b);
                }
            }
            AbstractC0824Nl0 nl0 = yh1.f9290a;
            int size = list.size();
            MostVisitedSitesBridge mostVisitedSitesBridge2 = (MostVisitedSitesBridge) nl0;
            long j3 = mostVisitedSitesBridge2.f10768a;
            if (j3 != 0) {
                N.MtbOuYlk(j3, mostVisitedSitesBridge2, size);
            }
            for (C0815Nh1 nh12 : list) {
                if (nh12.a()) {
                    AbstractC3364kK0.g("NewTabPage.TileOfflineAvailable", nh12.b, 12);
                }
            }
        }
    }
}
