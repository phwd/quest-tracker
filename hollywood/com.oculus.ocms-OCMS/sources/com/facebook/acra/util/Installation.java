package com.facebook.acra.util;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

public class Installation {
    private static final String INSTALLATION = "ACRA-INSTALLATION";
    private static String sID;

    public static synchronized String id(Context context) {
        synchronized (Installation.class) {
            if (sID == null) {
                File filesDir = context.getFilesDir();
                if (filesDir == null) {
                    return "n/a";
                }
                File file = new File(filesDir.getParent(), INSTALLATION);
                try {
                    if (!file.exists()) {
                        writeInstallationFile(file);
                    }
                    sID = readInstallationFile(file);
                } catch (Exception unused) {
                    return "n/a";
                }
            }
            return sID;
        }
    }

    private static String readInstallationFile(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            byte[] bArr = new byte[((int) randomAccessFile.length())];
            randomAccessFile.readFully(bArr);
            return new String(bArr);
        } finally {
            randomAccessFile.close();
        }
    }

    private static void writeInstallationFile(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(UUID.randomUUID().toString().getBytes());
        } finally {
            fileOutputStream.close();
        }
    }
}
