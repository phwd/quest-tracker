package com.facebook.secure.intent;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.AppVerifier;

public class ExternalIntentScope extends DifferentKeyIntentScope {
    /* access modifiers changed from: package-private */
    @Override // com.facebook.secure.intent.DifferentKeyIntentScope
    public boolean verifySignature(Context context, ComponentInfo componentInfo) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        ApplicationInfo applicationInfo2 = componentInfo.applicationInfo;
        if (applicationInfo == null || applicationInfo2 == null) {
            Reporter reporter = this.reporter;
            reporter.report("ExternalIntentScope", "Null app info, current app: " + applicationInfo + ", target app: " + applicationInfo2, null);
            return false;
        }
        try {
            return !AppVerifier.verifySameSignature(context, applicationInfo, applicationInfo2);
        } catch (SecurityException e) {
            Reporter reporter2 = this.reporter;
            reporter2.report("ExternalIntentScope", "Unexpected exception in verifying signature for: " + componentInfo.packageName, e);
            return !isEnforceEverywhere();
        }
    }
}
