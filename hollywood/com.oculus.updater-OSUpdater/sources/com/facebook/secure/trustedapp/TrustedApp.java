package com.facebook.secure.trustedapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.ultralight.UL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"CatchGeneralException", "DeprecatedMethod", "TodoWithoutTask"})
public class TrustedApp {
    private final Map<AppSignatureHash, Set<String>> mTrustedPackages;
    private final Set<AppSignatureHash> mTrustedSignatures;

    @Deprecated
    public static AppIdentity getAppIdentity(int i, Context context) {
        String[] packageNamesFromUid = AppVerifier.getPackageNamesFromUid(context, i);
        return new AppIdentity(i, Collections.unmodifiableList(Arrays.asList(packageNamesFromUid)), AppVerifier.getSignatureHashFromPackageNames(context, packageNamesFromUid), null, null);
    }

    @Deprecated
    public boolean isAppIdentityTrusted(int i, Context context) {
        return isAppIdentityTrusted(getAppIdentity(i, context), context);
    }

    @Deprecated
    public boolean isAppIdentityTrusted(@Nullable AppIdentity appIdentity, Context context) {
        return isAppIdentityTrusted(appIdentity, AppVerifier.verifySignedWithDebugKey(context));
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
    @TargetApi(UL.id._UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_BINDING_ID)
    public int hashCode() {
        if (Build.VERSION.SDK_INT > 18) {
            return Objects.hash(this.mTrustedSignatures, this.mTrustedPackages);
        }
        return Arrays.hashCode(Arrays.asList(this.mTrustedSignatures, this.mTrustedPackages).toArray(new Object[0]));
    }
}
