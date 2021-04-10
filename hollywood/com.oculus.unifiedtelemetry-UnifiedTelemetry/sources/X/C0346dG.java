package X;

/* renamed from: X.dG  reason: case insensitive filesystem */
public final class C0346dG {
    public static String A00(C0367dp dpVar) {
        String str = dpVar.A04;
        int indexOf = str.indexOf(47, dpVar.A03.length() + 3);
        String substring = str.substring(indexOf, dZ.A02(str, indexOf, str.length(), "?#"));
        String A09 = dpVar.A09();
        if (A09 != null) {
            return AnonymousClass06.A00(substring, '?', A09);
        }
        return substring;
    }
}
