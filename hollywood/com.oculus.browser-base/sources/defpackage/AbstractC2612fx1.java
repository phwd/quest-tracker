package defpackage;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* renamed from: fx1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2612fx1 {

    /* renamed from: a  reason: collision with root package name */
    public static byte[] f9968a;
    public static byte[] b;
    public static PublicKey c;
    public static boolean d;

    public static ResolveInfo a(Context context, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ResolveInfo resolveInfo = (ResolveInfo) it.next();
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null && b(context, activityInfo.packageName)) {
                return resolveInfo;
            }
        }
        return null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:68|(1:70)|71|72|73|74|75|76|112) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:74:0x0115 */
    /* JADX WARNING: Removed duplicated region for block: B:113:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x013e A[SYNTHETIC, Splitter:B:87:0x013e] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x014a A[SYNTHETIC, Splitter:B:94:0x014a] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x014f A[SYNTHETIC, Splitter:B:98:0x014f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(android.content.Context r13, java.lang.String r14) {
        /*
        // Method dump skipped, instructions count: 366
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC2612fx1.b(android.content.Context, java.lang.String):boolean");
    }

    public static String c(Context context, String str) {
        ResolveInfo a2 = a(context, d(context, str));
        if (a2 != null) {
            return a2.activityInfo.packageName;
        }
        return null;
    }

    public static List d(Context context, String str) {
        return e(context, str, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0045, code lost:
        return new java.util.LinkedList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0046, code lost:
        android.os.StrictMode.setThreadPolicy(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0049, code lost:
        throw r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x003d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List e(android.content.Context r2, java.lang.String r3, java.lang.String r4) {
        /*
            r0 = 1
            r1 = 0
            android.content.Intent r3 = android.content.Intent.parseUri(r3, r0)     // Catch:{ Exception -> 0x0021 }
            java.lang.String r0 = "android.intent.category.BROWSABLE"
            r3.addCategory(r0)
            if (r4 == 0) goto L_0x0011
            r3.setPackage(r4)
            goto L_0x0014
        L_0x0011:
            r3.setComponent(r1)
        L_0x0014:
            android.content.Intent r4 = r3.getSelector()
            if (r4 == 0) goto L_0x0020
            r4.addCategory(r0)
            r4.setComponent(r1)
        L_0x0020:
            r1 = r3
        L_0x0021:
            if (r1 != 0) goto L_0x0029
            java.util.LinkedList r2 = new java.util.LinkedList
            r2.<init>()
            return r2
        L_0x0029:
            android.os.StrictMode$ThreadPolicy r3 = android.os.StrictMode.allowThreadDiskReads()
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ Exception -> 0x003d }
            r4 = 64
            java.util.List r2 = r2.queryIntentActivities(r1, r4)     // Catch:{ Exception -> 0x003d }
            android.os.StrictMode.setThreadPolicy(r3)
            return r2
        L_0x003b:
            r2 = move-exception
            goto L_0x0046
        L_0x003d:
            java.util.LinkedList r2 = new java.util.LinkedList     // Catch:{ all -> 0x003b }
            r2.<init>()     // Catch:{ all -> 0x003b }
            android.os.StrictMode.setThreadPolicy(r3)
            return r2
        L_0x0046:
            android.os.StrictMode.setThreadPolicy(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC2612fx1.e(android.content.Context, java.lang.String, java.lang.String):java.util.List");
    }

    public static boolean f(PackageInfo packageInfo, String str) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr != null && signatureArr.length == 2 && str.startsWith("org.chromium.webapk")) {
            for (Signature signature : packageInfo.signatures) {
                if (Arrays.equals(f9968a, signature.toByteArray())) {
                    return true;
                }
            }
        }
        return false;
    }
}
