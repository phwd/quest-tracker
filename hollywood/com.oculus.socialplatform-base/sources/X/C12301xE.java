package X;

/* renamed from: X.1xE  reason: invalid class name and case insensitive filesystem */
public final class C12301xE {
    public static final Throwable A00 = new C12311xF();

    public static RuntimeException A00(Throwable th) {
        if (th instanceof Error) {
            throw th;
        } else if (th instanceof RuntimeException) {
            return (RuntimeException) th;
        } else {
            return new RuntimeException(th);
        }
    }
}
