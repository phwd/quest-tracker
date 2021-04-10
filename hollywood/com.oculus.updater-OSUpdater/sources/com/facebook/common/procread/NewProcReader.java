package com.facebook.common.procread;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ultralight.UL;
import java.io.FileDescriptor;
import java.io.InterruptedIOException;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

@TargetApi(UL.id._UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_BINDING_ID)
@Nullsafe(Nullsafe.Mode.LOCAL)
final class NewProcReader implements IProcReader {
    private final Set<String> mFilesCannotAccess = new HashSet();

    static NewProcReader create() {
        return new NewProcReader();
    }

    NewProcReader() {
    }

    @Override // com.facebook.common.procread.IProcReader
    public boolean readProcFile(String str, int[] iArr, @Nullable String[] strArr, @Nullable long[] jArr, @Nullable float[] fArr) {
        byte[] bArr = new byte[256];
        int readFile = readFile(str, bArr);
        if (readFile >= 0) {
            return parseProcLine(bArr, 0, readFile, iArr, strArr, jArr, fArr);
        }
        Log.i("NewProcReader", "Unable to open and read process file: " + str);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x003e A[SYNTHETIC] */
    @Override // com.facebook.common.procread.IProcReader
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean parseProcLine(byte[] r21, int r22, int r23, int[] r24, @javax.annotation.Nullable java.lang.String[] r25, @javax.annotation.Nullable long[] r26, @javax.annotation.Nullable float[] r27) {
        /*
        // Method dump skipped, instructions count: 288
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
            if (bArr[i] == c) {
                return i;
            }
            i++;
        }
        return i2;
    }
}
