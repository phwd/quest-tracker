package com.facebook.secure.intent;

import com.facebook.secure.intent.IntentScope;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.TrustedApp;
import com.facebook.secure.trustedapp.TrustedAppHelper;

public class FamilyIntentScope extends TargetedAppsIntentScope {
    private static final String TAG = "FamilyIntentScope";

    public FamilyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter) {
        this(launchEnforcement, reporter, new LoggingConfiguration());
    }

    public FamilyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration) {
        this(launchEnforcement, reporter, loggingConfiguration, false);
    }

    public FamilyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration, boolean z) {
        this(launchEnforcement, reporter, loggingConfiguration, z, TrustedAppHelper.createAllFBFamilyTrustedApp());
    }

    public FamilyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration, boolean z, TrustedApp trustedApp) {
        super(launchEnforcement, reporter, loggingConfiguration, trustedApp, TAG, true, z);
    }

    @Override // com.facebook.secure.intent.TargetedAppsIntentScope, com.facebook.secure.intent.IntentScope
    public IntentScope.ScopeType getScopeType() {
        return IntentScope.ScopeType.FAMILY;
    }
}
