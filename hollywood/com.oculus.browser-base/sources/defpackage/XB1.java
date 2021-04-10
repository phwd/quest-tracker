package defpackage;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* renamed from: XB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class XB1 implements WV, XV {

    /* renamed from: a  reason: collision with root package name */
    public final C2470f7 f9198a;
    public final boolean b;
    public AbstractC1624aC1 c;

    public XB1(C2470f7 f7Var, boolean z) {
        this.f9198a = f7Var;
        this.b = z;
    }

    public final void a() {
        SE0.i(this.c, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }

    @Override // defpackage.AbstractC0482Hx
    public final void c(int i) {
        a();
        this.c.c(i);
    }

    @Override // defpackage.AbstractC0777Ms0
    public final void e0(ConnectionResult connectionResult) {
        a();
        this.c.d(connectionResult, this.f9198a, this.b);
    }

    @Override // defpackage.AbstractC0482Hx
    public final void f(Bundle bundle) {
        a();
        this.c.f(bundle);
    }
}
