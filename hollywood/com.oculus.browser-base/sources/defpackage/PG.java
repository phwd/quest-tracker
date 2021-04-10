package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.UpdateDelta;
import org.chromium.components.offline_items_collection.VisualsCallback;

/* renamed from: PG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PG implements AbstractC3960nr0, AbstractC3789mr0 {
    public AbstractC3960nr0 F;
    public C1322Vq0 G = new C1322Vq0();

    public PG(AbstractC3960nr0 nr0) {
        this.F = nr0;
        this.F.i(this);
    }

    @Override // defpackage.AbstractC3789mr0
    public void a(OfflineItem offlineItem, UpdateDelta updateDelta) {
        if (!U70.b(offlineItem.F)) {
            Iterator it = this.G.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC3789mr0) uq0.next()).a(offlineItem, updateDelta);
                } else {
                    return;
                }
            }
        }
    }

    @Override // defpackage.AbstractC3960nr0
    public void b(C0788My my) {
        this.F.b(my);
    }

    @Override // defpackage.AbstractC3789mr0
    public void c(List list) {
        ArrayList l = l(list);
        Iterator it = this.G.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC3789mr0) uq0.next()).c(l);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC3789mr0
    public void d(C0788My my) {
        if (!U70.b(my)) {
            Iterator it = this.G.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC3789mr0) uq0.next()).d(my);
                } else {
                    return;
                }
            }
        }
    }

    @Override // defpackage.AbstractC3960nr0
    public void e(C0788My my, boolean z) {
        this.F.e(my, z);
    }

    @Override // defpackage.AbstractC3960nr0
    public void f(C0788My my, VisualsCallback visualsCallback) {
        this.F.f(my, visualsCallback);
    }

    @Override // defpackage.AbstractC3960nr0
    public void g(C0788My my) {
        this.F.g(my);
    }

    @Override // defpackage.AbstractC3960nr0
    public void h(Callback callback) {
        this.F.h(new OG(this, callback));
    }

    @Override // defpackage.AbstractC3960nr0
    public void i(AbstractC3789mr0 mr0) {
        this.G.b(mr0);
    }

    @Override // defpackage.AbstractC3960nr0
    public void j(C0788My my) {
        this.F.j(my);
    }

    @Override // defpackage.AbstractC3960nr0
    public void k(C1916bt0 bt0, C0788My my) {
        this.F.k(bt0, my);
    }

    public final ArrayList l(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            OfflineItem offlineItem = (OfflineItem) it.next();
            if (!U70.b(offlineItem.F)) {
                arrayList.add(offlineItem);
            }
        }
        return arrayList;
    }
}
