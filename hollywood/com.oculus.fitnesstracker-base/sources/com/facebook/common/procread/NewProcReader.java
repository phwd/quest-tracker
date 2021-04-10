package com.facebook.common.procread;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import java.io.FileDescriptor;
import java.io.InterruptedIOException;
import java.util.HashSet;
import java.util.Set;

@TargetApi(26)
@Nullsafe(Nullsafe.Mode.LOCAL)
final class NewProcReader implements IProcReader {
    private final Set<String> mFilesCannotAccess = new HashSet();

    NewProcReader() {
    }

    @Override // com.facebook.common.procread.IProcReader
    public final boolean readProcFile(String str, int[] iArr, String[] strArr, long[] jArr, float[] fArr) {
        byte[] bArr = new byte[384];
        int readFile = readFile(str, bArr);
        if (readFile >= 0) {
            return parseProcLine(bArr, 0, readFile, iArr, strArr, jArr, fArr);
        }
        Log.i("NewProcReader", "Unable to open and read process file: " + str);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x004a A[SYNTHETIC] */
    @Override // com.facebook.common.procread.IProcReader
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean parseProcLine(byte[] r23, int r24, int r25, int[] r26, java.lang.String[] r27, long[] r28, float[] r29) {
        /*
        // Method dump skipped, instructions count: 304
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.procread.NewProcReader.parseProcLine(byte[], int, int, int[], java.lang.String[], long[], float[]):boolean");
    }

    private int readFile(String str, byte[] bArr) {
        if (this.mFilesCannotAccess.contains(str)) {
            return -1;
        }
        StrictMode.ThreadPolicy threadPolicy = null;
        if (Build.VERSION.SDK_INT >= 9) {
            threadPolicy = StrictMode.allowThreadDiskReads();
        }
        try {
            int readFileSystemOs = readFileSystemOs(str, bArr);
            if (readFileSystemOs == -2147483647) {
                this.mFilesCannotAccess.add(str);
                return -1;
            }
            if (threadPolicy != null && Build.VERSION.SDK_INT >= 9) {
                StrictMode.setThreadPolicy(threadPolicy);
            }
            return readFileSystemOs;
        } finally {
            if (threadPolicy != null && Build.VERSION.SDK_INT >= 9) {
                StrictMode.setThreadPolicy(threadPolicy);
            }
        }
    }

    private static int readFileSystemOs(String str, byte[] bArr) {
        int length = bArr.length;
        try {
            FileDescriptor open = Os.open(str, 0, 0);
            try {
                int read = Os.read(open, bArr, 0, length - 1);
                try {
                    Os.close(open);
                } catch (ErrnoException unused) {
                }
                return read;
            } catch (ErrnoException | InterruptedIOException e) {
                Log.i("NewProcReader", "Unable to read process file: " + str, e);
                try {
                    Os.close(open);
                } catch (ErrnoException unused2) {
                }
                return -2147483647;
            } catch (Throwable th) {
                try {
                    Os.close(open);
                } catch (ErrnoException unused3) {
                }
                throw th;
            }
        } catch (ErrnoException e2) {
            Log.i("NewProcReader", "Unable to raw open process file: " + str, e2);
            return -2147483647;
        }
    }

    private static int findChar(byte[] bArr, int i, char c, int i2) {
        while (i < bArr.length) {
            if (bArr[i] == 0) {
                return i;
            }
            i++;
        }
        return i2;
    }
}
