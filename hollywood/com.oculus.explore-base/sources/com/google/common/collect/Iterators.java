package com.google.common.collect;

public final class Iterators {
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0007  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean elementsEqual(java.util.Iterator<?> r4, java.util.Iterator<?> r5) {
        /*
            r2 = 0
        L_0x0001:
            boolean r3 = r4.hasNext()
            if (r3 == 0) goto L_0x001d
            boolean r3 = r5.hasNext()
            if (r3 != 0) goto L_0x000e
        L_0x000d:
            return r2
        L_0x000e:
            java.lang.Object r0 = r4.next()
            java.lang.Object r1 = r5.next()
            boolean r3 = com.google.common.base.Objects.equal(r0, r1)
            if (r3 != 0) goto L_0x0001
            goto L_0x000d
        L_0x001d:
            boolean r3 = r5.hasNext()
            if (r3 != 0) goto L_0x000d
            r2 = 1
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Iterators.elementsEqual(java.util.Iterator, java.util.Iterator):boolean");
    }
}
