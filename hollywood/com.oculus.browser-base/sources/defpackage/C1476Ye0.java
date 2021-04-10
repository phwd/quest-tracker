package defpackage;

import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

/* renamed from: Ye0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1476Ye0 extends MediaRouter2.TransferCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1537Ze0 f9285a;

    public C1476Ye0(C1537Ze0 ze0) {
        this.f9285a = ze0;
    }

    public void onStop(MediaRouter2.RoutingController routingController) {
        C2392eh0 c;
        AbstractC0385Gg0 gg0 = (AbstractC0385Gg0) this.f9285a.k.remove(routingController);
        if (gg0 != null) {
            C1360Wg0 wg0 = this.f9285a.j;
            C1543Zg0 zg0 = wg0.f9165a;
            if (gg0 == zg0.s && wg0.f9165a.g() != (c = zg0.c())) {
                wg0.f9165a.l(c, 2);
                return;
            }
            return;
        }
        Log.w("MR2Provider", "onStop: No matching routeController found. routingController=" + routingController);
    }

    public void onTransfer(MediaRouter2.RoutingController routingController, MediaRouter2.RoutingController routingController2) {
        C2392eh0 eh0;
        this.f9285a.k.remove(routingController);
        if (routingController2 == this.f9285a.i.getSystemController()) {
            C1360Wg0 wg0 = this.f9285a.j;
            C2392eh0 c = wg0.f9165a.c();
            if (wg0.f9165a.g() != c) {
                wg0.f9165a.l(c, 3);
                return;
            }
            return;
        }
        List selectedRoutes = routingController2.getSelectedRoutes();
        if (selectedRoutes.isEmpty()) {
            Log.w("MR2Provider", "Selected routes are empty. This shouldn't happen.");
            return;
        }
        String id = ((MediaRoute2Info) selectedRoutes.get(0)).getId();
        this.f9285a.k.put(routingController2, new C1293Ve0(this.f9285a, routingController2, id));
        C1360Wg0 wg02 = this.f9285a.j;
        Iterator it = wg02.f9165a.e.iterator();
        while (true) {
            if (!it.hasNext()) {
                eh0 = null;
                break;
            }
            eh0 = (C2392eh0) it.next();
            if (eh0.d() == wg02.f9165a.c && TextUtils.equals(id, eh0.b)) {
                break;
            }
        }
        if (eh0 == null) {
            Log.w("MediaRouter", "onSelectRoute: The target RouteInfo is not found for descriptorId=" + id);
        } else {
            wg02.f9165a.l(eh0, 3);
        }
        this.f9285a.k(routingController2);
    }

    public void onTransferFailure(MediaRoute2Info mediaRoute2Info) {
        Log.w("MR2Provider", "Transfer failed. requestedRoute=" + mediaRoute2Info);
    }
}
