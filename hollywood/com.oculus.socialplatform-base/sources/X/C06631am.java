package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1am  reason: invalid class name and case insensitive filesystem */
public final class C06631am {
    public static final AtomicReference<byte[]> A00 = new AtomicReference<>();

    /* JADX WARNING: Can't wrap try/catch for region: R(7:3|4|5|6|7|8|19) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0029 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(@androidx.annotation.NonNull java.nio.ByteBuffer r4, @androidx.annotation.NonNull java.io.File r5) throws java.io.IOException {
        /*
            r3 = 0
            r4.position(r3)
            r2 = 0
            java.lang.String r0 = "rw"
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ all -> 0x002d }
            r1.<init>(r5, r0)     // Catch:{ all -> 0x002d }
            java.nio.channels.FileChannel r2 = r1.getChannel()     // Catch:{ all -> 0x0023 }
            r2.write(r4)     // Catch:{ all -> 0x0023 }
            r2.force(r3)     // Catch:{ all -> 0x0023 }
            r2.close()     // Catch:{ all -> 0x0023 }
            r1.close()     // Catch:{ all -> 0x0023 }
            r2.close()     // Catch:{ IOException -> 0x001f }
        L_0x001f:
            r1.close()     // Catch:{ IOException -> 0x0022 }
        L_0x0022:
            return
        L_0x0023:
            r0 = move-exception
            if (r2 == 0) goto L_0x0029
            r2.close()     // Catch:{ IOException -> 0x0029 }
        L_0x0029:
            r1.close()     // Catch:{ IOException -> 0x002e }
            throw r0
        L_0x002d:
            r0 = move-exception
        L_0x002e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06631am.A00(java.nio.ByteBuffer, java.io.File):void");
    }
}
