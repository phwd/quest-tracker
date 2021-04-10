package defpackage;

import android.content.Context;
import android.media.MediaRouter;

/* renamed from: F51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F51 extends L51 {
    public F51(Context context, M51 m51) {
        super(context, m51);
    }

    @Override // defpackage.K51, defpackage.J51, defpackage.L51
    public void r(H51 h51, C0808Nf0 nf0) {
        super.r(h51, nf0);
        nf0.f8563a.putInt("deviceType", ((MediaRouter.RouteInfo) h51.f8136a).getDeviceType());
    }
}
