package X;

import java.io.IOException;

public final class EZ implements XS {
    @Override // X.XS
    public final XK A29(EU eu) throws IOException {
        StringBuilder sb;
        String str;
        String str2;
        char charAt;
        System.currentTimeMillis();
        XN xn = eu.A01;
        XN xn2 = new XB(xn).A00;
        XA xa = new XA(xn2);
        if (xa.A00 != null) {
            C0186Xl xl = xn2.A05;
            if (xl == null) {
                String[] strArr = xn2.A02.A00;
                int length = strArr.length >> 1;
                boolean z = true;
                String str3 = null;
                boolean z2 = false;
                boolean z3 = false;
                int i = -1;
                int i2 = -1;
                boolean z4 = false;
                boolean z5 = false;
                boolean z6 = false;
                int i3 = -1;
                int i4 = -1;
                boolean z7 = false;
                boolean z8 = false;
                for (int i5 = 0; i5 < length; i5++) {
                    int i6 = i5 << 1;
                    String str4 = strArr[i6];
                    String str5 = strArr[i6 + 1];
                    if (str4.equalsIgnoreCase("Cache-Control")) {
                        if (str3 == null) {
                            str3 = str5;
                        }
                        z = false;
                    } else {
                        if (!str4.equalsIgnoreCase("Pragma")) {
                        }
                        z = false;
                    }
                    int i7 = 0;
                    while (i7 < str5.length()) {
                        int A01 = C0171Ww.A01(str5, i7, "=,;");
                        String trim = str5.substring(i7, A01).trim();
                        if (A01 == str5.length() || str5.charAt(A01) == ',' || str5.charAt(A01) == ';') {
                            i7 = A01 + 1;
                            str2 = null;
                        } else {
                            while (true) {
                                A01++;
                                if (A01 >= str5.length() || !((charAt = str5.charAt(A01)) == ' ' || charAt == '\t')) {
                                }
                            }
                            if (A01 >= str5.length() || str5.charAt(A01) != '\"') {
                                i7 = C0171Ww.A01(str5, A01, ",;");
                                str2 = str5.substring(A01, i7).trim();
                            } else {
                                int i8 = A01 + 1;
                                int A012 = C0171Ww.A01(str5, i8, "\"");
                                str2 = str5.substring(i8, A012);
                                i7 = A012 + 1;
                            }
                        }
                        if ("no-cache".equalsIgnoreCase(trim)) {
                            z2 = true;
                        } else if ("no-store".equalsIgnoreCase(trim)) {
                            z3 = true;
                        } else if ("max-age".equalsIgnoreCase(trim)) {
                            i = C0171Ww.A00(str2, -1);
                        } else if ("s-maxage".equalsIgnoreCase(trim)) {
                            i2 = C0171Ww.A00(str2, -1);
                        } else if ("private".equalsIgnoreCase(trim)) {
                            z4 = true;
                        } else if ("public".equalsIgnoreCase(trim)) {
                            z5 = true;
                        } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                            z6 = true;
                        } else if ("max-stale".equalsIgnoreCase(trim)) {
                            i3 = C0171Ww.A00(str2, Integer.MAX_VALUE);
                        } else if ("min-fresh".equalsIgnoreCase(trim)) {
                            i4 = C0171Ww.A00(str2, -1);
                        } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                            z7 = true;
                        } else if ("no-transform".equalsIgnoreCase(trim)) {
                            z8 = true;
                        }
                    }
                }
                if (!z) {
                    str3 = null;
                }
                xl = new C0186Xl(z2, z3, i, i2, z4, z5, z6, i3, i4, z7, z8, str3);
                xn2.A05 = xl;
            }
            if (xl.A0B) {
                xa = new XA(null);
            }
        }
        XN xn3 = xa.A00;
        if (xn3 == null) {
            XL xl2 = new XL();
            xl2.A07 = xn;
            xl2.A06 = XP.HTTP_1_1;
            xl2.A00 = 504;
            xl2.A03 = "Unsatisfiable Request (only-if-cached)";
            xl2.A0B = XD.A08;
            xl2.A02 = -1;
            xl2.A01 = System.currentTimeMillis();
            return xl2.A00();
        }
        XK A00 = eu.A00(xn3);
        XL xl3 = new XL(A00);
        xl3.A08 = null;
        if (!(A00 == null || A00.A0B == null)) {
            XL xl4 = new XL(A00);
            xl4.A0B = null;
            A00 = xl4.A00();
        }
        if (A00 != null) {
            if (A00.A0B != null) {
                sb = new StringBuilder();
                sb.append("networkResponse");
                str = ".body != null";
            } else if (A00.A09 != null) {
                sb = new StringBuilder();
                sb.append("networkResponse");
                str = ".networkResponse != null";
            } else if (A00.A08 != null) {
                sb = new StringBuilder();
                sb.append("networkResponse");
                str = ".cacheResponse != null";
            } else if (A00.A0A != null) {
                sb = new StringBuilder();
                sb.append("networkResponse");
                str = ".priorResponse != null";
            }
            sb.append(str);
            throw new IllegalArgumentException(sb.toString());
        }
        xl3.A09 = A00;
        XK A002 = xl3.A00();
        C0171Ww.A03(A002);
        return A002;
    }
}
