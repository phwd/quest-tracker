package com.facebook.acra.util;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

public class Installation {
    public static final String INSTALLATION = "ACRA-INSTALLATION";
    public static String sID;

    public static synchronized String id(Context context) {
        String str;
        synchronized (Installation.class) {
            str = sID;
            if (str == null) {
                File filesDir = context.getFilesDir();
                if (filesDir == null) {
                    str = "n/a";
                } else {
                    File file = new File(filesDir.getParent(), INSTALLATION);
                    try {
                        if (!file.exists()) {
                            writeInstallationFile(file);
                        }
                        str = readInstallationFile(file);
                        sID = str;
                    } catch (Exception unused) {
                        str = "n/a";
                    }
                }
            }
        }
        return str;
    }

    public static String readInstallationFile(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            byte[] bArr = new byte[((int) randomAccessFile.length())];
            randomAccessFile.readFully(bArr);
            return new String(bArr);
        } finally {
            randomAccessFile.close();
        }
    }

    public static void writeInstallationFile(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(UUID.randomUUID().toString().getBytes());
        } finally {
            fileOutputStream.close();
        }
    }
}
