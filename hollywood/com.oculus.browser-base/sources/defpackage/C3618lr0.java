package defpackage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.UpdateDelta;

/* renamed from: lr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3618lr0 implements AbstractC3789mr0 {
    public static C3618lr0 F;
    public Set G = new HashSet();
    public Set H = new HashSet();

    public static C3618lr0 b() {
        if (F == null) {
            F = new C3618lr0();
            AbstractC3960nr0 a2 = AbstractC2935hr0.a();
            a2.i(F);
            C3618lr0 lr0 = F;
            lr0.getClass();
            a2.h(new C3447kr0(lr0));
        }
        return F;
    }

    @Override // defpackage.AbstractC3789mr0
    public void a(OfflineItem offlineItem, UpdateDelta updateDelta) {
    }

    @Override // defpackage.AbstractC3789mr0
    public void c(List list) {
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                OfflineItem offlineItem = (OfflineItem) it.next();
                if (offlineItem.K) {
                    this.G.add(offlineItem.F);
                }
                if (!offlineItem.f10857J) {
                    this.H.add(offlineItem.F);
                }
            }
            e();
        }
    }

    @Override // defpackage.AbstractC3789mr0
    public void d(C0788My my) {
        boolean remove = this.G.remove(my);
        boolean remove2 = this.H.remove(my);
        if (remove || remove2) {
            e();
        }
    }

    public final void e() {
        PU0 pu0 = NU0.f8549a;
        pu0.m("Chrome.NTPExploreOfflineCard.HasExploreOfflineContent", !this.G.isEmpty());
        pu0.m("Chrome.OfflineIndicatorV2.HasPersistentOfflineContent", !this.H.isEmpty());
    }
}
