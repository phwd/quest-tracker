package com.facebook.soloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* access modifiers changed from: package-private */
public final class j implements h {
    private /* synthetic */ boolean a;
    private /* synthetic */ String b;
    private /* synthetic */ String c;
    private /* synthetic */ Runtime d;
    private /* synthetic */ Method e;

    j(boolean z, String str, String str2, Runtime runtime, Method method) {
        this.a = z;
        this.b = str;
        this.c = str2;
        this.d = runtime;
        this.e = method;
    }

    private static String a(String str) {
        try {
            File file = new File(str);
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        instance.update(bArr, 0, read);
                    } else {
                        String format = String.format("%32x", new BigInteger(1, instance.digest()));
                        fileInputStream.close();
                        return format;
                    }
                }
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e2) {
            return e2.toString();
        } catch (SecurityException e3) {
            return e3.toString();
        } catch (NoSuchAlgorithmException e4) {
            return e4.toString();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        if (r1 == null) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
        android.util.Log.e("SoLoader", "Error when loading lib: " + r1 + " lib hash: " + a(r9) + " search path is " + r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return;
     */
    @Override // com.facebook.soloader.h
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r9, int r10) {
        /*
        // Method dump skipped, instructions count: 183
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.j.a(java.lang.String, int):void");
    }
}
