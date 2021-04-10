package X;

/* renamed from: X.0o  reason: invalid class name */
public final class AnonymousClass0o extends C00070p {
    public static /* synthetic */ int A00(CharSequence charSequence, CharSequence charSequence2, int i) {
        int length = charSequence.length();
        if (i > length) {
            i = length;
        }
        C1199v0 v0Var = new C1199v0(0, i);
        if (!(charSequence instanceof String) || !(charSequence2 instanceof String)) {
            int i2 = v0Var.A00;
            int i3 = v0Var.A01;
            int i4 = v0Var.A02;
            if (i2 <= i3) {
                while (true) {
                    int length2 = charSequence2.length();
                    if (i2 >= 0 && 0 <= charSequence2.length() - length2 && i2 <= charSequence.length() - length2) {
                        for (int i5 = 0; i5 < length2; i5++) {
                            if (charSequence2.charAt(0 + i5) == charSequence.charAt(i2 + i5)) {
                            }
                        }
                        return i2;
                    }
                    if (i2 == i3) {
                        break;
                    }
                    i2 += i4;
                }
            }
        } else {
            int i6 = v0Var.A00;
            int i7 = v0Var.A01;
            int i8 = v0Var.A02;
            if (i6 <= i7) {
                while (true) {
                    String str = (String) charSequence2;
                    String str2 = (String) charSequence;
                    int length3 = charSequence2.length();
                    C0514bB.A02(str, "$this$regionMatches");
                    C0514bB.A02(str2, "other");
                    if (str.regionMatches(0, str2, i6, length3)) {
                        return i6;
                    }
                    if (i6 == i7) {
                        break;
                    }
                    i6 += i8;
                }
            }
        }
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0033, code lost:
        if (r4 == -1) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ java.lang.String A01(java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0o.A01(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0028 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ boolean A02(java.lang.CharSequence r3, java.lang.CharSequence r4) {
        /*
            java.lang.String r0 = "$this$contains"
            X.C0514bB.A02(r3, r0)
            java.lang.String r0 = "other"
            X.C0514bB.A02(r4, r0)
            boolean r0 = r4 instanceof java.lang.String
            r2 = 1
            if (r0 == 0) goto L_0x002a
            java.lang.String r4 = (java.lang.String) r4
            r1 = 0
            java.lang.String r0 = "$this$indexOf"
            X.C0514bB.A02(r3, r0)
            java.lang.String r0 = "string"
            X.C0514bB.A02(r4, r0)
            boolean r0 = r3 instanceof java.lang.String
            if (r0 == 0) goto L_0x002a
            java.lang.String r3 = (java.lang.String) r3
            int r0 = r3.indexOf(r4, r1)
        L_0x0026:
            if (r0 >= 0) goto L_0x0029
            r2 = 0
        L_0x0029:
            return r2
        L_0x002a:
            int r0 = r3.length()
            int r0 = A00(r3, r4, r0)
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0o.A02(java.lang.CharSequence, java.lang.CharSequence):boolean");
    }
}
