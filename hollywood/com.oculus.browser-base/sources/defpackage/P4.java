package defpackage;

/* renamed from: P4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class P4 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class f8663a;
    public static final boolean b;

    static {
        Class<?> cls;
        Class<?> cls2 = null;
        try {
            cls = Class.forName("libcore.io.Memory");
        } catch (Throwable unused) {
            cls = null;
        }
        f8663a = cls;
        try {
            cls2 = Class.forName("org.robolectric.Robolectric");
        } catch (Throwable unused2) {
        }
        b = cls2 != null;
    }

    public static boolean a() {
        return f8663a != null && !b;
    }
}
