package X;

/* renamed from: X.Mp  reason: case insensitive filesystem */
public enum EnumC0242Mp {
    ANY,
    NON_PRIVATE,
    PROTECTED_AND_PUBLIC,
    PUBLIC_ONLY,
    NONE,
    DEFAULT;

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
        if (java.lang.reflect.Modifier.isProtected(r5.getModifiers()) != false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        if (r3 != 5) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isVisible(java.lang.reflect.Member r5) {
        /*
            r4 = this;
            int[] r1 = X.Mo.A00
            int r0 = r4.ordinal()
            r3 = r1[r0]
            r2 = 1
            if (r3 == r2) goto L_0x002d
            r0 = 2
            r1 = 0
            if (r3 == r0) goto L_0x0018
            r0 = 3
            if (r3 == r0) goto L_0x0019
            r0 = 4
            if (r3 == r0) goto L_0x0023
            r0 = 5
            if (r3 == r0) goto L_0x002e
        L_0x0018:
            return r1
        L_0x0019:
            int r0 = r5.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isPrivate(r0)
            r0 = r0 ^ r2
            return r0
        L_0x0023:
            int r0 = r5.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isProtected(r0)
            if (r0 == 0) goto L_0x002e
        L_0x002d:
            return r2
        L_0x002e:
            int r0 = r5.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isPublic(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.EnumC0242Mp.isVisible(java.lang.reflect.Member):boolean");
    }
}
