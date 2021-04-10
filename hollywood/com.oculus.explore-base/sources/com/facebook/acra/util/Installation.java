package com.facebook.acra.util;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

public class Installation {
    private static String sID = null;

    public static synchronized String id(Context context) {
        String str;
        synchronized (Installation.class) {
            if (sID == null) {
                File filesDir = context.getFilesDir();
                if (filesDir == null) {
                    str = "n/a";
                } else {
                    File installation = new File(filesDir.getParent(), "ACRA-INSTALLATION");
                    try {
                        if (!installation.exists()) {
                            writeInstallationFile(installation);
                        }
                        sID = readInstallationFile(installation);
                    } catch (Exception e) {
                        str = "n/a";
                    }
                }
            }
            str = sID;
        }
        return str;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        try {
            byte[] bytes = new byte[((int) f.length())];
            f.readFully(bytes);
            return new String(bytes);
        } finally {
            f.close();
        }
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        try {
            out.write(UUID.randomUUID().toString().getBytes());
        } finally {
            out.close();
        }
    }
}
