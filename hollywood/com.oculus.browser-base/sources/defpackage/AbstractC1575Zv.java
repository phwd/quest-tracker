package defpackage;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: Zv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1575Zv {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicReference f9383a = new AtomicReference();

    public AbstractC1575Zv(AbstractC1392Wv wv) {
    }

    public static AbstractC1575Zv e() {
        return (AbstractC1575Zv) f9383a.get();
    }

    public static void h(String[] strArr) {
        AbstractC1575Zv zv = (AbstractC1575Zv) f9383a.getAndSet(new C1453Xv(strArr));
        if (zv != null) {
            zv.c();
        }
    }

    public static boolean i() {
        return f9383a.get() != null;
    }

    public abstract void a(String str);

    public abstract void b(String str, String str2);

    public void c() {
    }

    public abstract String[] d();

    public abstract String f(String str);

    public abstract boolean g(String str);
}
