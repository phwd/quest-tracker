package X;

/* renamed from: X.r0  reason: case insensitive filesystem */
public abstract class AbstractC1029r0 extends OF {
    public final String A00(String str) {
        if (!(this instanceof V7)) {
            if (str == null) {
                return str;
            }
            int length = str.length();
            StringBuilder sb = new StringBuilder(length << 1);
            int i = 0;
            boolean z = false;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if (i2 > 0 || charAt != '_') {
                    if (Character.isUpperCase(charAt)) {
                        if (!z && i > 0 && sb.charAt(i - 1) != '_') {
                            sb.append('_');
                            i++;
                        }
                        charAt = Character.toLowerCase(charAt);
                        z = true;
                    } else {
                        z = false;
                    }
                    sb.append(charAt);
                    i++;
                }
            }
            if (i > 0) {
                return sb.toString();
            }
            return str;
        } else if (str == null || str.length() == 0) {
            return str;
        } else {
            char charAt2 = str.charAt(0);
            if (Character.isUpperCase(charAt2)) {
                return str;
            }
            StringBuilder sb2 = new StringBuilder(str);
            sb2.setCharAt(0, Character.toUpperCase(charAt2));
            return sb2.toString();
        }
    }
}
