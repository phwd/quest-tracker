package X;

/* renamed from: X.0Cs  reason: invalid class name */
public final class AnonymousClass0Cs {
    public static final int A00;

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
        if (r0 == -1) goto L_0x004a;
     */
    static {
        /*
            java.lang.String r0 = "java.version"
            java.lang.String r5 = java.lang.System.getProperty(r0)
            java.lang.String r0 = "[._]"
            java.lang.String[] r3 = r5.split(r0)     // Catch:{ NumberFormatException -> 0x0020 }
            r0 = 0
            r0 = r3[r0]     // Catch:{ NumberFormatException -> 0x0020 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0020 }
            r2 = 1
            if (r0 != r2) goto L_0x0021
            int r1 = r3.length     // Catch:{ NumberFormatException -> 0x0020 }
            if (r1 <= r2) goto L_0x0021
            r0 = r3[r2]     // Catch:{ NumberFormatException -> 0x0020 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0020 }
            goto L_0x0021
        L_0x0020:
            r0 = -1
        L_0x0021:
            r4 = -1
            if (r0 != r4) goto L_0x0048
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x004a }
            r3.<init>()     // Catch:{ NumberFormatException -> 0x004a }
            r2 = 0
        L_0x002a:
            int r0 = r5.length()     // Catch:{ NumberFormatException -> 0x004a }
            if (r2 >= r0) goto L_0x0040
            char r1 = r5.charAt(r2)     // Catch:{ NumberFormatException -> 0x004a }
            boolean r0 = java.lang.Character.isDigit(r1)     // Catch:{ NumberFormatException -> 0x004a }
            if (r0 == 0) goto L_0x0040
            r3.append(r1)     // Catch:{ NumberFormatException -> 0x004a }
            int r2 = r2 + 1
            goto L_0x002a
        L_0x0040:
            java.lang.String r0 = r3.toString()     // Catch:{ NumberFormatException -> 0x004a }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x004a }
        L_0x0048:
            if (r0 != r4) goto L_0x004b
        L_0x004a:
            r0 = 6
        L_0x004b:
            X.AnonymousClass0Cs.A00 = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Cs.<clinit>():void");
    }
}
