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
import javax.annotation.Nullable;

@TargetApi(26)
@Nullsafe(Nullsafe.Mode.LOCAL)
final class NewProcReader implements IProcReader {
    private static final int ERROR_LEN = -1;
    private static final int FILE_NOT_EXIST_ERROR_LEN = -2147483647;
    private static final int READ_PROC_FILE_BUFFER_SIZE = 256;
    private static final int READ_PROC_LINE_BUFFER_SIZE = 2048;
    private static final String TAG = "NewProcReader";
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
        Log.i(TAG, "Unable to open and read process file: " + str);
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

    @Override // com.facebook.common.procread.IProcReader
    public boolean readProcLines(String str, String[] strArr, long[] jArr) {
        boolean z;
        int i;
        boolean z2;
        if (str == null || strArr == null || jArr == null) {
            throw new NullPointerException("Cannot pass null values");
        }
        int length = strArr.length;
        if (length <= jArr.length) {
            for (int i2 = 0; i2 < length; i2++) {
                jArr[i2] = 0;
            }
            byte[] bArr = new byte[2048];
            int readFile = readFile(str, bArr);
            if (readFile < 0) {
                Log.i(TAG, "Unable to read " + str);
                z = false;
                readFile = 0;
            } else {
                z = true;
            }
            int length2 = bArr.length;
            if (readFile < length2) {
                bArr[readFile] = 0;
            }
            int i3 = 0;
            int i4 = 0;
            while (i3 < readFile && bArr[i3] != 0 && i4 < length) {
                int i5 = 0;
                while (true) {
                    if (i5 >= length) {
                        i = i3;
                        z2 = true;
                        break;
                    }
                    String str2 = strArr[i5];
                    if (bufferEquals(bArr, i3, str2)) {
                        int length3 = i3 + str2.length();
                        while (length3 < length2 && (bArr[length3] == 0 || bArr[length3] == 32 || bArr[length3] == 9)) {
                            length3++;
                        }
                        i = length3;
                        while (i < length2 && bArr[i] >= 48 && bArr[i] <= 57) {
                            i++;
                        }
                        z2 = i >= length2 || bArr[i] != 10;
                        if (i < length2 && bArr[i] != 0) {
                            bArr[i] = 0;
                            i++;
                        }
                        jArr[i5] = ByteParse.strtoll(bArr, length3, 10, null);
                        i4++;
                    } else {
                        i5++;
                    }
                }
                if (z2) {
                    while (i < readFile && bArr[i] != 0 && bArr[i] != 10) {
                        i++;
                    }
                    if (i < length2 && bArr[i] == 10) {
                        i++;
                    }
                }
                i3 = i;
            }
            return z;
        }
        throw new IllegalArgumentException("Array lengths differ");
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
            if (readFileSystemOs == FILE_NOT_EXIST_ERROR_LEN) {
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
                Log.i(TAG, "Unable to read process file: " + str, e);
                try {
                    Os.close(open);
                } catch (ErrnoException unused2) {
                }
                return FILE_NOT_EXIST_ERROR_LEN;
            } catch (Throwable th) {
                try {
                    Os.close(open);
                } catch (ErrnoException unused3) {
                }
                throw th;
            }
        } catch (ErrnoException e2) {
            Log.i(TAG, "Unable to raw open process file: " + str, e2);
            return FILE_NOT_EXIST_ERROR_LEN;
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

    private static boolean bufferEquals(byte[] bArr, int i, String str) {
        int length = str.length();
        int i2 = 0;
        while (i < bArr.length && i2 < length) {
            if (bArr[i] != str.charAt(i2)) {
                return false;
            }
            i++;
            i2++;
        }
        if (i2 == length) {
            return true;
        }
        return false;
    }
}
