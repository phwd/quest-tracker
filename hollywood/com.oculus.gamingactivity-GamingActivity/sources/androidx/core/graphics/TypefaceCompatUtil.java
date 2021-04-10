package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    @Nullable
    public static File getTempFile(Context context) {
        String prefix = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i = 0; i < 100; i++) {
            File file = new File(context.getCacheDir(), prefix + i);
            try {
                if (file.createNewFile()) {
                    return file;
                }
            } catch (IOException e) {
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002e, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0042, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0043, code lost:
        r2 = null;
     */
    @androidx.annotation.Nullable
    @androidx.annotation.RequiresApi(19)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.nio.ByteBuffer mmap(java.io.File r11) {
        /*
            r8 = 0
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0024 }
            r7.<init>(r11)     // Catch:{ IOException -> 0x0024 }
            r9 = 0
            java.nio.channels.FileChannel r0 = r7.getChannel()     // Catch:{ Throwable -> 0x002b, all -> 0x0042 }
            long r4 = r0.size()     // Catch:{ Throwable -> 0x002b, all -> 0x0042 }
            java.nio.channels.FileChannel$MapMode r1 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ Throwable -> 0x002b, all -> 0x0042 }
            r2 = 0
            java.nio.MappedByteBuffer r1 = r0.map(r1, r2, r4)     // Catch:{ Throwable -> 0x002b, all -> 0x0042 }
            if (r7 == 0) goto L_0x001e
            if (r8 == 0) goto L_0x0027
            r7.close()     // Catch:{ Throwable -> 0x001f }
        L_0x001e:
            return r1
        L_0x001f:
            r2 = move-exception
            r9.addSuppressed(r2)
            goto L_0x001e
        L_0x0024:
            r6 = move-exception
            r1 = r8
            goto L_0x001e
        L_0x0027:
            r7.close()
            goto L_0x001e
        L_0x002b:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x002d }
        L_0x002d:
            r2 = move-exception
            r10 = r2
            r2 = r1
            r1 = r10
        L_0x0031:
            if (r7 == 0) goto L_0x0038
            if (r2 == 0) goto L_0x003e
            r7.close()     // Catch:{ Throwable -> 0x0039 }
        L_0x0038:
            throw r1
        L_0x0039:
            r3 = move-exception
            r2.addSuppressed(r3)
            goto L_0x0038
        L_0x003e:
            r7.close()
            goto L_0x0038
        L_0x0042:
            r1 = move-exception
            r2 = r8
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatUtil.mmap(java.io.File):java.nio.ByteBuffer");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0057, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0058, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0067, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0068, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0070, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0071, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x008e, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x008f, code lost:
        r2 = null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0067 A[ExcHandler: all (th java.lang.Throwable)] */
    @androidx.annotation.Nullable
    @androidx.annotation.RequiresApi(19)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.ByteBuffer mmap(android.content.Context r13, android.os.CancellationSignal r14, android.net.Uri r15) {
        /*
        // Method dump skipped, instructions count: 145
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatUtil.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    @Nullable
    @RequiresApi(19)
    public static ByteBuffer copyToDirectBuffer(Context context, Resources res, int id) {
        File tmpFile = getTempFile(context);
        if (tmpFile == null) {
            return null;
        }
        try {
            if (!copyToFile(tmpFile, res, id)) {
                return null;
            }
            ByteBuffer mmap = mmap(tmpFile);
            tmpFile.delete();
            return mmap;
        } finally {
            tmpFile.delete();
        }
    }

    public static boolean copyToFile(File file, InputStream is) {
        Throwable th;
        FileOutputStream os = null;
        StrictMode.ThreadPolicy old = StrictMode.allowThreadDiskWrites();
        try {
            FileOutputStream os2 = new FileOutputStream(file, false);
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    int readLen = is.read(buffer);
                    if (readLen != -1) {
                        os2.write(buffer, 0, readLen);
                    } else {
                        closeQuietly(os2);
                        StrictMode.setThreadPolicy(old);
                        return true;
                    }
                }
            } catch (IOException e) {
                e = e;
                os = os2;
                try {
                    Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
                    closeQuietly(os);
                    StrictMode.setThreadPolicy(old);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    closeQuietly(os);
                    StrictMode.setThreadPolicy(old);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                os = os2;
                closeQuietly(os);
                StrictMode.setThreadPolicy(old);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
            closeQuietly(os);
            StrictMode.setThreadPolicy(old);
            return false;
        }
    }

    public static boolean copyToFile(File file, Resources res, int id) {
        InputStream is = null;
        try {
            is = res.openRawResource(id);
            return copyToFile(file, is);
        } finally {
            closeQuietly(is);
        }
    }

    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }
}
