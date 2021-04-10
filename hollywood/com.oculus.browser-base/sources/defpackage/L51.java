package defpackage;

import android.content.Context;
import android.media.MediaRouter;

/* renamed from: L51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class L51 extends K51 {
    public L51(Context context, M51 m51) {
        super(context, m51);
    }

    @Override // defpackage.J51
    public Object p() {
        return ((MediaRouter) this.l).getDefaultRoute();
    }

    @Override // defpackage.K51, defpackage.J51
    public void r(H51 h51, C0808Nf0 nf0) {
        super.r(h51, nf0);
        CharSequence description = ((MediaRouter.RouteInfo) h51.f8136a).getDescription();
        if (description != null) {
            nf0.f8563a.putString("status", description.toString());
        }
    }

    @Override // defpackage.J51
    public void t(Object obj) {
        ((MediaRouter) this.l).selectRoute(8388611, (MediaRouter.RouteInfo) obj);
    }

    @Override // defpackage.J51
    public void u() {
        if (this.r) {
            ((MediaRouter) this.l).removeCallback((MediaRouter.Callback) this.m);
        }
        this.r = true;
        Object obj = this.l;
        MediaRouter mediaRouter = (MediaRouter) obj;
        mediaRouter.addCallback(this.p, (MediaRouter.Callback) this.m, (this.q ? 1 : 0) | 2);
    }

    @Override // defpackage.J51
    public void x(I51 i51) {
        super.x(i51);
        ((MediaRouter.UserRouteInfo) i51.b).setDescription(i51.f8199a.e);
    }

    @Override // defpackage.K51
    public boolean y(H51 h51) {
        return ((MediaRouter.RouteInfo) h51.f8136a).isConnecting();
    }
}
