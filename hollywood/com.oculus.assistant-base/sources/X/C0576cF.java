package X;

/* renamed from: X.cF  reason: case insensitive filesystem */
public final class C0576cF {
    public static String A00(C0544bh bhVar) {
        String str = bhVar.A04;
        int indexOf = str.indexOf(47, bhVar.A03.length() + 3);
        String substring = str.substring(indexOf, C0561by.A02(str, indexOf, str.length(), "?#"));
        String A08 = bhVar.A08();
        if (A08 == null) {
            return substring;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(substring);
        sb.append('?');
        sb.append(A08);
        return sb.toString();
    }
}
