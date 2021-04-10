package com.facebook.secure.trustedapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import com.oculus.os.Version;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"CatchGeneralException", "DeprecatedMethod", "TodoWithoutTask"})
public class TrustedApp {
    private final Map<AppSignatureHash, Set<String>> mTrustedPackages;
    private final Set<AppSignatureHash> mTrustedSignatures;

    @Nullable
    @Deprecated
    public static AppIdentity getAppIdentity(Context context, Intent launchingIntent) {
        AppIdentity callerAppIdentity = CallerInfoHelper.getCallerInfo(context, launchingIntent);
        if (callerAppIdentity == null) {
            return null;
        }
        if (Binder.getCallingPid() == Process.myPid() || callerAppIdentity.getUid() == -1 || Binder.getCallingUid() == callerAppIdentity.getUid()) {
            return new AppIdentity(callerAppIdentity.getUid(), callerAppIdentity.getPackageNames(), AppVerifier.getSignatureHashFromPackageNames(context, (String[]) callerAppIdentity.getPackageNames().toArray(new String[0])), callerAppIdentity.getVersionName(), callerAppIdentity.getDomainName());
        }
        throw new SecurityException(String.format(Locale.US, "Uid %d from PI not equal to uid %d from binder data", Integer.valueOf(callerAppIdentity.getUid()), Integer.valueOf(Binder.getCallingUid())));
    }

    public static boolean isCurrentAppDebugBuild(Context context) {
        return checkDebugSignature(AppVerifier.getSignatureFromPackageName(context, context.getPackageName()));
    }

    public static boolean checkDebugSignature(AppSignatureHash appSignature) {
        return AllFamilyTrustedSignatures.FB_DEBUG.contains(appSignature) || AllFamilyTrustedSignatures.WHATSAPP_DEBUG.contains(appSignature) || AllFamilyTrustedSignatures.OCULUS_DEBUG.contains(appSignature) || AllFamilyTrustedSignatures.PORTAL_DEBUG.contains(appSignature) || AllFamilyTrustedSignatures.FB_INHOUSE_DEBUG.contains(appSignature);
    }

    public static boolean checkDebugSignatureFromPackageName(Context context, String packageName) {
        return checkDebugSignature(AppVerifier.getSignatureFromPackageName(context, packageName));
    }

    public boolean equals(Object obj) {
        boolean mTrustedSignaturesMatch;
        boolean mTrustedPackagesMatch;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TrustedApp)) {
            return false;
        }
        TrustedApp trustedApp = (TrustedApp) obj;
        if (trustedApp.mTrustedSignatures != null) {
            mTrustedSignaturesMatch = trustedApp.mTrustedSignatures.equals(this.mTrustedSignatures);
        } else {
            mTrustedSignaturesMatch = this.mTrustedSignatures == null;
        }
        if (trustedApp.mTrustedPackages != null) {
            mTrustedPackagesMatch = trustedApp.mTrustedPackages.equals(this.mTrustedPackages);
        } else {
            mTrustedPackagesMatch = this.mTrustedPackages == null;
        }
        return mTrustedSignaturesMatch && mTrustedPackagesMatch;
    }

    @SuppressLint({"ObjectsUse"})
    @TargetApi(Version.VERSION_19)
    public int hashCode() {
        if (Build.VERSION.SDK_INT > 18) {
            return Objects.hash(this.mTrustedSignatures, this.mTrustedPackages);
        }
        return Arrays.hashCode(Arrays.asList(this.mTrustedSignatures, this.mTrustedPackages).toArray(new Object[0]));
    }
}
