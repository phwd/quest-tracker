package com.facebook.common.util;

public class MultimapUtil {
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <K, V> boolean equal(com.google.common.collect.Multimap<K, V> r6, com.google.common.collect.Multimap<K, V> r7) {
        /*
            int r0 = r6.size()
            int r1 = r7.size()
            r2 = 0
            if (r0 != r1) goto L_0x0043
            java.util.Set r0 = r6.keySet()
            java.util.Set r1 = r7.keySet()
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L_0x001a
            goto L_0x0043
        L_0x001a:
            java.util.Iterator r0 = r0.iterator()
        L_0x001e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0041
            java.lang.Object r1 = r0.next()
            java.util.Collection r3 = r6.get(r1)
            java.util.Collection r1 = r7.get(r1)
            int r4 = r3.size()
            int r5 = r1.size()
            if (r4 != r5) goto L_0x0040
            boolean r1 = r3.containsAll(r1)
            if (r1 != 0) goto L_0x001e
        L_0x0040:
            return r2
        L_0x0041:
            r6 = 1
            return r6
        L_0x0043:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.util.MultimapUtil.equal(com.google.common.collect.Multimap, com.google.common.collect.Multimap):boolean");
    }
}
