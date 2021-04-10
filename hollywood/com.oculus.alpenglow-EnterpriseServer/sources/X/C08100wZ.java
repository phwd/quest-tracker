package X;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.DeadObjectException;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: X.0wZ  reason: invalid class name and case insensitive filesystem */
public final class C08100wZ {
    public static C08130wc A00(Context context, String str, int i) {
        String str2;
        Integer num;
        MessageDigest messageDigest;
        ApplicationInfo applicationInfo;
        boolean z;
        C08130wc r4 = new C08130wc(str);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, i);
            r4.A01 = packageInfo;
            if (!(packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null)) {
                if (applicationInfo.enabled) {
                    AnonymousClass153 r1 = (AnonymousClass153) ((AnonymousClass0v1) AnonymousClass151.A00);
                    if (r1.A02.equals(str) || r1.A01.equals(str)) {
                        z = true;
                    } else {
                        try {
                            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(new Intent("com.facebook.rti.fbns.intent.RECEIVE").setPackage(str), 0);
                            if (queryBroadcastReceivers != null) {
                                z = Boolean.valueOf(!queryBroadcastReceivers.isEmpty());
                            }
                        } catch (RuntimeException e) {
                            AnonymousClass0NK.A09("RtiGracefulSystemMethodHelper", e, "Failed to queryBroadcastReceivers");
                            if (!(e.getCause() instanceof DeadObjectException)) {
                                throw e;
                            }
                        }
                        z = null;
                    }
                    if (Boolean.TRUE.equals(z)) {
                        r4.A02 = AnonymousClass007.A0E;
                    } else if (Boolean.FALSE.equals(z)) {
                        r4.A02 = AnonymousClass007.A0D;
                    }
                } else {
                    r4.A02 = AnonymousClass007.A0C;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
            r4.A02 = AnonymousClass007.A01;
        } catch (RuntimeException e2) {
            AnonymousClass0NK.A09("RtiGracefulSystemMethodHelper", e2, "Failed to getPackageInfo");
            if (!(e2.getCause() instanceof DeadObjectException)) {
                throw e2;
            }
        }
        if (!((i & 64) == 0 || r4.A02 != AnonymousClass007.A0E || r4.A01 == null)) {
            Signature[] signatureArr = r4.A01.signatures;
            if (signatureArr == null || signatureArr.length != 1) {
                str2 = null;
            } else {
                byte[] byteArray = signatureArr[0].toByteArray();
                try {
                    messageDigest = MessageDigest.getInstance("SHA-1");
                } catch (NoSuchAlgorithmException unused2) {
                    try {
                        Class.forName("org.apache.harmony.security.fortress.Services").getMethod("setNeedRefresh", new Class[0]).invoke(null, new Object[0]);
                    } catch (Exception unused3) {
                    }
                    messageDigest = MessageDigest.getInstance("SHA-1");
                }
                messageDigest.update(byteArray, 0, byteArray.length);
                str2 = Base64.encodeToString(messageDigest.digest(), 11);
            }
            boolean z2 = !C08090wY.A00(context).A02;
            HashSet hashSet = new HashSet();
            Set<C05140ii> set = ((AnonymousClass153) ((AnonymousClass0v1) AnonymousClass151.A00)).A05;
            if (!z2) {
                HashSet hashSet2 = new HashSet();
                for (C05140ii r12 : set) {
                    Set<C05140ii> set2 = C05120ig.A0z.get(r12);
                    if (set2 == null) {
                        set2 = C05120ig.A00(C05120ig.A01);
                    }
                    hashSet2.addAll(set2);
                }
                set = Collections.unmodifiableSet(hashSet2);
            }
            for (C05140ii r0 : set) {
                hashSet.add(r0.sha1Hash);
            }
            if (Collections.unmodifiableSet(hashSet).contains(str2)) {
                num = AnonymousClass007.A0G;
            } else {
                num = AnonymousClass007.A0F;
            }
            r4.A02 = num;
        }
        return r4;
    }
}
