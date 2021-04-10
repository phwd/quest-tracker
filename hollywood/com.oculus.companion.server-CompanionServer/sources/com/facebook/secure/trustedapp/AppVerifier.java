package com.facebook.secure.trustedapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.LruCache;
import com.facebook.secure.trustedapp.exception.MultipleSignatureException;
import com.facebook.secure.trustedapp.exception.PackageInfoMisMatchException;
import com.facebook.secure.trustedapp.exception.PackageInfoNullException;
import com.facebook.secure.trustedapp.exception.PackageNameNotFoundException;
import com.facebook.secure.trustedapp.exception.SignatureNotFoundException;
import com.facebook.secure.trustedapp.exception.SignatureNullException;
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

    @SuppressLint({"CatchGeneralException"})
    public static String[] getPackageNamesFromUid(Context context, int i) {
        try {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
            if (packagesForUid != null && packagesForUid.length != 0) {
                return packagesForUid;
            }
            throw new PackageNameNotFoundException("No packageName associated with uid=" + i);
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }

    public static int getUidFromPackageName(Context context, String str) {
        return getPackageInfoWithSignature(context, str).applicationInfo.uid;
    }

    @SuppressLint({"CatchGeneralException"})
    public static PackageInfo getPackageInfoWithSignature(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo == null) {
                throw new PackageInfoNullException(str);
            } else if (str.equals(packageInfo.packageName)) {
                return packageInfo;
            } else {
                throw new PackageInfoMisMatchException(str, packageInfo.packageName);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            throw new PackageNameNotFoundException(str + " not found by PackageManager.");
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }

    public static Signature getSignature(PackageInfo packageInfo) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null || signatureArr.length == 0) {
            throw new SignatureNotFoundException(packageInfo.packageName);
        } else if (signatureArr.length > 1) {
            throw new MultipleSignatureException(packageInfo.packageName);
        } else if (signatureArr[0] != null) {
            return signatureArr[0];
        } else {
            throw new SignatureNullException(packageInfo.packageName);
        }
    }

    public static Signature getSignatureFromUidPackageNames(Context context, String[] strArr) {
        Signature signature = getSignature(getPackageInfoWithSignature(context, strArr[0]));
        if (strArr.length > 1) {
            for (int i = 1; i < strArr.length; i++) {
                if (!signature.equals(getSignature(getPackageInfoWithSignature(context, strArr[i])))) {
                    throw new MultipleSignatureException("packageName=" + Arrays.toString(strArr));
                }
            }
        }
        return signature;
    }

    public static AppSignatureHash getSignatureHash(Signature signature) {
        try {
            return new AppSignatureHash(HashHelper.getBase64Hash(signature.toByteArray(), "SHA-1"), HashHelper.getBase64Hash(signature.toByteArray(), "SHA-256"));
        } catch (NoSuchAlgorithmException unused) {
            throw new SecurityException("Error obtaining SHA1/SHA256");
        }
    }

    public static AppSignatureHash getSignatureFromPackageName(Context context, String str) {
        return getSignatureHash(getSignature(getPackageInfoWithSignature(context, str)));
    }

    public static AppSignatureHash getSignatureHashFromPackageNames(Context context, String[] strArr) {
        return getSignatureHash(getSignatureFromUidPackageNames(context, strArr));
    }

    public static Signature getRawSignature(Context context, String str) {
        return getSignature(getPackageInfoWithSignature(context, str));
    }

    public static long getVersionCodeFromPackageName(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (isOnOrAboveApi28()) {
                return Api28Utils.getVersionCode(packageInfo);
            }
            return Long.valueOf((long) packageInfo.versionCode).longValue();
        } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            return 0;
        }
    }

    protected static boolean isOnOrAboveApi28() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @TargetApi(28)
    protected static class Api28Utils {
        protected Api28Utils() {
        }

        public static long getVersionCode(PackageInfo packageInfo) {
            return packageInfo.getLongVersionCode();
        }
    }
}
