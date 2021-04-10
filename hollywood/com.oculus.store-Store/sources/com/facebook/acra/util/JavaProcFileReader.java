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
    private static final String TAG = JavaProcFileReader.class.getSimpleName();
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
        String shortFileLeafPath;
        StringBuilder builder = new StringBuilder();
        try {
            String[] lines = CommandOutputCollector.collect("/system/bin/ls", "-l", String.format(FD_DIR, new Object[0])).split("\n");
            Map<String, Counter> map = new LinkedHashMap<>();
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                int index = line.lastIndexOf(LS_SYMLINK_ARROW);
                if (index != -1) {
                    String fileLeafPath = line.substring(LS_SYMLINK_ARROW.length() + index);
                    int index2 = fileLeafPath.lastIndexOf(FD_DIR_STRING);
                    if (index2 == -1) {
                        shortFileLeafPath = fileLeafPath;
                    } else {
                        shortFileLeafPath = fileLeafPath.substring(FD_DIR_STRING.length() + index2);
                    }
                    if (shortFileLeafPath.startsWith(PIPE_STRING)) {
                        fileLeafPath = PIPE_STRING;
                    } else if (shortFileLeafPath.startsWith(SOCKET_STRING)) {
                        fileLeafPath = SOCKET_STRING;
                    }
                    Counter counter = map.get(fileLeafPath);
                    if (counter == null) {
                        counter = new Counter();
                        map.put(fileLeafPath, counter);
                    }
                    counter.count++;
                }
            }
            for (Map.Entry<String, Counter> entry : map.entrySet()) {
                builder.append(entry.getKey()).append(" ").append(entry.getValue().count).append("\n");
            }
            return builder.toString();
        } catch (IOException | IndexOutOfBoundsException | SecurityException e) {
            BLog.e(TAG, e, "Exception caught while reading open file descriptors");
            return e.getMessage();
        }
    }

    @Override // com.facebook.acra.util.ProcFileReader
    public int getOpenFDCount() {
        try {
            String[] fdFiles = new File(FD_DIR).list();
            if (fdFiles != null) {
                return fdFiles.length;
            }
            return -1;
        } catch (SecurityException e) {
            BLog.e(TAG, e.getMessage());
            return -2;
        }
    }

    private static int findNewLineOrEnd(byte[] buf, int start) {
        if (start >= buf.length) {
            return -1;
        }
        int i = start;
        while (i < buf.length - 1 && buf[i] != 10 && buf[i] != 0) {
            i++;
        }
        return i;
    }

    private static boolean startsWithOffset(byte[] arr1, int offset, byte[] arr2) {
        if (arr1.length - offset < arr2.length) {
            return false;
        }
        for (int i = 0; i < arr2.length; i++) {
            if (arr1[i + offset] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.facebook.acra.util.ProcFileReader
    @Nullable
    public ProcFileReader.OpenFDLimits getOpenFDLimits() {
        ProcFileReader.OpenFDLimits res = null;
        byte[] fileBuf = new byte[8192];
        int curr = 0;
        byte[] rowName = "Max open files".getBytes();
        String[] readBuf = new String[2];
        try {
            FileInputStream in = new FileInputStream("/proc/self/limits");
            try {
                int fileSize = in.read(fileBuf, 0, fileBuf.length - 1);
                fileBuf[fileSize] = 0;
                in.close();
                while (true) {
                    if ((fileSize - 1) - curr <= rowName.length) {
                        break;
                    }
                    int nl = findNewLineOrEnd(fileBuf, curr);
                    if (!startsWithOffset(fileBuf, curr, rowName)) {
                        curr = nl + 1;
                    } else if (ProcReader.parseProcLine(fileBuf, curr, nl, PROC_OPEN_FD_LIMITS_FORMAT, readBuf, null, null)) {
                        res = new ProcFileReader.OpenFDLimits(Integer.parseInt(readBuf[0]), Integer.parseInt(readBuf[1]));
                    }
                }
                return res;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        } catch (IOException e) {
            BLog.w(TAG, e, "Failed to read /proc/self/limits");
            return null;
        }
    }
}
