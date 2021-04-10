package X;

/* renamed from: X.0L6  reason: invalid class name */
public class AnonymousClass0L6 extends AbstractC02520aA {
    @Override // X.AbstractC02520aA
    public final String A04(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(0, Character.toUpperCase(charAt));
        return sb.toString();
    }
}
