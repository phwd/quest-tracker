package X;

/* renamed from: X.0i2  reason: invalid class name */
public final class AnonymousClass0i2 {
    public static String A00(C05890la r4) {
        String str = r4.A04;
        int indexOf = str.indexOf(47, r4.A03.length() + 3);
        String substring = str.substring(indexOf, C05570jz.A02(str, indexOf, str.length(), "?#"));
        String A08 = r4.A08();
        if (A08 != null) {
            return AnonymousClass006.A00(substring, '?', A08);
        }
        return substring;
    }
}
