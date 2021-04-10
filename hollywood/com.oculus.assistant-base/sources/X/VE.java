package X;

public final class VE extends C1018qn {
    public static final VE A00 = new VE();
    public static final String A01;
    public static final char[] A02;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (r0 == null) goto L_0x000f;
     */
    static {
        /*
            X.VE r0 = new X.VE
            r0.<init>()
            X.VE.A00 = r0
            java.lang.String r0 = "line.separator"
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch:{ all -> 0x000f }
            if (r0 != 0) goto L_0x0011
        L_0x000f:
            java.lang.String r0 = "\n"
        L_0x0011:
            X.VE.A01 = r0
            r0 = 64
            char[] r1 = new char[r0]
            X.VE.A02 = r1
            r0 = 32
            java.util.Arrays.fill(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.VE.<clinit>():void");
    }
}
