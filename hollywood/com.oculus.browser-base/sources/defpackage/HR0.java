package defpackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;

/* renamed from: HR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class HR0 {
    public static void a(SecureRandom secureRandom) {
        FileInputStream fileInputStream = new FileInputStream("/dev/urandom");
        try {
            byte[] bArr = new byte[16];
            if (fileInputStream.read(bArr) == 16) {
                secureRandom.setSeed(bArr);
                fileInputStream.close();
                return;
            }
            throw new IOException("Failed to get enough random data.");
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
