package com.facebook.acra.util;

import android.util.Base64;
import com.facebook.assistant.oacr.OacrConstants;
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
    public static String compressToBase64String(byte[] bArr) {
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

    public static String loadAttachment(InputStream inputStream, int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i - i2;
            if (i4 > 0) {
                i3 = inputStream.read(bArr, i2, i4);
                if (i3 == -1) {
                    break;
                }
                i2 += i3;
            } else if (i3 == 0) {
                return OacrConstants.AUTO_SPEECH_DOMAIN;
            }
        }
        return compressToBase64String(bArr);
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

    public static File[] sortPruneOldFiles(File file, int i) {
        File[] listFiles = file.listFiles();
        sortPruneOldFiles(listFiles, i);
        return listFiles;
    }

    public static File[] sortPruneOldFiles(File[] fileArr, int i) {
        int length = fileArr.length;
        if (length != 0) {
            Arrays.sort(fileArr, new Comparator() {
                /* class com.facebook.acra.util.AttachmentUtil.AnonymousClass1 */

                public int compare(File file, File file2) {
                    return Long.valueOf(file2.lastModified()).compareTo(Long.valueOf(file.lastModified()));
                }
            });
            if (length > i) {
                while (i < length) {
                    fileArr[i].delete();
                    fileArr[i] = null;
                    i++;
                }
            }
        }
        return fileArr;
    }
}
