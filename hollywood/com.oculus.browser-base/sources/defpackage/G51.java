package defpackage;

import android.media.MediaRouter;

/* renamed from: G51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class G51 extends AbstractC0385Gg0 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f8062a;

    public G51(Object obj) {
        this.f8062a = obj;
    }

    @Override // defpackage.AbstractC0385Gg0
    public void f(int i) {
        ((MediaRouter.RouteInfo) this.f8062a).requestSetVolume(i);
    }

    @Override // defpackage.AbstractC0385Gg0
    public void i(int i) {
        ((MediaRouter.RouteInfo) this.f8062a).requestUpdateVolume(i);
    }
}
