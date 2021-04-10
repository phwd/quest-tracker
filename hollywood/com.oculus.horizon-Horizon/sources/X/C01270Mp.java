package X;

import java.io.IOException;

/* renamed from: X.0Mp  reason: invalid class name and case insensitive filesystem */
public final class C01270Mp implements AbstractC08380wS {
    @Override // X.AbstractC08380wS
    public final C08220wC intercept(AbstractC08390wT r27) throws IOException {
        StringBuilder sb;
        String str;
        String str2;
        char charAt;
        System.currentTimeMillis();
        C08330wN A8H = r27.A8H();
        C08330wN r7 = new AnonymousClass0w3(A8H).A00;
        C08150w2 r4 = new C08150w2(r7);
        if (r4.A00 != null) {
            C08580wo r13 = r7.A05;
            if (r13 == null) {
                String[] strArr = r7.A02.A00;
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
                        int A01 = C08050vo.A01(str5, i7, "=,;");
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
                                i7 = C08050vo.A01(str5, A01, ",;");
                                str2 = str5.substring(A01, i7).trim();
                            } else {
                                int i8 = A01 + 1;
                                int A012 = C08050vo.A01(str5, i8, "\"");
                                str2 = str5.substring(i8, A012);
                                i7 = A012 + 1;
                            }
                        }
                        if ("no-cache".equalsIgnoreCase(trim)) {
                            z2 = true;
                        } else if ("no-store".equalsIgnoreCase(trim)) {
                            z3 = true;
                        } else if ("max-age".equalsIgnoreCase(trim)) {
                            i = C08050vo.A00(str2, -1);
                        } else if ("s-maxage".equalsIgnoreCase(trim)) {
                            i2 = C08050vo.A00(str2, -1);
                        } else if ("private".equalsIgnoreCase(trim)) {
                            z4 = true;
                        } else if ("public".equalsIgnoreCase(trim)) {
                            z5 = true;
                        } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                            z6 = true;
                        } else if ("max-stale".equalsIgnoreCase(trim)) {
                            i3 = C08050vo.A00(str2, Integer.MAX_VALUE);
                        } else if ("min-fresh".equalsIgnoreCase(trim)) {
                            i4 = C08050vo.A00(str2, -1);
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
                r13 = new C08580wo(z2, z3, i, i2, z4, z5, z6, i3, i4, z7, z8, str3);
                r7.A05 = r13;
            }
            if (r13.A0B) {
                r4 = new C08150w2(null);
            }
        }
        C08330wN r0 = r4.A00;
        if (r0 == null) {
            C08230wD r2 = new C08230wD();
            r2.A07 = A8H;
            r2.A06 = EnumC08350wP.HTTP_1_1;
            r2.A00 = 504;
            r2.A03 = "Unsatisfiable Request (only-if-cached)";
            r2.A0B = C08160w5.A08;
            r2.A02 = -1;
            r2.A01 = System.currentTimeMillis();
            return r2.A00();
        }
        C08220wC A7Z = r27.A7Z(r0);
        C08230wD r1 = new C08230wD(A7Z);
        r1.A08 = null;
        if (!(A7Z == null || A7Z.A0B == null)) {
            C08230wD r02 = new C08230wD(A7Z);
            r02.A0B = null;
            A7Z = r02.A00();
        }
        if (A7Z != null) {
            if (A7Z.A0B != null) {
                sb = new StringBuilder();
                sb.append("networkResponse");
                str = ".body != null";
            } else if (A7Z.A09 != null) {
                sb = new StringBuilder();
                sb.append("networkResponse");
                str = ".networkResponse != null";
            } else if (A7Z.A08 != null) {
                sb = new StringBuilder();
                sb.append("networkResponse");
                str = ".cacheResponse != null";
            } else if (A7Z.A0A != null) {
                sb = new StringBuilder();
                sb.append("networkResponse");
                str = ".priorResponse != null";
            }
            sb.append(str);
            throw new IllegalArgumentException(sb.toString());
        }
        r1.A09 = A7Z;
        C08220wC A00 = r1.A00();
        C08050vo.A03(A00);
        return A00;
    }
}
