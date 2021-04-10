package defpackage;

import android.os.Looper;
import com.google.android.gms.location.LocationRequest;

/* renamed from: aJ1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1645aJ1 extends AC1 {
    public final /* synthetic */ LocationRequest q;
    public final /* synthetic */ AbstractC0489Ia0 r;
    public final /* synthetic */ Looper s;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1645aJ1(YV yv, LocationRequest locationRequest, AbstractC0489Ia0 ia0, Looper looper) {
        super(yv);
        this.q = locationRequest;
        this.r = ia0;
        this.s = looper;
    }

    @Override // defpackage.AbstractC4439qg
    public final void j(Z6 z6) {
        C3176jE1 je1 = (C3176jE1) z6;
        HC1 hc1 = new HC1(this);
        LocationRequest locationRequest = this.q;
        AbstractC0489Ia0 ia0 = this.r;
        Looper looper = this.s;
        if (looper == null) {
            SE0.k(Looper.myLooper() != null, "Can't create handler inside thread that has not called Looper.prepare()");
            looper = Looper.myLooper();
        }
        String simpleName = AbstractC0489Ia0.class.getSimpleName();
        SE0.i(ia0, "Listener must not be null");
        SE0.i(looper, "Looper must not be null");
        SE0.i(simpleName, "Listener type must not be null");
        C5718y90 y90 = new C5718y90(looper, ia0, simpleName);
        synchronized (je1.F) {
            je1.F.b(locationRequest, y90, hc1);
        }
    }
}
