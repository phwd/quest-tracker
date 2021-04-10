package X;

import com.squareup.okhttp.HttpUrl;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: X.0la  reason: invalid class name and case insensitive filesystem */
public final class C05890la {
    public static final char[] A09 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public final int A00;
    public final String A01;
    public final String A02;
    public final String A03;
    public final String A04;
    public final List<String> A05;
    public final String A06;
    public final String A07;
    public final List<String> A08;

    public static int A00(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'a';
        if (c < 'a' || c > 'f') {
            c2 = 'A';
            if (c < 'A' || c > 'F') {
                return -1;
            }
        }
        return (c - c2) + 10;
    }

    public static String A03(String str, int i, int i2, boolean z) {
        int i3 = i;
        while (i3 < i2) {
            char charAt = str.charAt(i3);
            if (charAt == '%' || (charAt == '+' && z)) {
                AnonymousClass0HR r4 = new AnonymousClass0HR();
                r4.A0G(str, i, i3);
                while (i3 < i2) {
                    int codePointAt = str.codePointAt(i3);
                    if (codePointAt == 37) {
                        int i4 = i3 + 2;
                        if (i4 < i2) {
                            int A002 = A00(str.charAt(i3 + 1));
                            int A003 = A00(str.charAt(i4));
                            if (!(A002 == -1 || A003 == -1)) {
                                r4.A09((A002 << 4) + A003);
                                i3 = i4;
                            }
                        }
                        r4.A0C(codePointAt);
                    } else {
                        if (codePointAt == 43 && z) {
                            r4.A09(32);
                        }
                        r4.A0C(codePointAt);
                    }
                    i3 += Character.charCount(codePointAt);
                }
                return r4.A04();
            }
            i3++;
        }
        return str.substring(i, i2);
    }

    public static int A01(String str) {
        if (str.equals("http")) {
            return 80;
        }
        if (str.equals("https")) {
            return 443;
        }
        return -1;
    }

