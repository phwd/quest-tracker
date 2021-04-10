package X;

import androidx.annotation.NonNull;
import java.io.File;
import java.nio.ByteBuffer;

/* renamed from: X.1al  reason: invalid class name and case insensitive filesystem */
public final class C06621al implements AbstractC07051bX<ByteBuffer> {
    public final File A00;

    @Override // X.AbstractC07051bX
    public final void A26() {
    }

    @Override // X.AbstractC07051bX
    public final void cancel() {
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<ByteBuffer> A3h() {
        return ByteBuffer.class;
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final AnonymousClass1fM A3i() {
        return AnonymousClass1fM.LOCAL;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:8|9|10|11|12|13|14|15|16|18) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x002d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x003c */
    @Override // X.AbstractC07051bX
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A6H(@androidx.annotation.NonNull X.AnonymousClass1cY r9, @androidx.annotation.NonNull X.AnonymousClass1Ry<? super java.nio.ByteBuffer> r10) {
        /*
            r8 = this;
            java.io.File r3 = r8.A00     // Catch:{ IOException -> 0x0052 }
            long r6 = r3.length()     // Catch:{ all -> 0x0050 }
            r1 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r0 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r0 > 0) goto L_0x0048
            r1 = 0
            int r0 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x0040
            java.lang.String r0 = "r"
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ all -> 0x0050 }
            r1.<init>(r3, r0)     // Catch:{ all -> 0x0050 }
            java.nio.channels.FileChannel r2 = r1.getChannel()     // Catch:{ all -> 0x003b }
            java.nio.channels.FileChannel$MapMode r3 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ all -> 0x0034 }
            r4 = 0
            java.nio.MappedByteBuffer r0 = r2.map(r3, r4, r6)     // Catch:{ all -> 0x0034 }
            java.nio.MappedByteBuffer r0 = r0.load()     // Catch:{ all -> 0x0034 }
            r2.close()     // Catch:{ IOException -> 0x002d }
        L_0x002d:
            r1.close()     // Catch:{ IOException -> 0x0030 }
        L_0x0030:
            r10.A6x(r0)
            return
        L_0x0034:
            r0 = move-exception
            if (r2 == 0) goto L_0x003c
            r2.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x003c
        L_0x003b:
            r0 = move-exception
        L_0x003c:
            r1.close()     // Catch:{ IOException -> 0x0051 }
            goto L_0x0051
        L_0x0040:
            java.lang.String r1 = "File unsuitable for memory mapping"
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r1)
            throw r0
        L_0x0048:
            java.lang.String r1 = "File too large to map into memory"
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r1)
            throw r0
        L_0x0050:
            r0 = move-exception
        L_0x0051:
            throw r0
        L_0x0052:
            r0 = move-exception
            r10.A7F(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06621al.A6H(X.1cY, X.1Ry):void");
    }

    public C06621al(File file) {
        this.A00 = file;
    }
}
