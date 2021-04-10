package defpackage;

import android.content.Context;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.chromium.base.ContextUtils;

/* renamed from: Ux1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Ux1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f9057a = new Object();
    public static SecretKey b;

    public static SecretKey a() {
        synchronized (f9057a) {
            SecretKey secretKey = b;
            if (secretKey != null) {
                return secretKey;
            }
            Context applicationContext = ContextUtils.getApplicationContext();
            SecretKey b2 = b(applicationContext, "webapp-authenticator", "HmacSHA256");
            if (b2 != null) {
                b = b2;
                return b2;
            }
            SecretKey secretKey2 = b;
            if (secretKey2 == null) {
                try {
                    KeyGenerator instance = KeyGenerator.getInstance("HmacSHA256");
                    SecureRandom secureRandom = new SecureRandom();
                    HR0.a(secureRandom);
                    instance.init(256, secureRandom);
                    secretKey2 = instance.generateKey();
                } catch (IOException | NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }
            b = secretKey2;
            if (!c(applicationContext, "webapp-authenticator", secretKey2)) {
                b = null;
                return null;
            }
            return b;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:22|23|26) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0083, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0084, code lost:
        android.util.Log.e("WebappAuthenticator", "Could not close key input stream '" + r8 + "': " + r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009c, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x007f */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bd A[SYNTHETIC, Splitter:B:34:0x00bd] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e0 A[SYNTHETIC, Splitter:B:42:0x00e0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static javax.crypto.SecretKey b(android.content.Context r8, java.lang.String r9, java.lang.String r10) {
        /*
        // Method dump skipped, instructions count: 254
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Ux1.b(android.content.Context, java.lang.String, java.lang.String):javax.crypto.SecretKey");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x007a A[SYNTHETIC, Splitter:B:23:0x007a] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009b A[SYNTHETIC, Splitter:B:30:0x009b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean c(android.content.Context r6, java.lang.String r7, javax.crypto.SecretKey r8) {
        /*
        // Method dump skipped, instructions count: 185
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Ux1.c(android.content.Context, java.lang.String, javax.crypto.SecretKey):boolean");
    }
}
