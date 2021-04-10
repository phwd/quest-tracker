package com.oculus.vrapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AppVerifier {
    @SuppressLint({"CatchGeneralException"})
    public static String[] getPackageNamesFromUid(Context context, int uid) {
        String[] packageNames = null;
        try {
            packageNames = context.getPackageManager().getPackagesForUid(uid);
        } catch (RuntimeException e) {
        }
        if (packageNames != null && packageNames.length != 0) {
            return packageNames;
        }
        throw new RuntimeException("No packageName associated with uid=" + uid);
    }

    @SuppressLint({"CatchGeneralException"})
    public static PackageInfo getPackageInfoWithSignature(Context context, String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 64);
        } catch (PackageManager.NameNotFoundException | RuntimeException e) {
        }
        if (packageInfo == null) {
            throw new RuntimeException(packageName);
        } else if (packageName.equals(packageInfo.packageName)) {
            return packageInfo;
        } else {
            throw new RuntimeException(packageInfo.packageName);
        }
    }

    public static Signature getSignature(PackageInfo packageInfo) {
        if (packageInfo.signatures == null || packageInfo.signatures.length == 0) {
            throw new RuntimeException(packageInfo.packageName);
        } else if (packageInfo.signatures.length > 1) {
            throw new RuntimeException(packageInfo.packageName);
        } else if (packageInfo.signatures[0] != null) {
            return packageInfo.signatures[0];
        } else {
            throw new RuntimeException(packageInfo.packageName);
        }
    }

    public static Signature getSignatureFromUidPackageNames(Context context, int uid) {
        String[] packageNames = getPackageNamesFromUid(context, uid);
        Signature uidSignature = getSignature(getPackageInfoWithSignature(context, packageNames[0]));
        if (packageNames.length > 1) {
            for (int i = 1; i < packageNames.length; i++) {
                if (!uidSignature.equals(getSignature(getPackageInfoWithSignature(context, packageNames[i])))) {
                    throw new RuntimeException("packageName=" + Arrays.toString(packageNames));
                }
            }
        }
        return uidSignature;
    }

    public static AppSignatureHash getSignatureHash(Signature signature) {
        try {
            return new AppSignatureHash(HashHelper.getBase64Hash(signature.toByteArray(), HashHelper.SHA1), HashHelper.getBase64Hash(signature.toByteArray(), "SHA-256"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error obtaining SHA1/SHA256");
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
        int checkSignaturesResult = 0;
        try {
            checkSignaturesResult = context.getPackageManager().checkSignatures(uid1, uid2);
        } catch (RuntimeException e) {
        }
        return verifySameUid(uid1, uid2) || checkSignaturesResult == 0;
    }

    public static boolean verifySameSignature(Context context, ApplicationInfo appInfo1, ApplicationInfo appInfo2) {
        return verifySameSignature(context, appInfo1.uid, appInfo2.uid);
    }

    public static boolean verifySameSignature(Context context, String otherPackageName) {
        ApplicationInfo currentAppInfo = getPackageInfoWithSignature(context, context.getPackageName()).applicationInfo;
        if (currentAppInfo != null) {
            ApplicationInfo otherAppInfo = getPackageInfoWithSignature(context, otherPackageName).applicationInfo;
            if (otherAppInfo != null) {
                return verifySameSignature(context, currentAppInfo, otherAppInfo);
            }
            throw new RuntimeException(otherPackageName);
        }
        throw new RuntimeException(context.getPackageName());
    }

    public static AppSignatureHash getSignatureFromPackageName(Context context, String packageName) {
        return getSignatureHash(getSignature(getPackageInfoWithSignature(context, packageName)));
    }

    public static Signature getRawSignature(Context context, String packageName) {
        return getSignature(getPackageInfoWithSignature(context, packageName));
    }
}
