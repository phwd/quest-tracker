package defpackage;

import android.os.Process;
import java.util.HashMap;

/* renamed from: Rr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Rr1 {

    /* renamed from: a  reason: collision with root package name */
    public final long f8859a = ((long) Process.myTid());
    public HashMap b = new HashMap();

    public static void a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException("Neither key nor object of UserDataHost can be null.");
        }
    }

    public final void b() {
        if (this.f8859a != ((long) Process.myTid())) {
            throw new IllegalStateException("UserData must only be used on a single thread.");
        } else if (this.b == null) {
            throw new IllegalStateException("Operation is not allowed after destroy().");
        }
    }

    public Qr1 c(Class cls) {
        b();
        a(cls != null);
        return (Qr1) cls.cast(this.b.get(cls));
    }

    public Qr1 d(Class cls) {
        b();
        a(cls != null);
        if (this.b.containsKey(cls)) {
            return (Qr1) cls.cast(this.b.remove(cls));
        }
        throw new IllegalStateException("UserData for the key is not present.");
    }

    public Qr1 e(Class cls, Qr1 qr1) {
        b();
        a((cls == null || qr1 == null) ? false : true);
        this.b.put(cls, qr1);
        return c(cls);
    }
}