    public static List<String> A04(String str) {
        String str2;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int length = str.length();
            if (i > length) {
                return arrayList;
            }
            int indexOf = str.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = length;
            }
            int indexOf2 = str.indexOf(61, i);
            if (indexOf2 == -1 || indexOf2 > indexOf) {
                arrayList.add(str.substring(i, indexOf));
                str2 = null;
            } else {
                arrayList.add(str.substring(i, indexOf2));
                str2 = str.substring(indexOf2 + 1, indexOf);
            }
            arrayList.add(str2);
            i = indexOf + 1;
        }
    }

    public static boolean A06(String str, int i, int i2) {
        int i3 = i + 2;
        if (i3 >= i2 || str.charAt(i) != '%' || A00(str.charAt(i + 1)) == -1 || A00(str.charAt(i3)) == -1) {
            return false;
        }
        return true;
    }

    public final String A07() {
        if (this.A06.isEmpty()) {
            return "";
        }
        String str = this.A04;
        return str.substring(str.indexOf(58, this.A03.length() + 3) + 1, str.indexOf(64));
    }

    public final String A08() {
        if (this.A05 == null) {
            return null;
        }
        String str = this.A04;
        int indexOf = str.indexOf(63) + 1;
        int i = indexOf + 1;
        int length = str.length();
        while (true) {
            if (i < length) {
                if (str.charAt(i) == '#') {
                    break;
                }
                i++;
            } else {
                i = length;
                break;
            }
        }
        return str.substring(indexOf, i);
    }

    public final String A09() {
        if (this.A07.isEmpty()) {
            return "";
        }
        int length = this.A03.length() + 3;
        String str = this.A04;
        return str.substring(length, C05570jz.A02(str, length, str.length(), ":@"));
    }

    public final URI A0A() {
        String substring;
        C06000lm r0 = new C06000lm();
        String str = this.A03;
        r0.A05 = str;
        r0.A03 = A09();
        r0.A02 = A07();
        r0.A04 = this.A02;
        int i = this.A00;
        if (i == A01(str)) {
            i = -1;
        }
        r0.A00 = i;
        List<String> list = r0.A07;
        list.clear();
        list.addAll(A0B());
        r0.A04(A08());
        if (this.A01 == null) {
            substring = null;
        } else {
            String str2 = this.A04;
            substring = str2.substring(str2.indexOf(35) + 1);
        }
        r0.A01 = substring;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            String str3 = list.get(i2);
            list.set(i2, A02(str3, 0, str3.length(), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, true, true, false, true));
        }
        List<String> list2 = r0.A06;
        if (list2 != null) {
            int size2 = list2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                String str4 = r0.A06.get(i3);
                if (str4 != null) {
                    r0.A06.set(i3, A02(str4, 0, str4.length(), HttpUrl.QUERY_COMPONENT_ENCODE_SET_URI, true, true, true, true));
                }
            }
        }
        String str5 = r0.A01;
        if (str5 != null) {
            r0.A01 = A02(str5, 0, str5.length(), HttpUrl.FRAGMENT_ENCODE_SET_URI, true, true, false, false);
        }
        String r3 = r0.toString();
        try {
            return new URI(r3);
        } catch (URISyntaxException e) {
            try {
                return URI.create(r3.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                throw new RuntimeException(e);
            }
        }
    }

    public final List<String> A0B() {
        String str = this.A04;
        int indexOf = str.indexOf(47, this.A03.length() + 3);
        int A022 = C05570jz.A02(str, indexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (indexOf < A022) {
            int i = indexOf + 1;
            indexOf = i;
            while (true) {
                if (indexOf < A022) {
                    if (str.charAt(indexOf) == '/') {
                        break;
                    }
                    indexOf++;
                } else {
                    indexOf = A022;
                    break;
                }
            }
            arrayList.add(str.substring(i, indexOf));
        }
        return arrayList;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C05890la) || !((C05890la) obj).A04.equals(this.A04)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A04.hashCode();
    }

    public final String toString() {
        return this.A04;
    }

    public C05890la(C06000lm r5) {
        List<String> list;
        this.A03 = r5.A05;
        String str = r5.A03;
        this.A07 = A03(str, 0, str.length(), false);
        String str2 = r5.A02;
        this.A06 = A03(str2, 0, str2.length(), false);
        this.A02 = r5.A04;
        int i = r5.A00;
        this.A00 = i == -1 ? A01(r5.A05) : i;
        this.A08 = A05(r5.A07, false);
        List<String> list2 = r5.A06;
        String str3 = null;
        if (list2 != null) {
            list = A05(list2, true);
        } else {
            list = null;
        }
        this.A05 = list;
        String str4 = r5.A01;
        this.A01 = str4 != null ? A03(str4, 0, str4.length(), false) : str3;
        this.A04 = r5.toString();
    }

    public static List<String> A05(List<String> list, boolean z) {
        String str;
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            String str2 = list.get(i);
            if (str2 != null) {
                str = A03(str2, 0, str2.length(), z);
            } else {
                str = null;
            }
            arrayList.add(str);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static String A02(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        String str3;
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt >= 32 && codePointAt != 127 && ((codePointAt < 128 || !z4) && str2.indexOf(codePointAt) == -1)) {
                if (codePointAt == 37) {
                    if (z) {
                        if (z2 && !A06(str, i3, i2)) {
                        }
                    }
                } else if (codePointAt == 43 && z3) {
                }
                i3 += Character.charCount(codePointAt);
            }
            AnonymousClass0HR r3 = new AnonymousClass0HR();
            r3.A0G(str, i, i3);
            AnonymousClass0HR r5 = null;
            while (i3 < i2) {
                int codePointAt2 = str.codePointAt(i3);
                if (!z || !(codePointAt2 == 9 || codePointAt2 == 10 || codePointAt2 == 12 || codePointAt2 == 13)) {
                    if (codePointAt2 == 43 && z3) {
                        if (z) {
                            str3 = "+";
                        } else {
                            str3 = "%2B";
                        }
                        r3.A0F(str3);
                    } else if (codePointAt2 < 32 || codePointAt2 == 127 || ((codePointAt2 >= 128 && z4) || str2.indexOf(codePointAt2) != -1 || (codePointAt2 == 37 && (!z || (z2 && !A06(str, i3, i2)))))) {
                        if (r5 == null) {
                            r5 = new AnonymousClass0HR();
                        }
                        r5.A0C(codePointAt2);
                        while (!r5.A2T()) {
                            int readByte = r5.readByte() & 255;
                            r3.A09(37);
                            char[] cArr = A09;
                            r3.A09(cArr[(readByte >> 4) & 15]);
                            r3.A09(cArr[readByte & 15]);
                        }
                    } else {
                        r3.A0C(codePointAt2);
                    }
                }
                i3 += Character.charCount(codePointAt2);
            }
            return r3.A04();
        }
        return str.substring(i, i2);
    }
}
