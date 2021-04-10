package com.facebook.secure.intent;

import android.content.Context;
import android.content.pm.ComponentInfo;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.TrustedApp;

public class ThirdPartyIntentScope extends DifferentKeyIntentScope {
    private final TrustedApp mTrustedApp;

    /* access modifiers changed from: package-private */
    @Override // com.facebook.secure.intent.DifferentKeyIntentScope
    public boolean verifySignature(Context context, ComponentInfo componentInfo) {
        if (componentInfo.applicationInfo == null) {
            this.reporter.report("ThirdPartyIntentScope", "Null application info.", null);
            return false;
        }
        try {
            return !this.mTrustedApp.isAppIdentityTrusted(componentInfo.applicationInfo.uid, context);
        } catch (SecurityException e) {
            Reporter reporter = this.reporter;
            reporter.report("ThirdPartyIntentScope", "Unexpected exception in checking trusted app for " + componentInfo.packageName, e);
            return !isEnforceEverywhere();
        }
    }
}
