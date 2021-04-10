package defpackage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: eo1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2414eo1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Executor f9882a = new Xn1();
    public final C3781mo1 b;
    public final C2585fo1 c;
    public final C4294po1 d;

    public C2414eo1(C3781mo1 mo1, C2585fo1 fo1, C4294po1 po1) {
        this.b = mo1;
        this.c = fo1;
        this.d = po1;
    }

    public static void a(C2414eo1 eo1, Tn1 tn1, String str) {
        Objects.requireNonNull(eo1);
        Bundle bundle = new Bundle();
        bundle.putString("message", str);
        tn1.a("onNewLocationError", bundle);
        Objects.requireNonNull(eo1.d);
        AbstractC3364kK0.g("TrustedWebActivity.LocationUpdateErrorCode", 3, 4);
    }

    public void b(Uri uri, AbstractC2073co1 co1) {
        AbstractFutureC5208v90 v90;
        C4649rt0 a2 = C4649rt0.a(uri);
        if (a2 == null) {
            co1.b();
            return;
        }
        Set b2 = this.c.b(a2);
        if (b2 == null || b2.isEmpty()) {
            co1.b();
            return;
        }
        C3781mo1 mo1 = this.b;
        Executor executor = AbstractC2032cb.f9616a;
        ServiceConnectionC0786Mx mx = (ServiceConnectionC0786Mx) mo1.b.get(uri);
        if (mx != null) {
            v90 = mx.a();
        } else {
            Intent a3 = mo1.a(mo1.f10447a, uri, b2, true);
            if (a3 == null) {
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException("No service exists for scope");
                C2685gM0 gm0 = new C2685gM0();
                gm0.j(illegalArgumentException);
                v90 = gm0;
            } else {
                ServiceConnectionC0786Mx mx2 = new ServiceConnectionC0786Mx(new RunnableC3439ko1(mo1, uri));
                mo1.b.put(uri, mx2);
                new AsyncTaskC3610lo1(mo1.f10447a, a3, mx2).executeOnExecutor(executor, new Void[0]);
                v90 = mx2.a();
            }
        }
        v90.a(new Wn1(co1, a2, v90), f9882a);
    }

    public final void c(int i) {
        Objects.requireNonNull(this.d);
        AbstractC3364kK0.g("TrustedWebActivity.DelegatedNotificationSmallIconFallback", i, 4);
    }

    public boolean d(Uri uri) {
        Set b2;
        C4649rt0 a2 = C4649rt0.a(uri);
        boolean z = false;
        if (a2 == null || (b2 = this.c.b(a2)) == null) {
            return false;
        }
        C3781mo1 mo1 = this.b;
        if (mo1.b.get(uri) != null) {
            return true;
        }
        if (mo1.a(mo1.f10447a, uri, b2, false) != null) {
            z = true;
        }
        return z;
    }
}
