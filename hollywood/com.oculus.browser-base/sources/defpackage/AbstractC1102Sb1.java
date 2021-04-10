package defpackage;

import java.io.File;

/* renamed from: Sb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1102Sb1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f8901a = new Object();
    public static File b;

    public static File a() {
        synchronized (f8901a) {
            if (b == null) {
                b = new File(AbstractC1041Rb1.f8842a, "0");
                P21 g0 = P21.g0();
                try {
                    if (!b.exists() && !b.mkdirs()) {
                        AbstractC1220Ua0.a("tabpersistence", "Failed to create state folder: " + b, new Object[0]);
                    }
                    g0.close();
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            }
        }
        return b;
        throw th;
    }
}
