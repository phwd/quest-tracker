package X;

/* renamed from: X.Wu  reason: case insensitive filesystem */
public final class C0169Wu {
    public static String A00(XT xt) {
        String str = xt.A04;
        int indexOf = str.indexOf(47, xt.A03.length() + 3);
        String substring = str.substring(indexOf, XD.A02(str, indexOf, str.length(), "?#"));
        String A08 = xt.A08();
        if (A08 != null) {
            return AnonymousClass06.A00(substring, '?', A08);
        }
        return substring;
    }
}
