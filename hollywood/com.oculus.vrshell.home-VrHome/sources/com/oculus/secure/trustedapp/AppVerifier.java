package com.oculus.secure.trustedapp;

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
    @SuppressLint({"CatchGeneralException"})
    public static String[] getPackageNamesFromUid(Context context, int uid) {
        try {
            String[] packageNames = context.getPackageManager().getPackagesForUid(uid);
            if (packageNames != null && packageNames.length != 0) {
                return packageNames;
            }
            throw new PackageNameNotFoundException("No packageName associated with uid=" + uid);
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }

    @SuppressLint({"CatchGeneralException"})
    public static PackageInfo getPackageInfoWithSignature(Context context, String packageName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 64);
            if (packageInfo == null) {
                throw new PackageInfoNullException(packageName);
            } else if (packageName.equals(packageInfo.packageName)) {
                return packageInfo;
            } else {
                throw new PackageInfoMisMatchException(packageName, packageInfo.packageName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new PackageNameNotFoundException(packageName + " not found by PackageManager.");
        } catch (RuntimeException e2) {
            throw new SecurityException(e2);
        }
    }

    public static Signature getSignature(PackageInfo packageInfo) {
        if (packageInfo.signatures == null || packageInfo.signatures.length == 0) {
            throw new SignatureNotFoundException(packageInfo.packageName);
        } else if (packageInfo.signatures.length > 1) {
            throw new MultipleSignatureException(packageInfo.packageName);
        } else if (packageInfo.signatures[0] != null) {
            return packageInfo.signatures[0];
        } else {
            throw new SignatureNullException(packageInfo.packageName);
        }
    }

    public static Signature getSignatureFromUidPackageNames(Context context, String[] packageNames) {
        Signature uidSignature = getSignature(getPackageInfoWithSignature(context, packageNames[0]));
        if (packageNames.length > 1) {
            for (int i = 1; i < packageNames.length; i++) {
                if (!uidSignature.equals(getSignature(getPackageInfoWithSignature(context, packageNames[i])))) {
                    throw new MultipleSignatureException("packageName=" + Arrays.toString(packageNames));
                }
            }
        }
        return uidSignature;
    }

    public static AppSignatureHash getSignatureHash(Signature signature) {
        try {
            return new AppSignatureHash(HashHelper.getBase64Hash(signature.toByteArray(), HashHelper.SHA1), HashHelper.getBase64Hash(signature.toByteArray(), "SHA-256"));
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException("Error obtaining SHA1/SHA256");
        }
    }

    public static boolean verifySamePackageName(Context context, String otherPackageName) {
        return context.getPackageName().equals(otherPackageName);
    }

    public static boolean verifySameUid(int uid1, int uid2) {
        return uid1 == uid2;
    }

    @SuppressLint({"CatchGeneralException"})
    public static boolean verifySameSignature(Context context, int uid1, int uid2) {
        try {
            return verifySameUid(uid1, uid2) || context.getPackageManager().checkSignatures(uid1, uid2) == 0;
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }

    public static boolean verifySameSignature(Context context, ApplicationInfo appInfo1, ApplicationInfo appInfo2) {
        return verifySameSignature(context, appInfo1.uid, appInfo2.uid);
    }

    public static boolean verifySameSignature(Context context, String otherPackageName) {
        ApplicationInfo currentAppInfo = getPackageInfoWithSignature(context, context.getPackageName()).applicationInfo;
        if (currentAppInfo == null) {
            throw new ApplicationInfoNotFoundException(context.getPackageName());
        }
        ApplicationInfo otherAppInfo = getPackageInfoWithSignature(context, otherPackageName).applicationInfo;
        if (otherAppInfo != null) {
            return verifySameSignature(context, currentAppInfo, otherAppInfo);
        }
        throw new ApplicationInfoNotFoundException(otherPackageName);
    }

    public static AppSignatureHash getSignatureFromPackageName(Context context, String packageName) {
        return getSignatureHash(getSignature(getPackageInfoWithSignature(context, packageName)));
    }

    public static Signature getRawSignature(Context context, String packageName) {
        return getSignature(getPackageInfoWithSignature(context, packageName));
    }
}
