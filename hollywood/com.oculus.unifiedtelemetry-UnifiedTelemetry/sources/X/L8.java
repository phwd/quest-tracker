package X;

import java.io.IOException;

public final class L8 implements Cdo {
    @Override // X.Cdo
    public final C0359dg A38(L3 l3) throws IOException {
        StringBuilder sb;
        String str;
        String str2;
        char charAt;
        System.currentTimeMillis();
        C0362dj djVar = l3.A01;
        C0362dj djVar2 = new dX(djVar).A00;
        dW dWVar = new dW(djVar2);
        if (dWVar.A00 != null) {
            e7 e7Var = djVar2.A05;
            if (e7Var == null) {
                String[] strArr = djVar2.A02.A00;
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
                        int A01 = dI.A01(str5, i7, "=,;");
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
                                i7 = dI.A01(str5, A01, ",;");
                                str2 = str5.substring(A01, i7).trim();
                            } else {
                                int i8 = A01 + 1;
                                int A012 = dI.A01(str5, i8, "\"");
                                str2 = str5.substring(i8, A012);
                                i7 = A012 + 1;
                            }
                        }
                        if ("no-cache".equalsIgnoreCase(trim)) {
                            z2 = true;
                        } else if ("no-store".equalsIgnoreCase(trim)) {
                            z3 = true;
                        } else if ("max-age".equalsIgnoreCase(trim)) {
                            i = dI.A00(str2, -1);
                        } else if ("s-maxage".equalsIgnoreCase(trim)) {
                            i2 = dI.A00(str2, -1);
                        } else if ("private".equalsIgnoreCase(trim)) {
                            z4 = true;
                        } else if ("public".equalsIgnoreCase(trim)) {
                            z5 = true;
                        } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                            z6 = true;
                        } else if ("max-stale".equalsIgnoreCase(trim)) {
                            i3 = dI.A00(str2, Integer.MAX_VALUE);
                        } else if ("min-fresh".equalsIgnoreCase(trim)) {
                            i4 = dI.A00(str2, -1);
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
                e7Var = new e7(z2, z3, i, i2, z4, z5, z6, i3, i4, z7, z8, str3);
                djVar2.A05 = e7Var;
            }
            if (e7Var.A0B) {
                dWVar = new dW(null);
            }
        }
        C0362dj djVar3 = dWVar.A00;
        if (djVar3 == null) {
            C0360dh dhVar = new C0360dh();
            dhVar.A07 = djVar;
            dhVar.A06 = EnumC0364dl.HTTP_1_1;
            dhVar.A00 = 504;
            dhVar.A03 = "Unsatisfiable Request (only-if-cached)";
            dhVar.A0B = dZ.A08;
            dhVar.A02 = -1;
            dhVar.A01 = System.currentTimeMillis();
            return dhVar.A00();
        }
        C0359dg A00 = l3.A00(djVar3);
        C0360dh dhVar2 = new C0360dh(A00);
        dhVar2.A08 = null;
        if (!(A00 == null || A00.A0B == null)) {
            C0360dh dhVar3 = new C0360dh(A00);
            dhVar3.A0B = null;
            A00 = dhVar3.A00();
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
        dhVar2.A09 = A00;
        C0359dg A002 = dhVar2.A00();
        dI.A03(A002);
        return A002;
    }
}
