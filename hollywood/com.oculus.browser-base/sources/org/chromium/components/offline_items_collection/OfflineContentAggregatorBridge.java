package org.chromium.components.offline_items_collection;

import J.N;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.Callback;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OfflineContentAggregatorBridge implements AbstractC3960nr0 {
    public long F;
    public C1322Vq0 G = new C1322Vq0();

    public OfflineContentAggregatorBridge(long j) {
        new Handler();
        this.F = j;
    }

    public static OfflineContentAggregatorBridge create(long j) {
        return new OfflineContentAggregatorBridge(j);
    }

    public static void onShareInfoAvailable(ShareCallback shareCallback, String str, String str2, OfflineItemShareInfo offlineItemShareInfo) {
        shareCallback.a(new C0788My(str, str2), offlineItemShareInfo);
    }

    public static void onVisualsAvailable(VisualsCallback visualsCallback, String str, String str2, OfflineItemVisuals offlineItemVisuals) {
        visualsCallback.b(new C0788My(str, str2), offlineItemVisuals);
    }

    @Override // defpackage.AbstractC3960nr0
    public void b(C0788My my) {
        long j = this.F;
        if (j != 0) {
            N.Mwk11G0z(j, this, my.f8514a, my.b);
        }
    }

    @Override // defpackage.AbstractC3960nr0
    public void e(C0788My my, boolean z) {
        long j = this.F;
        if (j != 0) {
            N.MSy1v2e$(j, this, my.f8514a, my.b, z);
        }
    }

    @Override // defpackage.AbstractC3960nr0
    public void f(C0788My my, VisualsCallback visualsCallback) {
        N.MwOuZAaJ(this.F, this, my.f8514a, my.b, visualsCallback);
    }

    @Override // defpackage.AbstractC3960nr0
    public void g(C0788My my) {
        long j = this.F;
        if (j != 0) {
            N.MBvrmOCy(j, this, my.f8514a, my.b);
        }
    }

    @Override // defpackage.AbstractC3960nr0
    public void h(Callback callback) {
        long j = this.F;
        if (j != 0) {
            N.MWgZa2II(j, this, callback);
        }
    }

    @Override // defpackage.AbstractC3960nr0
    public void i(AbstractC3789mr0 mr0) {
        this.G.b(mr0);
    }

    @Override // defpackage.AbstractC3960nr0
    public void j(C0788My my) {
        long j = this.F;
        if (j != 0) {
            N.MGbhWq61(j, this, my.f8514a, my.b);
        }
    }

    @Override // defpackage.AbstractC3960nr0
    public void k(C1916bt0 bt0, C0788My my) {
        long j = this.F;
        if (j != 0) {
            Objects.requireNonNull(bt0);
            N.MXureVYk(j, this, 1, bt0.f9568a, my.f8514a, my.b);
        }
    }

    public final void onItemRemoved(String str, String str2) {
        C0788My my = new C0788My(str, str2);
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

    public final void onItemUpdated(OfflineItem offlineItem, UpdateDelta updateDelta) {
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

    public final void onItemsAdded(ArrayList arrayList) {
        if (arrayList.size() != 0) {
            Iterator it = this.G.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC3789mr0) uq0.next()).c(arrayList);
                } else {
                    return;
                }
            }
        }
    }

    public final void onNativeDestroyed() {
        this.F = 0;
    }
}
