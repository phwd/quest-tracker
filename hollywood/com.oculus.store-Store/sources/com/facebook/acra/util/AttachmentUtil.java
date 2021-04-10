package com.facebook.acra.util;

import android.util.Base64;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class AttachmentUtil {
    public static String loadAttachment(InputStream in, int nbytes) throws IOException {
        int offset = 0;
        int nbytesread = 0;
        byte[] attachment = new byte[nbytes];
        while (nbytes - offset > 0 && (nbytesread = in.read(attachment, offset, nbytes - offset)) != -1) {
            offset += nbytesread;
        }
        if (nbytesread == 0) {
            return "";
        }
        return compressToBase64String(attachment);
    }

    public static String compressToBase64String(byte[] array) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(baos);
        try {
            gzipOutputStream.write(array, 0, array.length);
            gzipOutputStream.finish();
            String encodeToString = Base64.encodeToString(baos.toByteArray(), 0);
            gzipOutputStream.close();
            return encodeToString;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static File[] sortPruneOldFiles(File[] traces, int numberToKeep) {
        if (traces.length != 0) {
            Arrays.sort(traces, new Comparator<File>() {
                /* class com.facebook.acra.util.AttachmentUtil.AnonymousClass1 */

                public int compare(File f1, File f2) {
                    return Long.valueOf(f2.lastModified()).compareTo(Long.valueOf(f1.lastModified()));
                }
            });
            if (traces.length > numberToKeep) {
                for (int i = numberToKeep; i < traces.length; i++) {
                    traces[i].delete();
                    traces[i] = null;
                }
            }
        }
        return traces;
    }

    public static File[] sortPruneOldFiles(File directory, int numberToKeep) {
        return sortPruneOldFiles(directory.listFiles(), numberToKeep);
    }

    public static boolean validateGzip(File gzipFile) {
        try {
            FileInputStream fis = new FileInputStream(gzipFile);
            try {
                GZIPInputStream gfis = new GZIPInputStream(fis);
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(gfis));
                    while (br.ready()) {
                        try {
                            br.readLine();
                        } catch (Throwable th) {
                            th.addSuppressed(th);
                        }
                    }
                    br.close();
                    gfis.close();
                    fis.close();
                    return true;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
                throw th;
                throw th;
            } catch (Throwable th3) {
                th.addSuppressed(th3);
            }
        } catch (IOException e) {
            return false;
        }
    }
}
