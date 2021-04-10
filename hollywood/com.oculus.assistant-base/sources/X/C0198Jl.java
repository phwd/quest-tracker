package X;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* renamed from: X.Jl  reason: case insensitive filesystem */
public final class C0198Jl {
    public static final C0196Jj A00 = new C0196Jj();
    public static final C0197Jk A01 = new C0197Jk();

    public static C0195Ji A02(Context context, String[] strArr) {
        Signature A012 = A01(A00(context, strArr[0]));
        int length = strArr.length;
        if (length > 1) {
            for (int i = 1; i < length; i++) {
                if (!A012.equals(A01(A00(context, strArr[i])))) {
                    throw new C0203Jq(AnonymousClass08.A04("packageName=", Arrays.toString(strArr)));
                }
            }
        }
        return A03(A012);
    }

    public static Signature A01(PackageInfo packageInfo) {
        int length;
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null || (length = signatureArr.length) == 0) {
            throw new C0207Ju(packageInfo.packageName);
        } else if (length <= 1) {
            Signature signature = signatureArr[0];
            if (signature != null) {
                return signature;
            }
            throw new C0208Jv(packageInfo.packageName);
        } else {
            throw new C0203Jq(packageInfo.packageName);
        }
    }

    public static PackageInfo A00(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo != null) {
                String str2 = packageInfo.packageName;
                if (str.equals(str2)) {
                    return packageInfo;
                }
                throw new C0204Jr(str, str2);
            }
            throw new C0205Js(str);
        } catch (PackageManager.NameNotFoundException unused) {
            throw new C0206Jt(AnonymousClass08.A04(str, " not found by PackageManager."));
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }

    public static C0195Ji A03(Signature signature) {
        try {
            byte[] byteArray = signature.toByteArray();
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(byteArray);
            String encodeToString = Base64.encodeToString(instance.digest(), 11);
            byte[] byteArray2 = signature.toByteArray();
            MessageDigest instance2 = MessageDigest.getInstance("SHA-256");
            instance2.update(byteArray2);
            return new C0195Ji("test", encodeToString, Base64.encodeToString(instance2.digest(), 11));
        } catch (NoSuchAlgorithmException unused) {
            throw new SecurityException("Error obtaining SHA1/SHA256");
        }
    }
}
