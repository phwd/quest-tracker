package X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Base64;
import com.oculus.secure.trustedapp.HashHelper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@SuppressLint({"CatchGeneralException"})
/* renamed from: X.0kC  reason: invalid class name */
public final class AnonymousClass0kC {
    public static final AnonymousClass0kA A00 = new AnonymousClass0kA();
    public static final AnonymousClass0kB A01 = new AnonymousClass0kB();

    public static C02920k8 A04(Context context, String[] strArr) {
        Signature A02 = A02(A01(context, strArr[0]));
        int length = strArr.length;
        if (length > 1) {
            for (int i = 1; i < length; i++) {
                if (!A02.equals(A02(A01(context, strArr[i])))) {
                    throw new C02930kV(AnonymousClass006.A07("packageName=", Arrays.toString(strArr)));
                }
            }
        }
        return A05(A02);
    }

    public static long A00(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (Build.VERSION.SDK_INT >= 28) {
                return AnonymousClass0k9.A00(packageInfo);
            }
            return Long.valueOf((long) packageInfo.versionCode).longValue();
        } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            return 0;
        }
    }

    public static Signature A02(PackageInfo packageInfo) {
        int length;
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null || (length = signatureArr.length) == 0) {
            throw new AnonymousClass0kZ(packageInfo.packageName);
        } else if (length <= 1) {
            Signature signature = signatureArr[0];
            if (signature != null) {
                return signature;
            }
            throw new C02940ka(packageInfo.packageName);
        } else {
            throw new C02930kV(packageInfo.packageName);
        }
    }

    @SuppressLint({"CatchGeneralException"})
    public static PackageInfo A01(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo != null) {
                String str2 = packageInfo.packageName;
                if (str.equals(str2)) {
                    return packageInfo;
                }
                throw new AnonymousClass0kW(str, str2);
            }
            throw new AnonymousClass0kX(str);
        } catch (PackageManager.NameNotFoundException unused) {
            throw new AnonymousClass0kY(AnonymousClass006.A07(str, " not found by PackageManager."));
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }

    public static C02920k8 A03(Context context, String str) {
        return A05(A02(A01(context, str)));
    }

    public static C02920k8 A05(Signature signature) {
        try {
            byte[] byteArray = signature.toByteArray();
            MessageDigest instance = MessageDigest.getInstance(HashHelper.SHA1);
            instance.update(byteArray);
            String encodeToString = Base64.encodeToString(instance.digest(), 11);
            byte[] byteArray2 = signature.toByteArray();
            MessageDigest instance2 = MessageDigest.getInstance("SHA-256");
            instance2.update(byteArray2);
            return new C02920k8("test", encodeToString, Base64.encodeToString(instance2.digest(), 11));
        } catch (NoSuchAlgorithmException unused) {
            throw new SecurityException("Error obtaining SHA1/SHA256");
        }
    }

    @SuppressLint({"CatchGeneralException"})
    public static boolean A06(Context context, int i, int i2) {
        try {
            int checkSignatures = context.getPackageManager().checkSignatures(i, i2);
            if (i == i2 || checkSignatures == 0) {
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }

    @SuppressLint({"CatchGeneralException"})
    public static String[] A07(Context context, int i) {
        try {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
            if (packagesForUid != null && packagesForUid.length != 0) {
                return packagesForUid;
            }
            throw new AnonymousClass0kY(AnonymousClass006.A03("No packageName associated with uid=", i));
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }
}
