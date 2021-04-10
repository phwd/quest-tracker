package defpackage;

import java.lang.ref.WeakReference;
import org.chromium.base.Callback;

/* renamed from: Vs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1326Vs0 {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f9112a;
    public final WeakReference b;
    public final Callback c;

    public C1326Vs0(AbstractC0956Pq0 pq0, Callback callback) {
        C1265Us0 us0 = new C1265Us0(this, null);
        this.f9112a = us0;
        this.b = new WeakReference(pq0);
        this.c = callback;
        ((C1078Rq0) pq0).l(us0);
    }
}
