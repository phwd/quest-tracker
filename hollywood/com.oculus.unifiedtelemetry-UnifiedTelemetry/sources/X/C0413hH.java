package X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressLint({"CatchGeneralException"})
/* renamed from: X.hH  reason: case insensitive filesystem */
public final class C0413hH {
    public static final C0411hF A00 = new C0411hF();
    public static final C0412hG A01 = new C0412hG();

    public static Signature A01(PackageInfo packageInfo) {
        int length;
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null || (length = signatureArr.length) == 0) {
            throw new C0427hd(packageInfo.packageName);
        } else if (length <= 1) {
            Signature signature = signatureArr[0];
            if (signature != null) {
                return signature;
            }
            throw new C0428he(packageInfo.packageName);
        } else {
            throw new C0423hZ(packageInfo.packageName);
        }
    }

    @SuppressLint({"CatchGeneralException"})
    public static PackageInfo A00(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo != null) {
                String str2 = packageInfo.packageName;
                if (str.equals(str2)) {
                    return packageInfo;
                }
                throw new C0424ha(str, str2);
            }
            throw new C0425hb(str);
        } catch (PackageManager.NameNotFoundException unused) {
            throw new C0426hc(AnonymousClass06.A04(str, " not found by PackageManager."));
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }

    public static C0409hD A02(Signature signature) {
        try {
            byte[] byteArray = signature.toByteArray();
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(byteArray);
            String encodeToString = Base64.encodeToString(instance.digest(), 11);
            byte[] byteArray2 = signature.toByteArray();
            MessageDigest instance2 = MessageDigest.getInstance("SHA-256");
            instance2.update(byteArray2);
            return new C0409hD("test", encodeToString, Base64.encodeToString(instance2.digest(), 11));
        } catch (NoSuchAlgorithmException unused) {
            throw new SecurityException("Error obtaining SHA1/SHA256");
        }
    }
}
