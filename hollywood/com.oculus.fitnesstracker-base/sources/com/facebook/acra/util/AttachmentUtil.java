package com.facebook.acra.util;

import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public final class AttachmentUtil {
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0013 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String loadAttachment(java.io.InputStream r4, int r5) throws java.io.IOException {
        /*
            byte[] r0 = new byte[r5]
            r1 = 0
            r2 = 0
        L_0x0004:
            int r3 = r5 - r1
            if (r3 <= 0) goto L_0x0011
            int r2 = r4.read(r0, r1, r3)
            r3 = -1
            if (r2 == r3) goto L_0x0011
            int r1 = r1 + r2
            goto L_0x0004
        L_0x0011:
            if (r2 != 0) goto L_0x0016
            java.lang.String r4 = ""
            return r4
        L_0x0016:
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
}
