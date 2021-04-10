package com.facebook.secure.trustedapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import com.facebook.secure.logger.Reporter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@SuppressLint({"CatchGeneralException", "DeprecatedMethod", "TodoWithoutTask"})
public class TrustedApp {
    private final Map<AppSignatureHash, Set<String>> mTrustedPackages;
    private final Set<AppSignatureHash> mTrustedSignatures;

    public TrustedApp(Map<AppSignatureHash, Set<String>> map) {
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        for (Map.Entry<AppSignatureHash, Set<String>> entry : map.entrySet()) {
            AppSignatureHash key = entry.getKey();
            Set<String> value = entry.getValue();
            if (value == null || !value.contains("*|all_packages|*")) {
                if (!hashMap.containsKey(key)) {
                    hashMap.put(key, new HashSet());
                }
                ((Set) hashMap.get(key)).addAll(value);
            } else {
                hashSet.add(key);
            }
        }
        this.mTrustedSignatures = Collections.unmodifiableSet(hashSet);
        this.mTrustedPackages = Collections.unmodifiableMap(hashMap);
    }

    @Deprecated
    public static boolean checkCallerHasFbPermissions(String str, Context context, Intent intent, Reporter reporter) {
        AppIdentity callerAppIdentity = CallerIdentityUtil.getCallerAppIdentity(context, intent, reporter);
        if (callerAppIdentity == null) {
            if (reporter != null) {
                reporter.report(String.format("AppIdentity not found for caller when checking %s permission", str));
            }
            throw new SecurityException("Invalid Caller Identity (null)");
        }
        if ((reporter != null ? FbPermission.get(reporter) : FbPermission.get()).canSkipFbPermissionCheck__DO_NOT_USE(context, callerAppIdentity.getPackageName(), str)) {
            return true;
        }
        return TrustedCaller.createWithFbPermission(str).isCallerAppTrusted(context, intent, reporter);
    }

    @Deprecated
    public static AppIdentity getAppIdentity(Context context, Intent intent) {
        AppIdentity callerInfo = CallerInfoHelper.getCallerInfo(context, intent);
        if (callerInfo == null) {
            return null;
        }
        if (Binder.getCallingPid() == Process.myPid() || callerInfo.getUid() == -1 || Binder.getCallingUid() == callerInfo.getUid()) {
            return new AppIdentity(callerInfo.getUid(), callerInfo.getPackageNames(), AppVerifier.getSignatureHashFromPackageNames(context, (String[]) callerInfo.getPackageNames().toArray(new String[0])), callerInfo.getVersionName(), callerInfo.getDomainName());
        }
        throw new SecurityException(String.format(Locale.US, "Uid %d from PI not equal to uid %d from binder data", Integer.valueOf(callerInfo.getUid()), Integer.valueOf(Binder.getCallingUid())));
    }

    @Deprecated
    public static AppIdentity getAppIdentity(int i, Context context) {
        String[] packageNamesFromUid = AppVerifier.getPackageNamesFromUid(context, i);
        return new AppIdentity(i, Collections.unmodifiableList(Arrays.asList(packageNamesFromUid)), AppVerifier.getSignatureHashFromPackageNames(context, packageNamesFromUid), (String) null, (String) null);
    }

    /* access modifiers changed from: protected */
    public boolean isAppIdentityTrusted(AppIdentity appIdentity, boolean z) {
        AppSignatureHash signatureHash;
        if (appIdentity == null || appIdentity.getSignatureHash() == null || (signatureHash = appIdentity.getSignatureHash()) == null) {
            return false;
        }
        for (AppSignatureHash appSignatureHash : this.mTrustedSignatures) {
            if (appIdentitySignatureMatch(signatureHash, appSignatureHash, z)) {
                return true;
            }
        }
        for (AppSignatureHash appSignatureHash2 : this.mTrustedPackages.keySet()) {
            if (appIdentitySignatureMatch(signatureHash, appSignatureHash2, z)) {
                for (String str : appIdentity.getPackageNames()) {
                    if (this.mTrustedPackages.get(appSignatureHash2).contains(str)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    private static boolean appIdentitySignatureMatch(AppSignatureHash appSignatureHash, AppSignatureHash appSignatureHash2, boolean z) {
        if (appSignatureHash.equals(appSignatureHash2)) {
            return true;
        }
        if (!z || !AllFamilyTrustedSignatures.getDebugAppSignatureHashes(appSignatureHash2).contains(appSignatureHash)) {
            return false;
        }
        return true;
    }

    public static boolean isCurrentAppDebugBuild(Context context) {
        return checkDebugSignature(AppVerifier.getSignatureFromPackageName(context, context.getPackageName()));
    }

    public static boolean checkDebugSignature(AppSignatureHash appSignatureHash) {
        return AllFamilyTrustedSignatures.FB_DEBUG.contains(appSignatureHash) || AllFamilyTrustedSignatures.WHATSAPP_DEBUG.contains(appSignatureHash) || AllFamilyTrustedSignatures.OCULUS_DEBUG.contains(appSignatureHash) || AllFamilyTrustedSignatures.PORTAL_DEBUG.contains(appSignatureHash) || AllFamilyTrustedSignatures.FB_INHOUSE_DEBUG.contains(appSignatureHash);
    }

    public static boolean checkDebugSignatureFromPackageName(Context context, String str) {
        return checkDebugSignature(AppVerifier.getSignatureFromPackageName(context, str));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TrustedApp)) {
            return false;
        }
        TrustedApp trustedApp = (TrustedApp) obj;
        Set<AppSignatureHash> set = trustedApp.mTrustedSignatures;
        boolean equals = set != null ? set.equals(this.mTrustedSignatures) : this.mTrustedSignatures == null;
        Map<AppSignatureHash, Set<String>> map = trustedApp.mTrustedPackages;
        return equals && (map != null ? map.equals(this.mTrustedPackages) : this.mTrustedPackages == null);
    }

    @SuppressLint({"ObjectsUse"})
    @TargetApi(19)
    public int hashCode() {
        if (Build.VERSION.SDK_INT > 18) {
            return Objects.hash(this.mTrustedSignatures, this.mTrustedPackages);
        }
        return Arrays.hashCode(Arrays.asList(this.mTrustedSignatures, this.mTrustedPackages).toArray(new Object[0]));
    }
}
