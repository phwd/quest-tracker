package com.facebook.acra.util;

import android.util.Base64;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class AttachmentUtil {
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0014 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String loadAttachment(java.io.InputStream r4, int r5) throws java.io.IOException {
        /*
            byte[] r0 = new byte[r5]
            r1 = 0
            r2 = 0
        L_0x0004:
            int r3 = r5 - r1
            if (r3 <= 0) goto L_0x0012
            int r2 = r4.read(r0, r1, r3)
            r3 = -1
            if (r2 != r3) goto L_0x0010
            goto L_0x0012
        L_0x0010:
            int r1 = r1 + r2
            goto L_0x0004
        L_0x0012:
            if (r2 != 0) goto L_0x0017
            java.lang.String r4 = ""
            return r4
        L_0x0017:
            java.lang.String r4 = compressToBase64String(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.util.AttachmentUtil.loadAttachment(java.io.InputStream, int):java.lang.String");
    }

    public static String compressToBase64String(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        try {
            gZIPOutputStream.write(bArr, 0, bArr.length);
            gZIPOutputStream.finish();
            String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
            gZIPOutputStream.close();
            return encodeToString;
        } catch (Throwable unused) {
        }
        throw th;
    }

    public static File[] sortPruneOldFiles(File[] fileArr, int i) {
        if (fileArr.length == 0) {
            return fileArr;
        }
        Arrays.sort(fileArr, new Comparator<File>() {
            /* class com.facebook.acra.util.AttachmentUtil.AnonymousClass1 */

            public int compare(File file, File file2) {
                return Long.valueOf(file2.lastModified()).compareTo(Long.valueOf(file.lastModified()));
            }
        });
        if (fileArr.length > i) {
            while (i < fileArr.length) {
                fileArr[i].delete();
                fileArr[i] = null;
                i++;
            }
        }
        return fileArr;
    }

    public static File[] sortPruneOldFiles(File file, int i) {
        return sortPruneOldFiles(file.listFiles(), i);
    }

    public static boolean validateGzip(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                GZIPInputStream gZIPInputStream = new GZIPInputStream(fileInputStream);
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream));
                    while (bufferedReader.ready()) {
                        try {
                            bufferedReader.readLine();
                        } catch (Throwable unused) {
                        }
                    }
                    bufferedReader.close();
                    gZIPInputStream.close();
                    fileInputStream.close();
                    return true;
                } catch (Throwable unused2) {
                }
                throw th;
                throw th;
                throw th;
            } catch (Throwable unused3) {
            }
        } catch (IOException unused4) {
            return false;
        }
    }
}
