package com.facebook.secure.intent;

import android.content.Context;
import android.content.pm.ComponentInfo;
import com.facebook.secure.intent.IntentScope;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.TrustedApp;
import com.facebook.secure.trustedapp.TrustedAppHelper;

public class ThirdPartyIntentScope extends DifferentKeyIntentScope {
    private static final String TAG = "ThirdPartyIntentScope";
    private final TrustedApp mTrustedApp;

    public ThirdPartyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter) {
        this(launchEnforcement, reporter, new LoggingConfiguration());
    }

    public ThirdPartyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration) {
        this(launchEnforcement, reporter, loggingConfiguration, TrustedAppHelper.createAllFBFamilyTrustedApp());
    }

    public ThirdPartyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration, TrustedApp trustedApp) {
        super(launchEnforcement, reporter, loggingConfiguration);
        this.mTrustedApp = trustedApp;
    }

    @Override // com.facebook.secure.intent.IntentScope
    public IntentScope.ScopeType getScopeType() {
        return IntentScope.ScopeType.THIRD_PARTY;
    }

    /* access modifiers changed from: package-private */
    @Override // com.facebook.secure.intent.DifferentKeyIntentScope
    public boolean verifySignature(Context context, ComponentInfo componentInfo) {
        if (componentInfo.applicationInfo == null) {
            this.reporter.report(TAG, "Null application info.", null);
            return false;
        }
        try {
            return !this.mTrustedApp.isAppIdentityTrusted(componentInfo.applicationInfo.uid, context);
        } catch (SecurityException e) {
            Reporter reporter = this.reporter;
            reporter.report(TAG, "Unexpected exception in checking trusted app for " + componentInfo.packageName, e);
            return !isEnforceEverywhere();
        }
    }
}
