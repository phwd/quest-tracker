package com.facebook.crudolib.processname;

import com.facebook.infer.annotation.Nullsafe;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProcessNameHelper {
    private static volatile boolean sAttemptedRead;
    @Nullable
    private static volatile String sMyFullProcessName;

    @Nullable
    public static String getMyFullProcessName() {
        String str;
        if (!sAttemptedRead) {
            try {
                str = readMyFullProcessName();
            } catch (IOException unused) {
                str = null;
            }
            sMyFullProcessName = str;
            sAttemptedRead = true;
        }
        return sMyFullProcessName;
    }

    private static String readMyFullProcessName() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("/proc/self/cmdline"));
        byte[] bArr = new byte[512];
        try {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                return new String(bArr, 0, read).trim();
            }
            throw new EOFException();
        } finally {
            fileInputStream.close();
        }
    }
}
