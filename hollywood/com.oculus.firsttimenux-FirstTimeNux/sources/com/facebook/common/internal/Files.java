package com.facebook.common.internal;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.InputStream;

@Nullsafe(Nullsafe.Mode.STRICT)
public class Files {
    private Files() {
    }

    static byte[] readFile(InputStream in, long expectedSize) throws IOException {
        if (expectedSize > 2147483647L) {
            throw new OutOfMemoryError("file is too large to fit in a byte array: " + expectedSize + " bytes");
        } else if (expectedSize == 0) {
            return ByteStreams.toByteArray(in);
        } else {
            return ByteStreams.toByteArray(in, (int) expectedSize);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] toByteArray(java.io.File r4) throws java.io.IOException {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0018 }
            r1.<init>(r4)     // Catch:{ all -> 0x0018 }
            java.nio.channels.FileChannel r2 = r1.getChannel()     // Catch:{ all -> 0x001f }
            long r2 = r2.size()     // Catch:{ all -> 0x001f }
            byte[] r2 = readFile(r1, r2)     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x0017
            r1.close()
        L_0x0017:
            return r2
        L_0x0018:
            r2 = move-exception
        L_0x0019:
            if (r0 == 0) goto L_0x001e
            r0.close()
        L_0x001e:
            throw r2
        L_0x001f:
            r2 = move-exception
            r0 = r1
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.internal.Files.toByteArray(java.io.File):byte[]");
    }
}
