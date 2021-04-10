package com.facebook.secure.trustedapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.exception.PackageInfoNullException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"CatchGeneralException", "DeprecatedMethod", "TodoWithoutTask"})
public class TrustedApp {
    public static final String ALL_PACKAGES_MARKER = "*|all_packages|*";
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
    public static boolean checkCallerHasFbPermissions(String str, Context context, @Nullable Intent intent, @Nullable Reporter reporter) {
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

    public static int getCallingUid() throws IllegalStateException {
        if (Binder.getCallingPid() != Process.myPid()) {
            return Binder.getCallingUid();
        }
        throw new IllegalStateException("This method should be called on behalf of an IPC transaction from binder thread.");
    }

    public static boolean checkSameSignatureCaller(Context context) {
        try {
            return AppVerifier.verifySameSignature(context, context.getApplicationInfo().uid, Binder.getCallingUid());
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static boolean checkSameSignatureCaller(@Nullable AppIdentity appIdentity, Context context) {
        if (appIdentity == null) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                return false;
            }
            return AppVerifier.verifySameSignature(context, applicationInfo.uid, appIdentity.getUid());
        } catch (SecurityException unused) {
            return false;
        }
    }

    @Deprecated
    public static AppIdentity getCallerAppIdentity(Context context) {
        return getAppIdentity(getCallingUid(), context);
    }

    @Nullable
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

    @Deprecated
    public static AppIdentity getAppIdentityCached(int i, Context context) {
        return new AppIdentity(i, Collections.unmodifiableList(Arrays.asList(AppVerifier.getPackageNamesFromUidCached(context, i))), AppVerifier.getSignatureHash(AppVerifier.getSignatureFromUidCached(context, i)), (String) null, (String) null);
    }

    @Deprecated
    public AppIdentity enforceTrustedCallerAppIdentity(Context context) {
        AppIdentity callerAppIdentity = getCallerAppIdentity(context);
        if (isAppIdentityTrusted(callerAppIdentity, context)) {
            return callerAppIdentity;
        }
        throw new SecurityException("Access denied.");
    }

    @Deprecated
    public AppIdentity enforceTrustedAppIdentity(Context context, Intent intent) {
        AppIdentity appIdentity = getAppIdentity(context, intent);
        if (appIdentity != null && isAppIdentityTrusted(appIdentity, context)) {
            return appIdentity;
        }
        throw new SecurityException("Access denied.");
    }

    @Deprecated
    public AppIdentity enforceTrustedAppIdentity(int i, Context context) {
        AppIdentity appIdentity = getAppIdentity(i, context);
        if (isAppIdentityTrusted(appIdentity, context)) {
            return appIdentity;
        }
        throw new SecurityException("Access denied.");
    }

    @Deprecated
    public boolean isCallerAppIdentityTrusted(@Nullable Context context) {
        if (context == null) {
            return false;
        }
        return isAppIdentityTrusted(getCallerAppIdentity(context), context);
    }

    @Deprecated
    public boolean isAppIdentityTrusted(Context context, Intent intent) {
        return isAppIdentityTrusted(getAppIdentity(context, intent), context);
    }

    @Deprecated
    public boolean isAppIdentityTrusted(int i, Context context) {
        return isAppIdentityTrusted(getAppIdentity(i, context), context);
    }

    @Deprecated
    public boolean isAppIdentityTrusted(@Nullable AppIdentity appIdentity) {
        return isAppIdentityTrusted(appIdentity, false);
    }

    @Deprecated
    public boolean isAppIdentityTrusted(@Nullable AppIdentity appIdentity, Context context) {
        return isAppIdentityTrusted(appIdentity, AppVerifier.verifySignedWithDebugKey(context));
    }

    @Nullable
    private String getTrustedContentProviderAuthority(Context context, Uri uri) {
        AppIdentity appIdentityFromProviderAuthority;
        String authority = uri.getAuthority();
        if (authority == null || (appIdentityFromProviderAuthority = AppIdentityUtil.getAppIdentityFromProviderAuthority(context, authority)) == null) {
            return null;
        }
        if (isAppIdentityTrusted(appIdentityFromProviderAuthority, context)) {
            return authority;
        }
        throw new SecurityException(String.format("The provider for uri '%s' is not trusted: %s", authority, appIdentityFromProviderAuthority));
    }

    @Nullable
    public ContentProviderClient getContentProviderClient(Context context, Uri uri) {
        String trustedContentProviderAuthority = getTrustedContentProviderAuthority(context, uri);
        if (trustedContentProviderAuthority == null) {
            return null;
        }
        return context.getContentResolver().acquireContentProviderClient(trustedContentProviderAuthority);
    }

    @Nullable
    public ContentProviderClient getUnstableContentProviderClient(Context context, Uri uri) {
        String trustedContentProviderAuthority = getTrustedContentProviderAuthority(context, uri);
        if (trustedContentProviderAuthority == null) {
            return null;
        }
        return context.getContentResolver().acquireUnstableContentProviderClient(trustedContentProviderAuthority);
    }

    public Context createPackageContext(Context context, String str, int i, @Nullable String str2) throws PackageManager.NameNotFoundException, PackageInfoNullException, SecurityException {
        AppIdentity appIdentityFromPackageName = AppIdentityUtil.getAppIdentityFromPackageName(context, str);
        boolean isAppIdentityTrusted = isAppIdentityTrusted(appIdentityFromPackageName, context);
        if (str2 != null && !isAppIdentityTrusted) {
            FbPermission fbPermission = FbPermission.get();
            isAppIdentityTrusted = fbPermission.canSkipFbPermissionCheck__DO_NOT_USE(context, str, str2) || fbPermission.checkFbPermission(context, appIdentityFromPackageName, str2, false);
        }
        if (isAppIdentityTrusted) {
            return context.createPackageContext(str, i);
        }
        throw new SecurityException(String.format("The package '%s' is not trusted: %s", str, appIdentityFromPackageName));
    }

    /* access modifiers changed from: protected */
    public boolean isAppIdentityTrusted(@Nullable AppIdentity appIdentity, boolean z) {
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

    public boolean mightMatchTrustedAppScope(String str) {
        if (TextUtils.isEmpty(str) || !this.mTrustedSignatures.isEmpty()) {
            return true;
        }
        for (Set<String> set : this.mTrustedPackages.values()) {
            if (set.contains(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TrustedApp)) {
            return false;
        }
        TrustedApp trustedApp = (TrustedApp) obj;
        Set<AppSignatureHash> set = trustedApp.mTrustedSignatures;
        if (set != null) {
            z = set.equals(this.mTrustedSignatures);
        } else {
            z = this.mTrustedSignatures == null;
        }
        Map<AppSignatureHash, Set<String>> map = trustedApp.mTrustedPackages;
        if (map != null) {
            z2 = map.equals(this.mTrustedPackages);
        } else {
            z2 = this.mTrustedPackages == null;
        }
        if (!z || !z2) {
            return false;
        }
        return true;
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
