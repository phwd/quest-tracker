package defpackage;

import android.os.Bundle;
import android.widget.RemoteViews;

/* renamed from: Fp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0345Fp0 {

    /* renamed from: a  reason: collision with root package name */
    public C0223Dp0 f8041a;

    public void a(Bundle bundle) {
        String c = c();
        if (c != null) {
            bundle.putString("androidx.core.app.extra.COMPAT_TEMPLATE", c);
        }
    }

    public abstract void b(C0406Gp0 gp0);

    public String c() {
        return null;
    }

    public RemoteViews d(C0406Gp0 gp0) {
        return null;
    }

    public RemoteViews e(C0406Gp0 gp0) {
        return null;
    }

    public void f(C0223Dp0 dp0) {
        if (this.f8041a != dp0) {
            this.f8041a = dp0;
            if (dp0.l != this) {
                dp0.l = this;
                f(dp0);
            }
        }
    }
}
