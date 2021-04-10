package com.facebook.acra.util;

import android.os.Process;
import com.facebook.acra.util.ProcFileReader;
import com.facebook.common.procread.ProcReader;
import com.facebook.debug.log.BLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public class JavaProcFileReader extends ProcFileReader {
    private static final String FD_DIR = String.format("/proc/%s/fd", Integer.valueOf(Process.myPid()));
    private static final String FD_DIR_STRING = "/fd/";
    private static final String LS_SYMLINK_ARROW = " -> ";
    private static final String PIPE_STRING = "pipe";
    private static final int[] PROC_OPEN_FD_LIMITS_FORMAT = {32, 32, 288, 4384, 4384, 288};
    private static final String SOCKET_STRING = "socket";
    private static final String TAG = "JavaProcFileReader";
    private static JavaProcFileReader sInstance = null;

    private static class Counter {
        public int count;

        private Counter() {
        }
    }

    public static synchronized JavaProcFileReader getInstance() {
        JavaProcFileReader javaProcFileReader;
        synchronized (JavaProcFileReader.class) {
            if (sInstance == null) {
                sInstance = new JavaProcFileReader();
            }
            javaProcFileReader = sInstance;
        }
        return javaProcFileReader;
    }

    private JavaProcFileReader() {
    }

    @Override // com.facebook.acra.util.ProcFileReader
    public String getOpenFileDescriptors() {
        String str;
        StringBuilder sb = new StringBuilder();
        try {
            String[] split = CommandOutputCollector.collect("/system/bin/ls", "-l", String.format(FD_DIR, new Object[0])).split("\n");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (int i = 1; i < split.length; i++) {
                String str2 = split[i];
                int lastIndexOf = str2.lastIndexOf(LS_SYMLINK_ARROW);
                if (lastIndexOf != -1) {
                    String substring = str2.substring(lastIndexOf + 4);
                    int lastIndexOf2 = substring.lastIndexOf(FD_DIR_STRING);
                    if (lastIndexOf2 == -1) {
                        str = substring;
                    } else {
                        str = substring.substring(lastIndexOf2 + 4);
                    }
                    if (str.startsWith(PIPE_STRING)) {
                        substring = PIPE_STRING;
                    } else if (str.startsWith(SOCKET_STRING)) {
                        substring = SOCKET_STRING;
                    }
                    Counter counter = (Counter) linkedHashMap.get(substring);
                    if (counter == null) {
                        counter = new Counter();
                        linkedHashMap.put(substring, counter);
                    }
                    counter.count++;
                }
            }
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                sb.append((String) entry.getKey());
                sb.append(" ");
                sb.append(((Counter) entry.getValue()).count);
                sb.append("\n");
            }
            return sb.toString();
        } catch (IOException | IndexOutOfBoundsException | SecurityException e) {
            BLog.e(TAG, e, "Exception caught while reading open file descriptors");
            return e.getMessage();
        }
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
            BLog.e(TAG, e.getMessage());
            return -2;
        }
    }

    private static int findNewLineOrEnd(byte[] bArr, int i) {
        if (i >= bArr.length) {
            return -1;
        }
        while (i < bArr.length - 1 && bArr[i] != 10 && bArr[i] != 0) {
            i++;
        }
        return i;
    }

    private static boolean startsWithOffset(byte[] bArr, int i, byte[] bArr2) {
        if (bArr.length - i < bArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            if (bArr[i2 + i] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.facebook.acra.util.ProcFileReader
    @Nullable
    public ProcFileReader.OpenFDLimits getOpenFDLimits() {
        byte[] bArr = new byte[8192];
        byte[] bytes = "Max open files".getBytes();
        String[] strArr = new String[2];
        try {
            FileInputStream fileInputStream = new FileInputStream("/proc/self/limits");
            try {
                int read = fileInputStream.read(bArr, 0, bArr.length - 1);
                bArr[read] = 0;
                fileInputStream.close();
                int i = 0;
                while ((read - 1) - i > bytes.length) {
                    int findNewLineOrEnd = findNewLineOrEnd(bArr, i);
                    if (!startsWithOffset(bArr, i, bytes)) {
                        i = findNewLineOrEnd + 1;
                    } else if (ProcReader.parseProcLine(bArr, i, findNewLineOrEnd, PROC_OPEN_FD_LIMITS_FORMAT, strArr, null, null)) {
                        return new ProcFileReader.OpenFDLimits(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]));
                    } else {
                        return null;
                    }
                }
                return null;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            BLog.w(TAG, e, "Failed to read /proc/self/limits");
            return null;
        }
    }
}
