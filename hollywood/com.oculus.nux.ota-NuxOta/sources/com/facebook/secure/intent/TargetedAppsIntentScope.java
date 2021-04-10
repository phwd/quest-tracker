package com.facebook.secure.intent;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.AppIdentity;
import com.facebook.secure.trustedapp.CallerInfoHelper;
import com.facebook.secure.trustedapp.TrustedApp;

public class TargetedAppsIntentScope extends BaseIntentScope {
    private final boolean mAttachCallerInfo;
    private final String mTag;
    private final TrustedApp mTrustedApp;

    protected TargetedAppsIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration, TrustedApp trustedApp, String str, boolean z, boolean z2) {
        super(launchEnforcement, reporter, loggingConfiguration, z2);
        this.mTag = str;
        this.mTrustedApp = trustedApp;
        this.mAttachCallerInfo = z;
    }

    @Override // com.facebook.secure.intent.IntentScope
    public Intent enforceReceiverIntent(Intent intent, Context context, String str) {
        int i;
        int i2 = context.getApplicationInfo().uid;
        AppIdentity callerInfo = CallerInfoHelper.getCallerInfo(context, intent);
        if (callerInfo == null) {
            i = -1;
        } else {
            i = callerInfo.getUid();
        }
        if (this.mTrustedApp.isAppIdentityTrusted(i, context)) {
            return intent;
        }
        String format = String.format("Access denied. Process %d cannot receive broadcasts from %d", Integer.valueOf(i2), Integer.valueOf(i));
        this.reporter.report(this.mTag, format, new SecurityException(format));
        return null;
    }
}
