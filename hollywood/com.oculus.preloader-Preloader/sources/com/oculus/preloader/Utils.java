package com.oculus.preloader;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.SystemProperties;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* access modifiers changed from: package-private */
public class Utils {
    public static final String ACTION_ALL_INSTALLS_COMPLETE = "com.oculus.preloader.ALL_INSTALLS_COMPLETE";
    public static final String ACTION_INSTALL_COMPLETE = "com.oculus.preloader.INSTALL_COMPLETE";
    public static final boolean DEBUG = false;
    public static final String TAG = "Preloader";

    private static native boolean copyFile(String str, String str2, int i);

    Utils() {
    }

    static {
        System.loadLibrary("preloader");
    }

    private static PublicKey getPublicKey(Context context) {
        X509Certificate x509Certificate;
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            if (signatureArr.length != 1) {
                return null;
            }
            try {
                x509Certificate = (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()));
            } catch (CertificateException unused) {
                x509Certificate = null;
            }
            if (x509Certificate != null) {
                return x509Certificate.getPublicKey();
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused2) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        $closeResource(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0041, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0044, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0045, code lost:
        $closeResource(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0048, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean verifyFileSignature(android.content.Context r4, java.lang.String r5, java.lang.String r6) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0049 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0049 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ all -> 0x0042 }
            r5.<init>(r6)     // Catch:{ all -> 0x0042 }
            java.lang.String r6 = "SHA1withRSA"
            java.security.Signature r6 = java.security.Signature.getInstance(r6)     // Catch:{ all -> 0x003b }
            java.security.PublicKey r4 = getPublicKey(r4)     // Catch:{ all -> 0x003b }
            r6.initVerify(r4)     // Catch:{ all -> 0x003b }
            r4 = 65536(0x10000, float:9.18355E-41)
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x003b }
        L_0x001c:
            int r2 = r1.read(r4)     // Catch:{ all -> 0x003b }
            r3 = -1
            if (r2 == r3) goto L_0x0027
            r6.update(r4, r0, r2)     // Catch:{ all -> 0x003b }
            goto L_0x001c
        L_0x0027:
            int r2 = r5.read(r4)     // Catch:{ all -> 0x003b }
            r3 = 256(0x100, float:3.59E-43)
            if (r2 != r3) goto L_0x0033
            boolean r0 = r6.verify(r4, r0, r3)     // Catch:{ all -> 0x003b }
        L_0x0033:
            r4 = 0
            $closeResource(r4, r5)
            $closeResource(r4, r1)
            goto L_0x0049
        L_0x003b:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x003d }
        L_0x003d:
            r6 = move-exception
            $closeResource(r4, r5)
            throw r6
        L_0x0042:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r5 = move-exception
            $closeResource(r4, r1)
            throw r5
        L_0x0049:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.preloader.Utils.verifyFileSignature(android.content.Context, java.lang.String, java.lang.String):boolean");
    }

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    public static boolean isEqualHash(byte[] bArr, String str) {
        if (bArr.length * 2 != str.length()) {
            return false;
        }
        int i = 0;
        while (i < bArr.length) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            i++;
            if (i2 != Integer.valueOf(str.substring(i3, i * 2), 16).intValue()) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        $closeResource(r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean verifyHash(java.lang.String r6, java.lang.String r7) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x002e }
            r1.<init>(r6)     // Catch:{ Exception -> 0x002e }
            r2 = 0
            java.lang.String r3 = "SHA-1"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch:{ all -> 0x0027 }
            r4 = 1048576(0x100000, float:1.469368E-39)
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x0027 }
        L_0x0011:
            int r5 = r1.read(r4)     // Catch:{ all -> 0x0027 }
            if (r5 < 0) goto L_0x001b
            r3.update(r4, r0, r5)     // Catch:{ all -> 0x0027 }
            goto L_0x0011
        L_0x001b:
            byte[] r3 = r3.digest()     // Catch:{ all -> 0x0027 }
            boolean r7 = isEqualHash(r3, r7)     // Catch:{ all -> 0x0027 }
            $closeResource(r2, r1)
            return r7
        L_0x0027:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0029 }
        L_0x0029:
            r2 = move-exception
            $closeResource(r7, r1)
            throw r2
        L_0x002e:
            r7 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Exception verifying "
            r1.append(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            java.lang.String r1 = "Preloader"
            android.util.Log.e(r1, r6, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.preloader.Utils.verifyHash(java.lang.String, java.lang.String):boolean");
    }

    public static boolean copyMedia(String str, String str2, String str3, int i) {
        return verifyHash(str, str3) && copyFile(str, str2, i);
    }

    public static boolean isRetailDevice() {
        return !SystemProperties.get("ro.boot.mfg.insecure").equals("1");
    }

    public static boolean isUnlocked() {
        return !SystemProperties.get("ro.boot.verifiedbootstate").equals("green");
    }

    public static void deleteRecursive(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                deleteRecursive(file2);
            }
        }
        if (!file.delete()) {
            Log.e("Preloader", "Failed to delete " + file.getAbsolutePath());
        }
    }

    public static int getUidForPackage(Context context, String str) {
        if ("root".equals(str)) {
            return 0;
        }
        if ("system".equals(str)) {
            return 1000;
        }
        try {
            return context.getPackageManager().getApplicationInfo(str, 0).uid;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("Preloader", "Package '" + str + "' not found");
            return -1;
        }
    }

    public static boolean installPackage(Context context, InputStream inputStream) {
        InstallCompleteReceiver installCompleteReceiver = new InstallCompleteReceiver();
        context.registerReceiver(installCompleteReceiver, new IntentFilter(ACTION_INSTALL_COMPLETE));
        try {
            if (installPackage_(context, inputStream)) {
                synchronized (installCompleteReceiver) {
                    while (!installCompleteReceiver.Done) {
                        installCompleteReceiver.wait();
                    }
                }
            }
        } catch (InterruptedException unused) {
            Log.e("Preloader", "Interrupted waiting for install");
        } catch (Throwable th) {
            context.unregisterReceiver(installCompleteReceiver);
            throw th;
        }
        context.unregisterReceiver(installCompleteReceiver);
        return installCompleteReceiver.Success;
    }

    private static boolean installPackage_(Context context, InputStream inputStream) {
        try {
            PackageInstaller packageInstaller = context.getPackageManager().getPackageInstaller();
            boolean z = true;
            int createSession = packageInstaller.createSession(new PackageInstaller.SessionParams(1));
            boolean z2 = createSession > 0;
            if (createSession <= 0) {
                Log.e("Preloader", "Failed to create install session!");
            }
            PackageInstaller.Session session = null;
            if (z2) {
                session = packageInstaller.openSession(createSession);
                if (session == null) {
                    z = false;
                }
            } else {
                z = z2;
            }
            if (z) {
                OutputStream openWrite = session.openWrite("Preloader", 0, -1);
                byte[] bArr = new byte[1048576];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    openWrite.write(bArr, 0, read);
                }
                session.fsync(openWrite);
                inputStream.close();
                openWrite.close();
                session.commit(createIntentSender(context, createSession));
            }
            return z;
        } catch (IOException unused) {
            return false;
        }
    }

    private static IntentSender createIntentSender(Context context, int i) {
        return PendingIntent.getBroadcast(context, i, new Intent(ACTION_INSTALL_COMPLETE), 0).getIntentSender();
    }

    /* access modifiers changed from: private */
    public static class InstallCompleteReceiver extends BroadcastReceiver {
        public boolean Done;
        public String PackageName;
        public boolean Success;

        private InstallCompleteReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", 1);
            this.Success = intExtra == 0 || intExtra == 5 || intent.getIntExtra("android.content.pm.extra.LEGACY_STATUS", -110) == -25;
            this.PackageName = intent.getStringExtra("android.content.pm.extra.PACKAGE_NAME");
            if (!this.Success) {
                Log.e("Preloader", "Failed to install " + this.PackageName + intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE"));
            }
            synchronized (this) {
                this.Done = true;
                notifyAll();
            }
        }
    }
}
