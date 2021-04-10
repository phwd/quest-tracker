package X;

/* renamed from: X.0Si  reason: invalid class name */
public class AnonymousClass0Si extends AnonymousClass0i5 {
    @Override // X.AnonymousClass0i5
    public final String A04(String str) {
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
    }
}
