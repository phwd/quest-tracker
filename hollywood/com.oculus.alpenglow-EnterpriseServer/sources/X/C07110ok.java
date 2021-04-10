package X;

import java.util.HashSet;

/* renamed from: X.0ok  reason: invalid class name and case insensitive filesystem */
public final class C07110ok {
    public AnonymousClass0ZV A00 = null;
    public AnonymousClass0ZU A01 = null;
    public AnonymousClass0ZT A02 = null;
    public AnonymousClass0ZS A03 = null;
    public AnonymousClass0ZR A04 = null;
    public AnonymousClass0ZQ A05 = null;
    public AnonymousClass0ZP A06 = null;

    public static <T> HashSet<T> A00(T[] tArr) {
        HashSet<T> hashSet = new HashSet<>();
        if (tArr != null) {
            for (T t : tArr) {
                hashSet.add(t);
            }
        }
        return hashSet;
    }
}
