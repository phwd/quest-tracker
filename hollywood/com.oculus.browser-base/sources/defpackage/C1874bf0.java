package defpackage;

import androidx.mediarouter.app.MediaRouteActionProvider;
import java.lang.ref.WeakReference;

/* renamed from: bf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1874bf0 extends AbstractC0750Mg0 {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f9552a;

    public C1874bf0(MediaRouteActionProvider mediaRouteActionProvider) {
        this.f9552a = new WeakReference(mediaRouteActionProvider);
    }

    @Override // defpackage.AbstractC0750Mg0
    public void a(C3246jh0 jh0, C2051ch0 ch0) {
        k(jh0);
    }

    @Override // defpackage.AbstractC0750Mg0
    public void b(C3246jh0 jh0, C2051ch0 ch0) {
        k(jh0);
    }

    @Override // defpackage.AbstractC0750Mg0
    public void c(C3246jh0 jh0, C2051ch0 ch0) {
        k(jh0);
    }

    @Override // defpackage.AbstractC0750Mg0
    public void d(C3246jh0 jh0, C2392eh0 eh0) {
        k(jh0);
    }

    @Override // defpackage.AbstractC0750Mg0
    public void e(C3246jh0 jh0, C2392eh0 eh0) {
        k(jh0);
    }

    @Override // defpackage.AbstractC0750Mg0
    public void f(C3246jh0 jh0, C2392eh0 eh0) {
        k(jh0);
    }

    public final void k(C3246jh0 jh0) {
        MediaRouteActionProvider mediaRouteActionProvider = (MediaRouteActionProvider) this.f9552a.get();
        if (mediaRouteActionProvider != null) {
            mediaRouteActionProvider.i();
        } else {
            jh0.j(this);
        }
    }
}
