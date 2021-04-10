package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/* renamed from: WH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class WH1 {
    public static C1822bI1 a(SharedPreferences sharedPreferences, String str) {
        long j;
        String string = sharedPreferences.getString(C4881tD1.c(str, "|P|"), null);
        String string2 = sharedPreferences.getString(C4881tD1.c(str, "|K|"), null);
        if (string == null || string2 == null) {
            return null;
        }
        KeyPair g = g(string, string2);
        String string3 = sharedPreferences.getString(C4881tD1.c(str, "cre"), null);
        if (string3 != null) {
            try {
                j = Long.parseLong(string3);
            } catch (NumberFormatException unused) {
            }
            return new C1822bI1(g, j);
        }
        j = 0;
        return new C1822bI1(g, j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0044, code lost:
        d(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static defpackage.C1822bI1 b(java.io.File r6) {
        /*
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r6)
            java.util.Properties r6 = new java.util.Properties     // Catch:{ all -> 0x0041 }
            r6.<init>()     // Catch:{ all -> 0x0041 }
            r6.load(r0)     // Catch:{ all -> 0x0041 }
            java.lang.String r1 = "pub"
            java.lang.String r1 = r6.getProperty(r1)     // Catch:{ all -> 0x0041 }
            java.lang.String r2 = "pri"
            java.lang.String r2 = r6.getProperty(r2)     // Catch:{ all -> 0x0041 }
            r3 = 0
            if (r1 == 0) goto L_0x003d
            if (r2 != 0) goto L_0x001f
            goto L_0x003d
        L_0x001f:
            java.security.KeyPair r1 = g(r1, r2)     // Catch:{ all -> 0x0041 }
            java.lang.String r2 = "cre"
            java.lang.String r6 = r6.getProperty(r2)     // Catch:{ NumberFormatException -> 0x0036 }
            long r4 = java.lang.Long.parseLong(r6)     // Catch:{ NumberFormatException -> 0x0036 }
            bI1 r6 = new bI1
            r6.<init>(r1, r4)
            d(r3, r0)
            return r6
        L_0x0036:
            r6 = move-exception
            hI1 r1 = new hI1
            r1.<init>(r6)
            throw r1
        L_0x003d:
            d(r3, r0)
            return r3
        L_0x0041:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r1 = move-exception
            d(r6, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.WH1.b(java.io.File):bI1");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004e, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0053, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0054, code lost:
        defpackage.AbstractC3701mI1.f10413a.a(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0059, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(android.content.Context r3, java.lang.String r4, defpackage.C1822bI1 r5) {
        /*
        // Method dump skipped, instructions count: 124
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.WH1.c(android.content.Context, java.lang.String, bI1):void");
    }

    public static void d(Throwable th, FileInputStream fileInputStream) {
        if (th != null) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                AbstractC3701mI1.f10413a.a(th, th2);
            }
        } else {
            fileInputStream.close();
        }
    }

    public static KeyPair g(String str, String str2) {
        try {
            byte[] decode = Base64.decode(str, 8);
            byte[] decode2 = Base64.decode(str2, 8);
            try {
                KeyFactory instance = KeyFactory.getInstance("RSA");
                return new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(valueOf.length() + 19);
                sb.append("Invalid key stored ");
                sb.append(valueOf);
                Log.w("InstanceID", sb.toString());
                throw new C2847hI1(e);
            }
        } catch (IllegalArgumentException e2) {
            throw new C2847hI1(e2);
        }
    }

    public static File j(Context context) {
        Object obj = K2.f8337a;
        File noBackupFilesDir = context.getNoBackupFilesDir();
        if (noBackupFilesDir != null && noBackupFilesDir.isDirectory()) {
            return noBackupFilesDir;
        }
        Log.w("InstanceID", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }

    public static File k(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "com.google.InstanceId.properties";
        } else {
            try {
                String encodeToString = Base64.encodeToString(str.getBytes("UTF-8"), 11);
                StringBuilder sb = new StringBuilder(String.valueOf(encodeToString).length() + 33);
                sb.append("com.google.InstanceId_");
                sb.append(encodeToString);
                sb.append(".properties");
                str2 = sb.toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(j(context), str2);
    }

    public final void e(Context context, String str, C1822bI1 bi1) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (bi1.equals(a(sharedPreferences, str))) {
                return;
            }
        } catch (C2847hI1 unused) {
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(C4881tD1.c(str, "|P|"), Base64.encodeToString(bi1.f9531a.getPublic().getEncoded(), 11));
        edit.putString(C4881tD1.c(str, "|K|"), Base64.encodeToString(bi1.f9531a.getPrivate().getEncoded(), 11));
        edit.putString(C4881tD1.c(str, "cre"), String.valueOf(bi1.b));
        edit.commit();
    }

    public final C1822bI1 f(Context context, String str) {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
            instance.initialize(2048);
            C1822bI1 bi1 = new C1822bI1(instance.generateKeyPair(), System.currentTimeMillis());
            try {
                C1822bI1 h = h(context, str);
                if (h != null) {
                    Log.isLoggable("InstanceID", 3);
                    return h;
                }
            } catch (C2847hI1 unused) {
            }
            Log.isLoggable("InstanceID", 3);
            c(context, str, bi1);
            e(context, str, bi1);
            return bi1;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public final C1822bI1 h(Context context, String str) {
        C2847hI1 e;
        try {
            C1822bI1 i = i(context, str);
            if (i != null) {
                e(context, str, i);
                return i;
            }
            e = null;
            try {
                C1822bI1 a2 = a(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
                if (a2 != null) {
                    c(context, str, a2);
                    return a2;
                }
            } catch (C2847hI1 e2) {
                e = e2;
            }
            if (e == null) {
                return null;
            }
            throw e;
        } catch (C2847hI1 e3) {
            e = e3;
        }
    }

    public final C1822bI1 i(Context context, String str) {
        File k = k(context, str);
        if (!k.exists()) {
            return null;
        }
        try {
            return b(k);
        } catch (IOException e) {
            if (Log.isLoggable("InstanceID", 3)) {
                String.valueOf(e).length();
            }
            try {
                return b(k);
            } catch (IOException e2) {
                String valueOf = String.valueOf(e2);
                StringBuilder sb = new StringBuilder(valueOf.length() + 45);
                sb.append("IID file exists, but failed to read from it: ");
                sb.append(valueOf);
                Log.w("InstanceID", sb.toString());
                throw new C2847hI1(e2);
            }
        }
    }
}
