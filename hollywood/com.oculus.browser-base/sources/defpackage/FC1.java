package defpackage;

/* renamed from: FC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class FC1 {

    /* renamed from: a  reason: collision with root package name */
    public static FC1 f8001a;

    public static synchronized FC1 b() {
        FC1 fc1;
        synchronized (FC1.class) {
            if (f8001a == null) {
                f8001a = new FC1();
            }
            fc1 = f8001a;
        }
        return fc1;
    }

    public final RC1 a() {
        return new RC1(Boolean.TRUE, null);
    }
}
