package X;

/* renamed from: X.0vm  reason: invalid class name and case insensitive filesystem */
public final class C08030vm {
    public static String A00(C08400wU r4) {
        String str = r4.A04;
        int indexOf = str.indexOf(47, r4.A03.length() + 3);
        String substring = str.substring(indexOf, C08160w5.A02(str, indexOf, str.length(), "?#"));
        String A09 = r4.A09();
        if (A09 != null) {
            return AnonymousClass006.A00(substring, '?', A09);
        }
        return substring;
    }
}
