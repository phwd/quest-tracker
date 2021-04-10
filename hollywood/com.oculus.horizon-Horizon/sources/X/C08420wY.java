package X;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: X.0wY  reason: invalid class name and case insensitive filesystem */
public final class C08420wY {
    public final List<String> A00 = new ArrayList(20);

    public final void A01(String str) {
        int i = 0;
        while (true) {
            List<String> list = this.A00;
            if (i < list.size()) {
                if (str.equalsIgnoreCase(list.get(i))) {
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
        String str3;
        String str4;
        Object[] objArr;
        String str5;
        char charAt;
        if (str != null) {
            if (!str.isEmpty()) {
                int length = str.length();
                int i = 0;
                while (true) {
                    if (i < length) {
                        charAt = str.charAt(i);
                        if (charAt <= ' ' || charAt >= 127) {
                            objArr = new Object[]{Integer.valueOf(charAt), Integer.valueOf(i), str};
                            str5 = "Unexpected char %#04x at %d in header name: %s";
                        } else {
                            i++;
                        }
                    } else if (str2 != null) {
                        int length2 = str2.length();
                        for (int i2 = 0; i2 < length2; i2++) {
                            char charAt2 = str2.charAt(i2);
                            if (charAt2 <= 31) {
                                if (charAt2 != '\t') {
                                    objArr = new Object[]{Integer.valueOf(charAt2), Integer.valueOf(i2), str, str2};
                                    str5 = "Unexpected char %#04x at %d in %s value: %s";
                                }
                            } else if (charAt2 >= 127) {
                                objArr = new Object[]{Integer.valueOf(charAt2), Integer.valueOf(i2), str, str2};
                                str5 = "Unexpected char %#04x at %d in %s value: %s";
                            }
                        }
                        return;
                    } else {
                        str3 = "value == null";
                    }
                }
                objArr = new Object[]{Integer.valueOf(charAt), Integer.valueOf(i), str};
                str5 = "Unexpected char %#04x at %d in header name: %s";
                str4 = String.format(Locale.US, str5, objArr);
            } else {
                str4 = "name is empty";
            }
            throw new IllegalArgumentException(str4);
        }
        str3 = "name == null";
        throw new NullPointerException(str3);
    }

    public final void A02(String str, String str2) {
        List<String> list = this.A00;
        list.add(str);
        list.add(str2.trim());
    }
}
