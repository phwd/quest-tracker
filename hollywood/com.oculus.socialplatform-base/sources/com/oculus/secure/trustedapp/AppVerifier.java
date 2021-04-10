package com.oculus.secure.trustedapp;

import X.AnonymousClass006;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.oculus.secure.trustedapp.exception.ApplicationInfoNotFoundException;
import com.oculus.secure.trustedapp.exception.MultipleSignatureException;
import com.oculus.secure.trustedapp.exception.PackageInfoMisMatchException;
import com.oculus.secure.trustedapp.exception.PackageInfoNullException;
import com.oculus.secure.trustedapp.exception.PackageNameNotFoundException;
import com.oculus.secure.trustedapp.exception.SignatureNotFoundException;
import com.oculus.secure.trustedapp.exception.SignatureNullException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AppVerifier {
    public static Signature getSignatureFromUidPackageNames(Context context, String[] strArr) {
        Signature signature = getSignature(getPackageInfoWithSignature(context, strArr[0]));
        int length = strArr.length;
        if (length > 1) {
            for (int i = 1; i < length; i++) {
                if (!signature.equals(getSignature(getPackageInfoWithSignature(context, strArr[i])))) {
                    throw new MultipleSignatureException(AnonymousClass006.A07("packageName=", Arrays.toString(strArr)));
                }
            }
        }
        return signature;
    }

    public static boolean verifySameUid(int i, int i2) {
        return i == i2;
    }

    public static Signature getSignature(PackageInfo packageInfo) {
        int length;
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null || (length = signatureArr.length) == 0) {
            throw new SignatureNotFoundException(packageInfo.packageName);
        } else if (length <= 1) {
            Signature signature = signatureArr[0];
            if (signature != null) {
                return signature;
            }
            throw new SignatureNullException(packageInfo.packageName);
        } else {
            throw new MultipleSignatureException(packageInfo.packageName);
        }
    }

    @SuppressLint({"CatchGeneralException"})
    public static PackageInfo getPackageInfoWithSignature(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo != null) {
                String str2 = packageInfo.packageName;
                if (str.equals(str2)) {
                    return packageInfo;
                }
                throw new PackageInfoMisMatchException(str, str2);
            }
            throw new PackageInfoNullException(str);
        } catch (PackageManager.NameNotFoundException unused) {
            throw new PackageNameNotFoundException(AnonymousClass006.A07(str, " not found by PackageManager."));
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }

    @SuppressLint({"CatchGeneralException"})
    public static String[] getPackageNamesFromUid(Context context, int i) {
        try {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
            if (packagesForUid != null && packagesForUid.length != 0) {
                return packagesForUid;
            }
            throw new PackageNameNotFoundException(AnonymousClass006.A03("No packageName associated with uid=", i));
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }

    public static Signature getRawSignature(Context context, String str) {
        return getSignature(getPackageInfoWithSignature(context, str));
    }

    public static AppSignatureHash getSignatureFromPackageName(Context context, String str) {
        return getSignatureHash(getSignature(getPackageInfoWithSignature(context, str)));
    }

    public static AppSignatureHash getSignatureHash(Signature signature) {
        try {
            return new AppSignatureHash(HashHelper.getBase64Hash(signature.toByteArray(), HashHelper.SHA1), HashHelper.getBase64Hash(signature.toByteArray(), "SHA-256"));
        } catch (NoSuchAlgorithmException unused) {
            throw new SecurityException("Error obtaining SHA1/SHA256");
        }
    }

    public static boolean verifySamePackageName(Context context, String str) {
        return context.getPackageName().equals(str);
    }

    @SuppressLint({"CatchGeneralException"})
    public static boolean verifySameSignature(Context context, int i, int i2) {
        try {
            return i == i2 || context.getPackageManager().checkSignatures(i, i2) == 0;
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }

    public static boolean verifySameSignature(Context context, ApplicationInfo applicationInfo, ApplicationInfo applicationInfo2) {
        return verifySameSignature(context, applicationInfo.uid, applicationInfo2.uid);
    }

    public static boolean verifySameSignature(Context context, String str) {
        ApplicationInfo applicationInfo = getPackageInfoWithSignature(context, context.getPackageName()).applicationInfo;
        if (applicationInfo != null) {
            ApplicationInfo applicationInfo2 = getPackageInfoWithSignature(context, str).applicationInfo;
            if (applicationInfo2 != null) {
                return verifySameSignature(context, applicationInfo, applicationInfo2);
            }
            throw new ApplicationInfoNotFoundException(str);
        }
        throw new ApplicationInfoNotFoundException(context.getPackageName());
    }
}
