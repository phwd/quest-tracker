package com.facebook.secure.intent;

import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.TrustedApp;

public class TrustedAppIntentScope extends TargetedAppsIntentScope {
    public TrustedAppIntentScope(TrustedApp trustedApp, LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration, boolean z) {
        super(launchEnforcement, reporter, loggingConfiguration, trustedApp, "TrustedAppIntentScope", true, z);
    }
}
