package com.facebook.secure.trustedapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.LruCache;
import com.facebook.secure.trustedapp.exception.MultipleSignatureException;
import com.facebook.secure.trustedapp.exception.PackageInfoMisMatchException;
import com.facebook.secure.trustedapp.exception.PackageInfoNullException;
import com.facebook.secure.trustedapp.exception.PackageNameNotFoundException;
import com.facebook.secure.trustedapp.exception.SignatureNotFoundException;
import com.facebook.secure.trustedapp.exception.SignatureNullException;
import com.oculus.secure.trustedapp.HashHelper;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@SuppressLint({"CatchGeneralException"})
public class AppVerifier {
    private static final UidToPackageNamesCache mUidToPackageNamesCache = new UidToPackageNamesCache();
    private static final UidToSignatureCache mUidToSignatureCache = new UidToSignatureCache();

    protected static class UidToSignatureCache extends LruCache<Integer, Signature> {
        public UidToSignatureCache() {
            super(100);
        }
    }

    protected static class UidToPackageNamesCache extends LruCache<Integer, String[]> {
        public UidToPackageNamesCache() {
            super(100);
        }
    }

    public static int getUidFromPackageName(Context context, String packageName) {
        return getPackageInfoWithSignature(context, packageName).applicationInfo.uid;
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

    public static AppSignatureHash getSignatureFromPackageName(Context context, String packageName) {
        return getSignatureHash(getSignature(getPackageInfoWithSignature(context, packageName)));
    }

    public static AppSignatureHash getSignatureHashFromPackageNames(Context context, String[] packageNames) {
        return getSignatureHash(getSignatureFromUidPackageNames(context, packageNames));
    }
}
