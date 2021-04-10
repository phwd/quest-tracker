package defpackage;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import java.lang.ref.WeakReference;

/* renamed from: LA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class LA1 implements AbstractC3071ig {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f8411a;
    public final C2470f7 b;
    public final boolean c;

    public LA1(JA1 ja1, C2470f7 f7Var, boolean z) {
        this.f8411a = new WeakReference(ja1);
        this.b = f7Var;
        this.c = z;
    }

    @Override // defpackage.AbstractC3071ig
    public final void a(ConnectionResult connectionResult) {
        JA1 ja1 = (JA1) this.f8411a.get();
        if (ja1 != null) {
            SE0.k(Looper.myLooper() == ja1.f8277a.m.g, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            ja1.b.lock();
            try {
                if (ja1.n(0)) {
                    if (!connectionResult.A()) {
                        ja1.l(connectionResult, this.b, this.c);
                    }
                    if (ja1.a()) {
                        ja1.h();
                    }
                    ja1.b.unlock();
                }
            } finally {
                ja1.b.unlock();
            }
        }
    }
}
