package com.facebook.secure.intent;

import com.facebook.secure.intent.IntentScope;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.TrustedApp;

public class TrustedAppIntentScope extends TargetedAppsIntentScope {
    private static final String TAG = "TrustedAppIntentScope";

    public TrustedAppIntentScope(TrustedApp trustedApp, LaunchEnforcement launchEnforcement, Reporter reporter) {
        this(trustedApp, launchEnforcement, reporter, new LoggingConfiguration());
    }

    public TrustedAppIntentScope(TrustedApp trustedApp, LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration) {
        this(trustedApp, launchEnforcement, reporter, loggingConfiguration, false);
    }

    public TrustedAppIntentScope(TrustedApp trustedApp, LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration, boolean z) {
        super(launchEnforcement, reporter, loggingConfiguration, trustedApp, TAG, true, z);
    }

    @Override // com.facebook.secure.intent.TargetedAppsIntentScope, com.facebook.secure.intent.IntentScope
    public IntentScope.ScopeType getScopeType() {
        return IntentScope.ScopeType.TRUSTED_APP;
    }
}
