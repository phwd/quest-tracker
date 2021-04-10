package X;

import java.io.FileFilter;

public final class NF {
    public static final FileFilter A00 = new NE();

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
        if (r2 != null) goto L_0x003e;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int A00(java.lang.String r3) {
        /*
            r0 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x003b, all -> 0x0033 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x003b, all -> 0x0033 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ IOException -> 0x003b, all -> 0x0033 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x003b, all -> 0x0033 }
            java.lang.String r1 = r0.readLine()     // Catch:{ IOException -> 0x003b, all -> 0x0033 }
            r0.close()     // Catch:{ IOException -> 0x003b, all -> 0x0033 }
            if (r1 == 0) goto L_0x0031
            java.lang.String r0 = "0-[\\d]+$"
            boolean r0 = r1.matches(r0)     // Catch:{ IOException -> 0x003b, all -> 0x0033 }
            if (r0 == 0) goto L_0x0031
            r0 = 2
            java.lang.String r0 = r1.substring(r0)     // Catch:{ IOException -> 0x003b, all -> 0x0033 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x003b, all -> 0x0033 }
            int r0 = r0.intValue()     // Catch:{ IOException -> 0x003b, all -> 0x0033 }
            int r0 = r0 + 1
            goto L_0x003e
        L_0x0031:
            r0 = -1
            goto L_0x003e
        L_0x0033:
            r0 = move-exception
            r2.close()     // Catch:{ IOException -> 0x0039 }
            throw r0
        L_0x0038:
            r0 = move-exception
        L_0x0039:
            throw r0
        L_0x003a:
            r2 = r0
        L_0x003b:
            r0 = -1
            if (r2 == 0) goto L_0x0041
        L_0x003e:
            r2.close()     // Catch:{ IOException -> 0x0041 }
        L_0x0041:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.NF.A00(java.lang.String):int");
    }
}
