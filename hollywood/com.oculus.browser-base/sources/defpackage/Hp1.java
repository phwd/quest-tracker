package defpackage;

import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* renamed from: Hp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Hp1 {

    /* renamed from: a  reason: collision with root package name */
    public final C1881bh1 f8184a;
    public Handler b;
    public HashMap c;

    public Hp1() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            Handler handler = new Handler(myLooper);
            this.f8184a = new C1881bh1();
            this.c = new HashMap();
            this.b = handler;
            return;
        }
        throw new IllegalStateException();
    }

    public Fp1 a(Ip1 ip1) {
        this.f8184a.a();
        WeakReference weakReference = (WeakReference) this.c.get(ip1);
        if (weakReference == null) {
            return null;
        }
        Fp1 fp1 = (Fp1) weakReference.get();
        if (fp1 != null) {
            return (Fp1) ip1.f8251a.cast(fp1);
        }
        ip1.c(this);
        return null;
    }
}
