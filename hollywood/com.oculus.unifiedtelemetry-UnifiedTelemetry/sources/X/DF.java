package X;

public class DF extends AbstractC0218Wd {
    @Override // X.AbstractC0218Wd
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
