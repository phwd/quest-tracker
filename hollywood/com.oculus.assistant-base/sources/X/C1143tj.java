package X;

/* renamed from: X.tj  reason: case insensitive filesystem */
public final class C1143tj implements AbstractC0545bi {
    @Override // X.AbstractC0545bi
    public final C0554br A3L(C1138te teVar) {
        String str;
        char charAt;
        System.currentTimeMillis();
        C0551bo boVar = teVar.A01;
        C0551bo boVar2 = new C0563c0(boVar).A00;
        C0564c1 c1Var = new C0564c1(boVar2);
        if (c1Var.A00 != null) {
            C0527bQ bQVar = boVar2.A05;
            if (bQVar == null) {
                String[] strArr = boVar2.A02.A00;
                int length = strArr.length >> 1;
                boolean z = true;
                String str2 = null;
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
                    String str3 = strArr[i6];
                    String str4 = strArr[i6 + 1];
                    if (str3.equalsIgnoreCase("Cache-Control")) {
                        if (str2 == null) {
                            str2 = str4;
                        }
                        z = false;
                    } else {
                        if (!str3.equalsIgnoreCase("Pragma")) {
                        }
                        z = false;
                    }
                    int i7 = 0;
                    while (i7 < str4.length()) {
                        int A01 = C0574cD.A01(str4, i7, "=,;");
                        String trim = str4.substring(i7, A01).trim();
                        if (A01 == str4.length() || str4.charAt(A01) == ',' || str4.charAt(A01) == ';') {
                            i7 = A01 + 1;
                            str = null;
                        } else {
                            while (true) {
                                A01++;
                                if (A01 >= str4.length() || !((charAt = str4.charAt(A01)) == ' ' || charAt == '\t')) {
                                }
                            }
                            if (A01 >= str4.length() || str4.charAt(A01) != '\"') {
                                i7 = C0574cD.A01(str4, A01, ",;");
                                str = str4.substring(A01, i7).trim();
                            } else {
                                int i8 = A01 + 1;
                                int A012 = C0574cD.A01(str4, i8, "\"");
                                str = str4.substring(i8, A012);
                                i7 = A012 + 1;
                            }
                        }
                        if ("no-cache".equalsIgnoreCase(trim)) {
                            z2 = true;
                        } else if ("no-store".equalsIgnoreCase(trim)) {
                            z3 = true;
                        } else if ("max-age".equalsIgnoreCase(trim)) {
                            i = C0574cD.A00(str, -1);
                        } else if ("s-maxage".equalsIgnoreCase(trim)) {
                            i2 = C0574cD.A00(str, -1);
                        } else if ("private".equalsIgnoreCase(trim)) {
                            z4 = true;
                        } else if ("public".equalsIgnoreCase(trim)) {
                            z5 = true;
                        } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                            z6 = true;
                        } else if ("max-stale".equalsIgnoreCase(trim)) {
                            i3 = C0574cD.A00(str, Integer.MAX_VALUE);
                        } else if ("min-fresh".equalsIgnoreCase(trim)) {
                            i4 = C0574cD.A00(str, -1);
                        } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                            z7 = true;
                        } else if ("no-transform".equalsIgnoreCase(trim)) {
                            z8 = true;
                        }
                    }
                }
                if (!z) {
                    str2 = null;
                }
                bQVar = new C0527bQ(z2, z3, i, i2, z4, z5, z6, i3, i4, z7, z8, str2);
                boVar2.A05 = bQVar;
            }
            if (bQVar.A0B) {
                c1Var = new C0564c1(null);
            }
        }
        C0551bo boVar3 = c1Var.A00;
        if (boVar3 == null) {
            C0553bq bqVar = new C0553bq();
            bqVar.A07 = boVar;
            bqVar.A06 = EnumC0549bm.HTTP_1_1;
            bqVar.A00 = 504;
            bqVar.A03 = "Unsatisfiable Request (only-if-cached)";
            bqVar.A0B = C0561by.A04;
            bqVar.A02 = -1;
            bqVar.A01 = System.currentTimeMillis();
            return bqVar.A00();
        }
        C0554br A00 = teVar.A00(boVar3, teVar.A03, teVar.A04, teVar.A02);
        C0553bq bqVar2 = new C0553bq(A00);
        bqVar2.A08 = null;
        if (!(A00 == null || A00.A0B == null)) {
            C0553bq bqVar3 = new C0553bq(A00);
            bqVar3.A0B = null;
            A00 = bqVar3.A00();
        }
        if (A00 != null) {
            if (A00.A0B != null) {
                throw new IllegalArgumentException(AnonymousClass08.A04("networkResponse", ".body != null"));
            } else if (A00.A09 != null) {
                throw new IllegalArgumentException(AnonymousClass08.A04("networkResponse", ".networkResponse != null"));
            } else if (A00.A08 != null) {
                throw new IllegalArgumentException(AnonymousClass08.A04("networkResponse", ".cacheResponse != null"));
            } else if (A00.A0A != null) {
                throw new IllegalArgumentException(AnonymousClass08.A04("networkResponse", ".priorResponse != null"));
            }
        }
        bqVar2.A09 = A00;
        C0554br A002 = bqVar2.A00();
        C0574cD.A03(A002);
        return A002;
    }
}
