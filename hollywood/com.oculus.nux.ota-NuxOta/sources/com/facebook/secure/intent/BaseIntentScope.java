package com.facebook.secure.intent;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;

public abstract class BaseIntentScope implements IntentScope {
    protected final LoggingConfiguration mDelegatingLoggingLevel;
    private final LaunchEnforcement mLaunchEnforcement;
    protected final Reporter reporter;
    protected final boolean showChooser;

    public BaseIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter2, LoggingConfiguration loggingConfiguration, boolean z) {
        this.mLaunchEnforcement = launchEnforcement;
        this.reporter = reporter2;
        this.mDelegatingLoggingLevel = loggingConfiguration;
        this.showChooser = z;
    }

    @Override // com.facebook.secure.intent.IntentScope
    public Intent enforceReceiverIntent(Intent intent, Context context) {
        return enforceReceiverIntent(intent, context, null);
    }
}
