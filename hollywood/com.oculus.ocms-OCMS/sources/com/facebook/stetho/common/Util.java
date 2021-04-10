package com.facebook.stetho.common;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Util {
    public static <T> T throwIfNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static <T1, T2> void throwIfNull(T1 t1, T2 t2) {
        throwIfNull(t1);
        throwIfNull(t2);
    }

    public static <T1, T2, T3> void throwIfNull(T1 t1, T2 t2, T3 t3) {
        throwIfNull(t1);
        throwIfNull(t2);
        throwIfNull(t3);
    }

    public static void throwIfNotNull(Object obj) {
        if (obj != null) {
            throw new IllegalStateException();
        }
    }

    public static void throwIf(boolean z) {
        if (z) {
            throw new IllegalStateException();
        }
    }

    public static void throwIfNot(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void throwIfNot(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(String.format(str, objArr));
        }
    }

    public static void copy(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static void close(Closeable closeable, boolean z) throws IOException {
        if (closeable == null) {
            return;
        }
        if (z) {
            try {
                closeable.close();
            } catch (IOException e) {
                LogUtil.e(e, "Hiding IOException because another is pending");
            }
        } else {
            closeable.close();
        }
    }

    public static void sleepUninterruptibly(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        do {
            try {
                Thread.sleep(j);
                return;
            } catch (InterruptedException unused) {
                j -= System.currentTimeMillis() - currentTimeMillis;
                if (j <= 0) {
                }
            }
        } while (j <= 0);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:0|1|3|2) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:1:?, LOOP_START, SYNTHETIC, Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void joinUninterruptibly(java.lang.Thread r0) {
        /*
        L_0x0000:
            r0.join()     // Catch:{ InterruptedException -> 0x0000 }
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.common.Util.joinUninterruptibly(java.lang.Thread):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:0|1|3|2) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:1:?, LOOP_START, SYNTHETIC, Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void awaitUninterruptibly(java.util.concurrent.CountDownLatch r0) {
        /*
        L_0x0000:
            r0.await()     // Catch:{ InterruptedException -> 0x0000 }
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.common.Util.awaitUninterruptibly(java.util.concurrent.CountDownLatch):void");
    }

    public static <T> T getUninterruptibly(Future<T> future, long j, TimeUnit timeUnit) throws TimeoutException, ExecutionException {
        long millis = timeUnit.toMillis(j);
        while (true) {
            try {
                return future.get(millis, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused) {
                millis -= System.currentTimeMillis() - System.currentTimeMillis();
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:4:? */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.concurrent.Future<T>] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.concurrent.Future] */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:0|1|3|2) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:1:?, LOOP_START, PHI: r0 
      PHI: (r0v1 ??) = (r0v0 ??), (r0v2 ??) binds: [B:4:?, B:1:?] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T getUninterruptibly(java.util.concurrent.Future<T> r0) throws java.util.concurrent.ExecutionException {
        /*
        L_0x0000:
            java.lang.Object r0 = r0.get()     // Catch:{ InterruptedException -> 0x0000 }
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.common.Util.getUninterruptibly(java.util.concurrent.Future):java.lang.Object");
    }

    public static String readAsUTF8(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(inputStream, byteArrayOutputStream, new byte[1024]);
        return byteArrayOutputStream.toString(Utf8Charset.NAME);
    }
}
