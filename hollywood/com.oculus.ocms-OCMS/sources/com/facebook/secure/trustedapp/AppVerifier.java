package com.facebook.secure.trustedapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.LruCache;
import androidx.annotation.VisibleForTesting;
import com.facebook.secure.trustedapp.exception.ApplicationInfoNotFoundException;
import com.facebook.secure.trustedapp.exception.MultipleSignatureException;
import com.facebook.secure.trustedapp.exception.PackageInfoMisMatchException;
import com.facebook.secure.trustedapp.exception.PackageInfoNullException;
import com.facebook.secure.trustedapp.exception.PackageNameNotFoundException;
import com.facebook.secure.trustedapp.exception.SignatureNotFoundException;
import com.facebook.secure.trustedapp.exception.SignatureNullException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.annotation.Nullable;

@SuppressLint({"CatchGeneralException"})
public class AppVerifier {
    private static final UidToPackageNamesCache mUidToPackageNamesCache = new UidToPackageNamesCache();
    private static final UidToSignatureCache mUidToSignatureCache = new UidToSignatureCache();

    public static boolean verifySameUid(int i, int i2) {
        return i == i2;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting(otherwise = 2)
    public static class UidToSignatureCache extends LruCache<Integer, Signature> {
        private static final int MAX_CACHE_ENTRIES = 100;

        public UidToSignatureCache() {
            super(100);
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting(otherwise = 2)
    public static class UidToPackageNamesCache extends LruCache<Integer, String[]> {
        private static final int MAX_CACHE_ENTRIES = 100;

        public UidToPackageNamesCache() {
            super(100);
        }
    }

    public static Signature getSignatureFromUidCached(Context context, int i) {
        Signature signature = (Signature) mUidToSignatureCache.get(Integer.valueOf(i));
        if (signature != null) {
            return signature;
        }
        Signature signature2 = getSignature(getPackageInfoWithSignature(context, getPackageNamesFromUidCached(context, i)[0]));
        mUidToSignatureCache.put(Integer.valueOf(i), signature2);
        return signature2;
    }

    public static void clearUidToSignatureCache() {
        mUidToSignatureCache.evictAll();
    }

    public static String[] getPackageNamesFromUidCached(Context context, int i) {
        String[] strArr = (String[]) mUidToPackageNamesCache.get(Integer.valueOf(i));
        if (strArr != null) {
            return strArr;
        }
        String[] packageNamesFromUid = getPackageNamesFromUid(context, i);
        mUidToPackageNamesCache.put(Integer.valueOf(i), packageNamesFromUid);
        return packageNamesFromUid;
    }

    public static void clearUidToPackageNamesCache() {
        mUidToPackageNamesCache.evictAll();
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
            return verifySameUid(i, i2) || context.getPackageManager().checkSignatures(i, i2) == 0;
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

    public static boolean verifySignedWithDebugKey(Context context) {
        return verifySignedWithDebugKey(context, context.getPackageName());
    }

    public static boolean verifySignedWithDebugKey(Context context, @Nullable AppIdentity appIdentity) {
        if (appIdentity == null) {
            return false;
        }
        for (String str : appIdentity.getPackageNames()) {
            if (verifySignedWithDebugKey(context, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean verifySignedWithDebugKey(Context context, String str) {
        return AllFamilyTrustedSignatures.ALL_FAMILY_DEBUG.contains(getSignatureFromPackageName(context, str));
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

    public static boolean isSystemApp(Context context, String str) throws PackageManager.NameNotFoundException {
        return isSystemApp(getApplicationInfoFromPackageName(context, str));
    }

    private static ApplicationInfo getApplicationInfoFromPackageName(Context context, String str) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getApplicationInfo(str, 0);
        }
        throw new IllegalStateException("Fail to obtain PackageManager");
    }

    private static boolean isSystemApp(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 1) != 0;
    }

    @VisibleForTesting(otherwise = 2)
    protected static boolean isOnOrAboveApi28() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @VisibleForTesting(otherwise = 2)
    @TargetApi(28)
    protected static class Api28Utils {
        protected Api28Utils() {
        }

        public static long getVersionCode(PackageInfo packageInfo) {
            return packageInfo.getLongVersionCode();
        }
    }
}
