package X;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.DeadObjectException;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.internal.Utility;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: X.0WZ  reason: invalid class name */
public final class AnonymousClass0WZ {
    public static AnonymousClass0WT A00(Context context, String str, int i) {
        String str2;
        Integer num;
        MessageDigest messageDigest;
        ApplicationInfo applicationInfo;
        boolean z;
        AnonymousClass0WT r4 = new AnonymousClass0WT(str);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, i);
            r4.A01 = packageInfo;
            if (!(packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null)) {
                if (applicationInfo.enabled) {
                    AbstractC01570Vx r1 = (AbstractC01570Vx) AnonymousClass0W2.A00;
                    if (r1.A02().equals(str) || r1.A01().equals(str)) {
                        z = true;
                    } else {
                        try {
                            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(new Intent("com.facebook.rti.fbns.intent.RECEIVE").setPackage(str), 0);
                            if (queryBroadcastReceivers != null) {
                                z = Boolean.valueOf(!queryBroadcastReceivers.isEmpty());
                            }
                        } catch (RuntimeException e) {
                            AnonymousClass0NO.A0H("RtiGracefulSystemMethodHelper", e, "Failed to queryBroadcastReceivers");
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
            AnonymousClass0NO.A0H("RtiGracefulSystemMethodHelper", e2, "Failed to getPackageInfo");
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
                    messageDigest = MessageDigest.getInstance(Utility.HASH_ALGORITHM_SHA1);
                } catch (NoSuchAlgorithmException unused2) {
                    try {
                        Class.forName("org.apache.harmony.security.fortress.Services").getMethod("setNeedRefresh", new Class[0]).invoke(null, new Object[0]);
                    } catch (Exception unused3) {
                    }
                    messageDigest = MessageDigest.getInstance(Utility.HASH_ALGORITHM_SHA1);
                }
                messageDigest.update(byteArray, 0, byteArray.length);
                str2 = Base64.encodeToString(messageDigest.digest(), 11);
            }
            boolean z2 = !C01600Wb.A00(context).A02;
            HashSet hashSet = new HashSet();
            Set<AnonymousClass0bQ> A05 = ((AbstractC01570Vx) AnonymousClass0W2.A00).A05();
            if (!z2) {
                HashSet hashSet2 = new HashSet();
                for (AnonymousClass0bQ r12 : A05) {
                    Set<AnonymousClass0bQ> set = C02780bN.A0z.get(r12);
                    if (set == null) {
                        set = C02780bN.A00(C02780bN.A01);
                    }
                    hashSet2.addAll(set);
                }
                A05 = Collections.unmodifiableSet(hashSet2);
            }
            for (AnonymousClass0bQ r0 : A05) {
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

    public static boolean A01(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!str.equals(context.getPackageName()) && A00(context, str, 64).A02 != AnonymousClass007.A0G) {
            return false;
        }
        return true;
    }
}
