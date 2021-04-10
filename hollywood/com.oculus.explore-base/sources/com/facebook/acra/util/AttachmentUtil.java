package com.facebook.acra.util;

import android.util.Base64;
import com.oculus.common.build.BuildConfig;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
            return BuildConfig.PROVIDER_SUFFIX;
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
}
