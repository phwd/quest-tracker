package defpackage;

import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.List;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.OfflineItemVisuals;
import org.chromium.components.offline_items_collection.UpdateDelta;
import org.chromium.components.offline_items_collection.VisualsCallback;

/* renamed from: ir0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3105ir0 implements AI, AbstractC3789mr0, VisualsCallback {
    public static final OfflineItemVisuals F = new OfflineItemVisuals();
    public final AbstractC3960nr0 G;
    public final D51 H;
    public final HashMap I = new HashMap();

    /* renamed from: J  reason: collision with root package name */
    public final HashMap f10168J = new HashMap();

    public C3105ir0(AbstractC3960nr0 nr0, D51 d51) {
        this.G = nr0;
        this.H = d51;
        nr0.i(this);
    }

    @Override // defpackage.AbstractC3789mr0
    public void a(OfflineItem offlineItem, UpdateDelta updateDelta) {
        f(offlineItem, updateDelta);
    }

    @Override // org.chromium.components.offline_items_collection.VisualsCallback
    public void b(C0788My my, OfflineItemVisuals offlineItemVisuals) {
        OfflineItem offlineItem = (OfflineItem) this.I.remove(my);
        if (offlineItem != null) {
            if (offlineItemVisuals == null) {
                offlineItemVisuals = F;
            }
            if (k(offlineItem)) {
                this.f10168J.put(my, offlineItemVisuals);
            }
            i(offlineItem, offlineItemVisuals);
        }
    }

    @Override // defpackage.AbstractC3789mr0
    public void c(List list) {
        for (int i = 0; i < list.size(); i++) {
            f((OfflineItem) list.get(i), null);
        }
    }

    @Override // defpackage.AbstractC3789mr0
    public void d(C0788My my) {
        this.I.remove(my);
        this.f10168J.remove(my);
        D51 d51 = this.H;
        d51.k(my);
        d51.b().e(my);
    }

    @Override // defpackage.AI
    public void e() {
    }

    public final void f(OfflineItem offlineItem, UpdateDelta updateDelta) {
        boolean z;
        int i;
        boolean z2 = false;
        if (offlineItem.c0 == 2 && updateDelta != null && !updateDelta.f10860a && !updateDelta.b) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            if (updateDelta != null && updateDelta.b) {
                this.f10168J.remove(offlineItem.F);
            }
            if (!offlineItem.O && ((i = offlineItem.c0) == 0 || i == 1 || i == 2 || i == 4 || i == 5 || i == 6)) {
                z2 = true;
            }
            if (!z2) {
                this.I.remove(offlineItem.F);
                this.f10168J.remove(offlineItem.F);
            } else if (!this.f10168J.containsKey(offlineItem.F)) {
                boolean z3 = !this.I.containsKey(offlineItem.F);
                this.I.put(offlineItem.F, offlineItem);
                if (z3) {
                    this.G.f(offlineItem.F, this);
                    return;
                }
                return;
            }
            i(offlineItem, (OfflineItemVisuals) this.f10168J.get(offlineItem.F));
            if (!k(offlineItem)) {
                this.f10168J.remove(offlineItem.F);
            }
        }
    }

    @Override // defpackage.AI
    public void g(C0788My my, OTRProfileID oTRProfileID) {
        this.G.g(my);
    }

    @Override // defpackage.AI
    public void h(C0788My my, OTRProfileID oTRProfileID) {
        this.G.b(my);
    }

    public final void i(OfflineItem offlineItem, OfflineItemVisuals offlineItemVisuals) {
        Bitmap bitmap;
        if (!offlineItem.K && offlineItem.l0 == null) {
            int i = offlineItem.c0;
            int i2 = 3;
            if (i == 3) {
                this.H.d(offlineItem.F);
                return;
            }
            boolean z = false;
            if (i == 2) {
                i2 = 1;
            } else if (i == 3) {
                i2 = 2;
            } else if (!(i == 4 || i == 5)) {
                i2 = 0;
            }
            DH dh = new DH();
            C0788My my = offlineItem.F;
            dh.z = my;
            dh.m = my.b;
            dh.e = offlineItem.G;
            dh.g = offlineItem.W;
            dh.f = offlineItem.H;
            dh.B = offlineItem.f10857J;
            dh.x = offlineItem.U;
            dh.A = offlineItem.V;
            dh.c = offlineItem.X;
            dh.f7878a = offlineItem.Y;
            dh.i = offlineItem.Z;
            dh.t = offlineItem.a0;
            dh.u = OTRProfileID.a(offlineItem.b0);
            dh.w = i2;
            if (offlineItem.c0 == 6) {
                z = true;
            }
            dh.s = z;
            dh.r = offlineItem.d0;
            dh.j = offlineItem.f0;
            dh.k = offlineItem.Q;
            dh.p = offlineItem.g0;
            dh.q = offlineItem.h0;
            dh.y = offlineItem.i0;
            dh.C = offlineItem.L;
            if (offlineItemVisuals == null) {
                bitmap = null;
            } else {
                bitmap = offlineItemVisuals.f10859a;
            }
            dh.D = bitmap;
            dh.E = offlineItem.k0;
            dh.F = offlineItem.j0;
            dh.G = offlineItem.M;
            dh.H = offlineItem.l0;
            DownloadInfo a2 = dh.a();
            int i3 = offlineItem.c0;
            if (i3 == 0) {
                this.H.h(a2, offlineItem.S, offlineItem.e0);
            } else if (i3 == 1) {
                this.H.g(a2);
            } else if (i3 == 2) {
                this.H.i(a2, -1, false, offlineItem.V);
            } else if (i3 == 4) {
                this.H.f(a2, !U70.b(offlineItem.F), offlineItem.k0);
            } else if (i3 == 5) {
                this.H.e(a2);
            } else if (i3 == 6) {
                this.H.g(a2);
            }
        }
    }

    @Override // defpackage.AI
    public void j(C0788My my, DownloadItem downloadItem, boolean z) {
        this.G.e(my, z);
    }

    public final boolean k(OfflineItem offlineItem) {
        if (offlineItem.O) {
            return false;
        }
        int i = offlineItem.c0;
        if (i == 0 || i == 1 || i == 2 || i == 4 || i == 6) {
            return true;
        }
        return false;
    }
}
