package defpackage;

import android.media.MediaRouter;

/* renamed from: ph0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4272ph0 extends MediaRouter.VolumeCallback {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC4101oh0 f11082a;

    public C4272ph0(AbstractC4101oh0 oh0) {
        this.f11082a = oh0;
    }

    public void onVolumeSetRequest(MediaRouter.RouteInfo routeInfo, int i) {
        this.f11082a.b(routeInfo, i);
    }

    public void onVolumeUpdateRequest(MediaRouter.RouteInfo routeInfo, int i) {
        this.f11082a.a(routeInfo, i);
    }
}
