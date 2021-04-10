package com.facebook.acra.util;

import X.C0070Kj;
import X.Mi;
import android.os.Process;
import com.facebook.acra.util.ProcFileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.annotation.Nullable;

public class JavaProcFileReader extends ProcFileReader {
    public static final String FD_DIR = String.format("/proc/%s/fd", Integer.valueOf(Process.myPid()));
    public static final String FD_DIR_STRING = "/fd/";
    public static final String LS_SYMLINK_ARROW = " -> ";
    public static final String PIPE_STRING = "pipe";
    public static final int[] PROC_OPEN_FD_LIMITS_FORMAT = {32, 32, 288, 4384, 4384, 288};
    public static final String SOCKET_STRING = "socket";
    public static final String TAG = "JavaProcFileReader";
    public static JavaProcFileReader sInstance;

    public static int findNewLineOrEnd(byte[] bArr, int i) {
        int length = bArr.length;
        if (i >= length) {
            return -1;
        }
        while (i < length - 1 && bArr[i] != 10 && bArr[i] != 0) {
            i++;
        }
        return i;
    }

    public static boolean startsWithOffset(byte[] bArr, int i, byte[] bArr2) {
        int length = bArr.length - i;
        int length2 = bArr2.length;
        if (length >= length2) {
            for (int i2 = 0; i2 < length2; i2++) {
                if (bArr[i2 + i] == bArr2[i2]) {
                }
            }
            return true;
        }
        return false;
    }

    public static synchronized JavaProcFileReader getInstance() {
        JavaProcFileReader javaProcFileReader;
        synchronized (JavaProcFileReader.class) {
            javaProcFileReader = sInstance;
            if (javaProcFileReader == null) {
                javaProcFileReader = new JavaProcFileReader();
                sInstance = javaProcFileReader;
            }
        }
        return javaProcFileReader;
    }

    @Override // com.facebook.acra.util.ProcFileReader
    public int getOpenFDCount() {
        try {
            String[] list = new File(FD_DIR).list();
            if (list != null) {
                return list.length;
            }
            return -1;
        } catch (SecurityException e) {
            Mi.A00(TAG, e.getMessage());
            return -2;
        }
    }

    @Override // com.facebook.acra.util.ProcFileReader
    @Nullable
    public ProcFileReader.OpenFDLimits getOpenFDLimits() {
        byte[] bArr = new byte[HttpRequestMultipart.STREAM_BLOCK_SIZE];
        byte[] bytes = "Max open files".getBytes();
        String[] strArr = new String[2];
        try {
            FileInputStream fileInputStream = new FileInputStream("/proc/self/limits");
            try {
                int read = fileInputStream.read(bArr, 0, 8191);
                bArr[read] = 0;
                fileInputStream.close();
                int i = 0;
                while ((read - 1) - i > bytes.length) {
                    int findNewLineOrEnd = findNewLineOrEnd(bArr, i);
                    if (startsWithOffset(bArr, i, bytes)) {
                        if (C0070Kj.A00.A2m(bArr, i, findNewLineOrEnd, PROC_OPEN_FD_LIMITS_FORMAT, strArr, null, null)) {
                            return new ProcFileReader.OpenFDLimits(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]));
                        }
                        return null;
                    }
                    i = findNewLineOrEnd + 1;
                }
                return null;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            Mi.A07(TAG, e, "Failed to read /proc/self/limits");
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ac, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ad, code lost:
        X.Mi.A06(com.facebook.acra.util.JavaProcFileReader.TAG, r2, "Exception caught while reading open file descriptors");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b8, code lost:
        return r2.getMessage();
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ac A[ExcHandler: IOException | IndexOutOfBoundsException | SecurityException (r2v4 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:12:0x0057] */
    @Override // com.facebook.acra.util.ProcFileReader
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getOpenFileDescriptors() {
        /*
        // Method dump skipped, instructions count: 185
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.util.JavaProcFileReader.getOpenFileDescriptors():java.lang.String");
    }

    public static class Counter {
        public int count;

        public Counter() {
        }

        public /* synthetic */ Counter(AnonymousClass1 r1) {
        }
    }
}
