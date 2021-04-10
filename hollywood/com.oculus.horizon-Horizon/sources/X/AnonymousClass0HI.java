package X;

/* renamed from: X.0HI  reason: invalid class name */
public class AnonymousClass0HI extends AbstractC03940gT {
    @Override // X.AbstractC03940gT
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
