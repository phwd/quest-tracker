package X;

import io.reactivex.functions.BiPredicate;

/* renamed from: X.219  reason: invalid class name */
public final class AnonymousClass219 {
    public static final BiPredicate<Object, Object> A00 = new C141021q();

    public static void A00(int i, String str) {
        if (i <= 0) {
            throw new IllegalArgumentException(AnonymousClass006.A08(str, " > 0 required but it was ", i));
        }
    }

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(TT;Ljava/lang/String;)TT; */
    public static void A01(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }
}
