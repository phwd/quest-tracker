package com.facebook.secure.trustedapp;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.facebook.secure.logger.Reporter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public class TrustedCaller {
    private static final long FLAG_CALLERIDENTITY_ALLOW_LONG_TTL = 8;
    private static final long FLAG_FBPERMISSION_ALLOW_SINGLE_MATCH = 4;
    private static final long FLAG_FBPERMISSION_FAIL_OPEN = 2;
    private static final long FLAG_TRUSTEDAPP_ALLOW_SAME_APP = 1;
    private static final String TAG = "TrustedCaller";
    private final ArrayList<String> mDomains;
    private final long mFlags;
    private final ArrayList<String> mPermissions;
    @Nullable
    private final TrustedApp mTrustedApp;

    public static TrustedCaller createWithFbPermission(String str) {
        return builder().addFbPermission(str).build();
    }

    public static TrustedCaller createWithFbPermission(String str, boolean z) {
        TrustedCallerBuilder addFbPermission = builder().addFbPermission(str);
        if (z) {
            addFbPermission = addFbPermission.enableFbPermissionFailOpen();
        }
        return addFbPermission.build();
    }

    public static TrustedCallerBuilder builder() {
        return new TrustedCallerBuilder();
    }

    private TrustedCaller(TrustedCallerBuilder trustedCallerBuilder) {
        this.mTrustedApp = trustedCallerBuilder.mTrustedApp;
        this.mDomains = trustedCallerBuilder.mDomains;
        this.mPermissions = trustedCallerBuilder.mPermissions;
        this.mFlags = trustedCallerBuilder.mFlags;
        if (this.mTrustedApp == null && this.mPermissions.isEmpty() && !hasFlag(1)) {
            throw new IllegalArgumentException("TrustedCaller needs to be configured with at least 1 security check");
        }
    }

    public boolean isCallerAppTrusted(Context context, @Nullable Intent intent) {
        return isCallerAppTrusted(context, intent, null);
    }

    public boolean isCallerAppTrusted(Context context, @Nullable Intent intent, @Nullable Reporter reporter) {
        try {
            enforceTrustedCallerApp(context, intent, reporter);
            return true;
        } catch (SecurityException e) {
            if (reporter == null) {
                return false;
            }
            reporter.report(TAG, e.getMessage(), e.getCause());
            return false;
        }
    }

    public void enforceTrustedCallerApp(Context context, @Nullable Intent intent) {
        enforceTrustedCallerApp(context, intent, null);
    }

    public void enforceTrustedCallerApp(Context context, @Nullable Intent intent, @Nullable Reporter reporter) {
        AppIdentity callerAppIdentity = CallerIdentityUtil.getCallerAppIdentity(context, intent, reporter, hasFlag(8));
        if (callerAppIdentity != null) {
            throwIfInvalidDomain(callerAppIdentity);
            if (!(hasFlag(1) && context.getPackageName().equals(callerAppIdentity.getPackageName()))) {
                boolean contains = AllFamilyTrustedSignatures.ALL_FAMILY_DEBUG.contains(AppVerifier.getSignatureFromPackageName(context, context.getPackageName()));
                throwIfTrustedAppMismatch(callerAppIdentity, contains);
                throwIfMissingFbPermission(callerAppIdentity, context, reporter, contains);
                return;
            }
            return;
        }
        throw new SecurityException("Invalid Caller Identity (null)");
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting(otherwise = 2)
    public void throwIfInvalidDomain(AppIdentity appIdentity) {
        if (!this.mDomains.isEmpty() && !this.mDomains.contains(appIdentity.getDomainName())) {
            throw new SecurityException(String.format("Missing required Caller Domains %s from caller %s", this.mDomains, appIdentity));
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting(otherwise = 2)
    public void throwIfTrustedAppMismatch(AppIdentity appIdentity, boolean z) {
        TrustedApp trustedApp = this.mTrustedApp;
        if (trustedApp != null && !trustedApp.isAppIdentityTrusted(appIdentity, z)) {
            throw new SecurityException(String.format("Caller Identity '%s' is not trusted", appIdentity));
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting(otherwise = 2)
    public void throwIfMissingFbPermission(AppIdentity appIdentity, Context context, @Nullable Reporter reporter, boolean z) {
        List<String> list;
        if (!this.mPermissions.isEmpty()) {
            boolean hasFlag = hasFlag(4);
            if (z) {
                list = FbPermission.getAppFbPermissionsFromManifest(context, appIdentity);
            } else {
                list = Collections.emptyList();
            }
            FbPermission fbPermission = reporter != null ? FbPermission.get(reporter) : FbPermission.get();
            Iterator<String> it = this.mPermissions.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                String next = it.next();
                z2 = (z && list.contains(next)) || fbPermission.checkFbPermission(context, appIdentity, next, hasFlag(2));
                if ((z2 && hasFlag) || (!z2 && !hasFlag)) {
                    break;
                }
            }
            if (!z2) {
                throw new SecurityException(String.format("Missing at least one required FBPermission %s from caller %s", this.mPermissions, appIdentity));
            }
        }
    }

    private boolean hasFlag(long j) {
        return (j & this.mFlags) != 0;
    }

    public static final class TrustedCallerBuilder {
        private ArrayList<String> mDomains = new ArrayList<>();
        private long mFlags = 0;
        private ArrayList<String> mPermissions = new ArrayList<>();
        @Nullable
        private TrustedApp mTrustedApp;
        private Map<AppSignatureHash, Set<String>> mTrustedPackages = new HashMap();

        public TrustedCallerBuilder addDomain(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mDomains.add(str);
                return this;
            }
            throw new IllegalArgumentException();
        }

        public TrustedCallerBuilder addFbPermission(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mPermissions.add(str);
                return this;
            }
            throw new IllegalArgumentException();
        }

        public TrustedCallerBuilder addTrustedPackage(AppSignatureHash appSignatureHash, String str) {
            Set<String> set;
            if (!this.mTrustedPackages.containsKey(appSignatureHash) || (set = this.mTrustedPackages.get(appSignatureHash)) == null) {
                HashSet hashSet = new HashSet();
                hashSet.add(str);
                this.mTrustedPackages.put(appSignatureHash, hashSet);
                return this;
            }
            set.add(str);
            return this;
        }

        public TrustedCallerBuilder addTrustedPackages(AppSignatureHash appSignatureHash, Set<String> set) {
            Set<String> set2;
            if (!this.mTrustedPackages.containsKey(appSignatureHash) || (set2 = this.mTrustedPackages.get(appSignatureHash)) == null) {
                this.mTrustedPackages.put(appSignatureHash, set);
                return this;
            }
            set2.addAll(set);
            return this;
        }

        public TrustedCallerBuilder setTrustedApp(TrustedApp trustedApp) {
            this.mTrustedApp = trustedApp;
            return this;
        }

        public TrustedCallerBuilder enableFbPermissionFailOpen() {
            this.mFlags |= 2;
            return this;
        }

        public TrustedCallerBuilder allowSingleFbPermissionMatch() {
            this.mFlags |= 4;
            return this;
        }

        public TrustedCallerBuilder enableTrustedAppSamePackage() {
            this.mFlags |= 1;
            return this;
        }

        public TrustedCallerBuilder allowCallerIdentityLongTTL_UNSAFE() {
            this.mFlags |= 8;
            return this;
        }

        private void throwIfInvalidBuilder() {
            if (this.mTrustedApp != null && !this.mTrustedPackages.isEmpty()) {
                throw new IllegalArgumentException("TrustedCaller needs to be configured with either a TrustedApp or list of trusted packages");
            }
        }

        public TrustedCaller build() {
            throwIfInvalidBuilder();
            if (!this.mTrustedPackages.isEmpty()) {
                this.mTrustedApp = new TrustedApp(this.mTrustedPackages);
            }
            return new TrustedCaller(this);
        }
    }
}
