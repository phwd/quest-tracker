package defpackage;

import org.chromium.net.NetworkChangeNotifier;

/* renamed from: hH0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2843hH0 extends AbstractC2032cb {
    public final /* synthetic */ C3014iH0 i;

    public C2843hH0(C3014iH0 ih0) {
        this.i = ih0;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        try {
            return this.i.F.run();
        } catch (C4424qb e) {
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder(e.toString());
            for (Throwable cause = e.getCause(); cause != null; cause = cause.getCause()) {
                sb.append("\nCaused by: ");
                sb.append(cause.toString());
            }
            objArr[0] = sb.toString();
            AbstractC1220Ua0.f("OAuth2TokenService", "Failed to perform auth task: %s", objArr);
            this.i.H.set(e.F);
            return null;
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        if (obj != null) {
            this.i.F.a(obj);
        } else if (!this.i.H.get() || this.i.G.incrementAndGet() >= 3 || !NetworkChangeNotifier.b()) {
            C3014iH0 ih0 = this.i;
            ih0.F.b(ih0.H.get());
        } else {
            NetworkChangeNotifier.a(this.i);
        }
    }
}
