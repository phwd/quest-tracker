package X;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: X.be  reason: case insensitive filesystem */
public final class C0541be {
    public final List A00 = new ArrayList(20);

    public final void A01(String str) {
        int i = 0;
        while (true) {
            List list = this.A00;
            if (i < list.size()) {
                if (str.equalsIgnoreCase((String) list.get(i))) {
                    list.remove(i);
                    list.remove(i);
                    i -= 2;
                }
                i += 2;
            } else {
                return;
            }
        }
    }

    public static void A00(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (!str.isEmpty()) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt <= ' ' || charAt >= 127) {
                    throw new IllegalArgumentException(String.format(Locale.US, "Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str));
                }
            }
            if (str2 != null) {
                int length2 = str2.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    char charAt2 = str2.charAt(i2);
                    if (charAt2 <= 31) {
                        if (charAt2 != '\t') {
                            throw new IllegalArgumentException(String.format(Locale.US, "Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt2), Integer.valueOf(i2), str, str2));
                        }
                    } else if (charAt2 >= 127) {
                        throw new IllegalArgumentException(String.format(Locale.US, "Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt2), Integer.valueOf(i2), str, str2));
                    }
                }
                return;
            }
            throw new NullPointerException("value == null");
        } else {
            throw new IllegalArgumentException("name is empty");
        }
    }

    public final void A02(String str, String str2) {
        List list = this.A00;
        list.add(str);
        list.add(str2.trim());
    }
}
