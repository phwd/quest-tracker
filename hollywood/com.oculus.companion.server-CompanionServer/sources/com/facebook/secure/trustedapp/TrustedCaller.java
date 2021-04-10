package com.facebook.secure.trustedapp;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.logger.Reporter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrustedCaller {
    private final ArrayList<String> mDomains;
    private final long mFlags;
    private final ArrayList<String> mPermissions;
    private final TrustedApp mTrustedApp;

    public static TrustedCaller createWithFbPermission(String str) {
        TrustedCallerBuilder builder = builder();
        builder.addFbPermission(str);
        return builder.build();
    }

    public static TrustedCallerBuilder builder() {
        return new TrustedCallerBuilder();
    }

    private TrustedCaller(TrustedCallerBuilder trustedCallerBuilder) {
        if (!trustedCallerBuilder.mTrustedPackages.isEmpty()) {
            this.mTrustedApp = new TrustedApp(trustedCallerBuilder.mTrustedPackages);
        } else {
            this.mTrustedApp = null;
        }
        this.mDomains = trustedCallerBuilder.mDomains;
        this.mPermissions = trustedCallerBuilder.mPermissions;
        this.mFlags = trustedCallerBuilder.mFlags;
        if (this.mTrustedApp == null && this.mPermissions.isEmpty()) {
            throw new IllegalArgumentException("TrustedCaller needs to be configured with at least 1 security check");
        }
    }

    public boolean isCallerAppTrusted(Context context, Intent intent, Reporter reporter) {
        try {
            enforceTrustedCallerApp(context, intent, reporter);
            return true;
        } catch (SecurityException e) {
            if (reporter == null) {
                return false;
            }
            reporter.report("TrustedCaller", e.getMessage(), e.getCause());
            return false;
        }
    }

    public void enforceTrustedCallerApp(Context context, Intent intent, Reporter reporter) {
        AppIdentity callerAppIdentity = CallerIdentityUtil.getCallerAppIdentity(context, intent, reporter);
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
    public void throwIfInvalidDomain(AppIdentity appIdentity) {
        if (!this.mDomains.isEmpty() && !this.mDomains.contains(appIdentity.getDomainName())) {
            throw new SecurityException(String.format("Missing required Caller Domains %s from caller %s", this.mDomains, appIdentity));
        }
    }

    /* access modifiers changed from: protected */
    public void throwIfTrustedAppMismatch(AppIdentity appIdentity, boolean z) {
        TrustedApp trustedApp = this.mTrustedApp;
        if (trustedApp != null && !trustedApp.isAppIdentityTrusted(appIdentity, z)) {
            throw new SecurityException(String.format("Caller Identity '%s' is not trusted", appIdentity));
        }
    }

    /* access modifiers changed from: protected */
    public void throwIfMissingFbPermission(AppIdentity appIdentity, Context context, Reporter reporter, boolean z) {
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
        return (this.mFlags & j) != 0;
    }

    public static class TrustedCallerBuilder {
        private ArrayList<String> mDomains = new ArrayList<>();
        private long mFlags = 0;
        private ArrayList<String> mPermissions = new ArrayList<>();
        private Map<AppSignatureHash, Set<String>> mTrustedPackages = new HashMap();

        public TrustedCallerBuilder addFbPermission(String str) {
            this.mPermissions.add(str);
            return this;
        }

        public TrustedCaller build() {
            return new TrustedCaller(this);
        }
    }
}
