package X;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.08Z  reason: invalid class name */
public final class AnonymousClass08Z {
    @Nullable
    public static File A00(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            String str = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
            for (int i = 0; i < 100; i++) {
                File file = new File(cacheDir, AnonymousClass006.A01(str, i));
                try {
                    if (file.createNewFile()) {
                        return file;
                    }
                } catch (IOException unused) {
                }
            }
        }
        return null;
    }

    @Nullable
    @RequiresApi(19)
    public static ByteBuffer A01(Context context, CancellationSignal cancellationSignal, Uri uri) {
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r", cancellationSignal);
            if (openFileDescriptor == null) {
                return null;
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
                try {
                    FileChannel channel = fileInputStream.getChannel();
                    MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                    fileInputStream.close();
                    openFileDescriptor.close();
                    return map;
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
            }
            throw th;
            throw th;
        } catch (IOException unused3) {
            return null;
        }
    }

    public static boolean A02(File file, Resources resources, int i) {
        InputStream openRawResource = resources.openRawResource(i);
        try {
            return A03(file, openRawResource);
        } finally {
            if (openRawResource != null) {
                try {
                    openRawResource.close();
                    throw th;
                } catch (IOException unused) {
                    throw th;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0042, code lost:
        if (r4 == null) goto L_0x0047;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003a A[SYNTHETIC, Splitter:B:23:0x003a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A03(java.io.File r7, java.io.InputStream r8) {
        /*
            android.os.StrictMode$ThreadPolicy r6 = android.os.StrictMode.allowThreadDiskWrites()
            r5 = 0
            r4 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0028 }
            r3.<init>(r7, r5)     // Catch:{ IOException -> 0x0028 }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r0]     // Catch:{ IOException -> 0x0025, all -> 0x0022 }
        L_0x000f:
            int r1 = r8.read(r2)     // Catch:{ IOException -> 0x0025, all -> 0x0022 }
            r0 = -1
            if (r1 == r0) goto L_0x001a
            r3.write(r2, r5, r1)     // Catch:{ IOException -> 0x0025, all -> 0x0022 }
            goto L_0x000f
        L_0x001a:
            r0 = 1
            r3.close()     // Catch:{ IOException -> 0x001e }
        L_0x001e:
            android.os.StrictMode.setThreadPolicy(r6)
            return r0
        L_0x0022:
            r0 = move-exception
            r4 = r3
            goto L_0x0044
        L_0x0025:
            r0 = move-exception
            r4 = r3
            goto L_0x0029
        L_0x0028:
            r0 = move-exception
        L_0x0029:
            java.lang.String r2 = "TypefaceCompatUtil"
            java.lang.String r1 = "Error copying resource contents to temp file: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0041 }
            java.lang.String r0 = X.AnonymousClass006.A05(r1, r0)     // Catch:{ all -> 0x0041 }
            android.util.Log.e(r2, r0)     // Catch:{ all -> 0x0041 }
            if (r4 == 0) goto L_0x003d
            r4.close()     // Catch:{ IOException -> 0x003d }
        L_0x003d:
            android.os.StrictMode.setThreadPolicy(r6)
            return r5
        L_0x0041:
            r0 = move-exception
            if (r4 == 0) goto L_0x0047
        L_0x0044:
            r4.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0047:
            android.os.StrictMode.setThreadPolicy(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass08Z.A03(java.io.File, java.io.InputStream):boolean");
    }
}
