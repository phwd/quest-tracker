package defpackage;

import android.content.Context;
import android.media.MediaRouter;
import android.util.Log;
import android.view.Display;

/* renamed from: K51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class K51 extends J51 implements AbstractC4443qh0 {
    public K51(Context context, M51 m51) {
        super(context, m51);
    }

    @Override // defpackage.J51
    public void r(H51 h51, C0808Nf0 nf0) {
        Display display;
        super.r(h51, nf0);
        if (!((MediaRouter.RouteInfo) h51.f8136a).isEnabled()) {
            nf0.f8563a.putBoolean("enabled", false);
        }
        if (y(h51)) {
            nf0.c(1);
        }
        try {
            display = ((MediaRouter.RouteInfo) h51.f8136a).getPresentationDisplay();
        } catch (NoSuchMethodError e) {
            Log.w("MediaRouterJellybeanMr1", "Cannot get presentation display for the route.", e);
            display = null;
        }
        if (display != null) {
            nf0.f8563a.putInt("presentationDisplayId", display.getDisplayId());
        }
    }

    public abstract boolean y(H51 h51);
}
